package fr.benjamindanlos.laptimes.Discord;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import fr.benjamindanlos.laptimes.Entities.Laptime;
import fr.benjamindanlos.laptimes.Service.GlobalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CommandHandler {

	@Autowired
	private GlobalService service;

	public String handle(String commandName, ChatInputInteractionEvent event) {
		try{
			switch (commandName) {
				case "search":
					return handleSearch(event);
				case "leaderboard":
					return handleLeaderboard(event);
				case "leaderboardbycar":
					return handleLeaderboardByCar(event);
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
}
