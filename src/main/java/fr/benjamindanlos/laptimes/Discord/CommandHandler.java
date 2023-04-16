package fr.benjamindanlos.laptimes.Discord;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import fr.benjamindanlos.laptimes.Entities.Laptime;
import fr.benjamindanlos.laptimes.Service.GlobalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CommandHandler {

	@Autowired
	private GlobalService service;

	/**
	 * Dispatch the command to a method based on the commandName
	 * @param commandName name of the command
	 * @param event event with all the information to forward
	 * @return a string to reply with
	 */
	public String handle(String commandName, ChatInputInteractionEvent event) {
		try{
			switch (commandName) {
				case "search":
					return handleSearch(event);
				case "leaderboard":
					return handleLeaderboard(event);
				case "leaderboardbycar":
					return handleLeaderboardByCar(event);
				case "endsession":
					return handleEndSession(event);
				default:
					return "This command is not implemented (yet?)";
			}
		}
		catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return "An error occurred, please try again later";
		}
	}

	/**
	 * proceed to the search according to the values provided in the command
	 * @param event event of the command with the values
	 * @return a laptime as discord string or a placeholder of nothing found
	 */
	public String handleSearch(ChatInputInteractionEvent event){

		Optional<ApplicationCommandInteractionOption> driverOption = event.getOption("driver");
		Optional<ApplicationCommandInteractionOption> trackOption = event.getOption("track");
		Optional<ApplicationCommandInteractionOption> carOption = event.getOption("car");
		Optional<ApplicationCommandInteractionOption> gameOption = event.getOption("game");
		if(driverOption.isEmpty() || trackOption.isEmpty() || carOption.isEmpty() || gameOption.isEmpty()){
			throw new IllegalArgumentException("Missing option(s)");
		}

		Optional<ApplicationCommandInteractionOptionValue> driverValue = driverOption.get().getValue();
		Optional<ApplicationCommandInteractionOptionValue> trackValue = trackOption.get().getValue();
		Optional<ApplicationCommandInteractionOptionValue> carValue = carOption.get().getValue();
		Optional<ApplicationCommandInteractionOptionValue> gameValue = gameOption.get().getValue();

		if(driverValue.isEmpty() || trackValue.isEmpty() || carValue.isEmpty() || gameValue.isEmpty()){
			throw new IllegalArgumentException("Missing option value(s)");
		}

		String driver = driverValue.get().asString();
		String track = trackValue.get().asString();
		String car = carValue.get().asString();
		String game = gameValue.get().asString();

		Laptime laptime = service.search(driver, track, car, game);
		if(laptime==null){
			return "No entries found";
		}
		else{
			return laptime.toDiscordString();
		}
	}

	/**
	 * generate a leaderboard from a track of driver's best laptimes
	 * @param event event of the command with the values
	 * @return the leaderboard formatted in a string
	 */
	public String handleLeaderboard(ChatInputInteractionEvent event) {
		Optional<ApplicationCommandInteractionOption> trackOption = event.getOption("track");
		Optional<ApplicationCommandInteractionOption> gameOption = event.getOption("game");
		if(trackOption.isEmpty() || gameOption.isEmpty()){
			throw new IllegalArgumentException("Missing option(s)");
		}

		Optional<ApplicationCommandInteractionOptionValue> trackValue = trackOption.get().getValue();
		Optional<ApplicationCommandInteractionOptionValue> gameValue = gameOption.get().getValue();

		if(trackValue.isEmpty() || gameValue.isEmpty()){
			throw new IllegalArgumentException("Missing option value(s)");
		}

		String track = trackValue.get().asString();
		String game = gameValue.get().asString();

		List<Laptime> laptimes = service.generateLeaderBoard(track, game);
		if(laptimes==null || laptimes.isEmpty()){
			return "No entries found";
		}
		else{
			StringBuilder sb = new StringBuilder();
			sb.append("Leaderboard for ").append(track).append(" ").append(game).append(" :\n");
			laptimes.forEach(l -> sb.append(l.getDriver()).append(" : ").append(l.getLaptimeString()).append(" with ").append(l.getCar()).append("\n"));
			return sb.toString();
		}
	}

	/**
	 * generate a leaderboard from a track of driver's best laptimes limited to one car
	 * @param event event of the command with the values
	 * @return the leaderboard formatted in a string
	 */
	public String handleLeaderboardByCar(ChatInputInteractionEvent event) {
		Optional<ApplicationCommandInteractionOption> trackOption = event.getOption("track");
		Optional<ApplicationCommandInteractionOption> gameOption = event.getOption("game");
		Optional<ApplicationCommandInteractionOption> carOption = event.getOption("car");

		if(trackOption.isEmpty() || gameOption.isEmpty() || carOption.isEmpty()){
			throw new IllegalArgumentException("Missing option(s)");
		}

		Optional<ApplicationCommandInteractionOptionValue> trackValue = trackOption.get().getValue();
		Optional<ApplicationCommandInteractionOptionValue> gameValue = gameOption.get().getValue();
		Optional<ApplicationCommandInteractionOptionValue> carValue = carOption.get().getValue();

		if(trackValue.isEmpty() || gameValue.isEmpty() || carValue.isEmpty()){
			throw new IllegalArgumentException("Missing option value(s)");
		}

		String track = trackValue.get().asString();
		String game = gameValue.get().asString();
		String car = carValue.get().asString();

		List<Laptime> laptimes = service.generateLeaderBoardByCar(track, game, car);
		if(laptimes==null || laptimes.isEmpty()){
			return "No entries found";
		}
		else{
			StringBuilder sb = new StringBuilder();
			sb.append("Leaderboard for ").append(track).append(" ").append(game).append(" for cars : ").append(car).append(" :\n");
			laptimes.forEach(l -> sb.append(l.getDriver()).append(" : ").append(l.getLaptimeString()).append("\n"));
			return sb.toString();
		}
	}

	/**
	 * generate a session summary
	 * @param event event of the command
	 * @return a formatted text of the summary
	 */
	public String handleEndSession(ChatInputInteractionEvent event){
		Optional<ApplicationCommandInteractionOption> dateOption = event.getOption("day");

		LocalDate date;

		if(dateOption.isPresent()){
			Optional<ApplicationCommandInteractionOptionValue> dateValue = dateOption.get().getValue();
			if(dateValue.isPresent()) {
				String dateString = dateValue.get().asString();
				log.info(dateValue.get().asString());
				date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
				log.info(date.toString());
			}
			else{
				date = LocalDate.now();
			}
		}
		else{
			date = LocalDate.now();
		}


		StringBuilder sb = new StringBuilder();
		//step 1 : get the best lap per driver per track of the session (=day)
		List<Laptime> sessionBests = service.sessionBests(date);
		sb.append("__Best laps of the session :__\n\n");
		sessionBests.forEach(laptime -> {
			sb.append("**").append(laptime.getDriver()).append("**, ")
					.append(laptime.getTrack()).append(", *")
					.append(laptime.getLaptimeString()).append("*, ")
					.append(laptime.getCar()).append("\n");
		});
		sb.append("\n__Full session__\n\n");
		List<Laptime> allSession = service.allLaptimesOfSession(date);
		allSession.forEach(laptime -> {
			sb.append("**").append(laptime.getDriver()).append("**, ")
					.append(laptime.getTrack()).append(", *")
					.append(laptime.getLaptimeString()).append("*, ")
					.append(laptime.getCar()).append("\n");
		});
		return sb.toString();
	}
}
