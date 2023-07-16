package fr.benjamindanlos.laptimes.Discord;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class LaptimesBot {

	@Value("${discord.bot.id}")
	private Long BOTID;

	@Autowired
	private RestClient restClient;

	@Autowired
	private GatewayDiscordClient gatewayDiscordClient;

	@Autowired
	private CommandHandler commandHandler;

	@PostConstruct
	public void init(){
		final ApplicationService applicationService = restClient.getApplicationService();

		//code to delete a command
		/*applicationService.getGlobalApplicationCommands(BOTID).flatMap(cmd -> {
			if (cmd.name().startsWith("seach")||"test".equals(cmd.name())) {
				return applicationService.deleteGlobalApplicationCommand(BOTID, cmd.id().asLong());
			}else{
				return Mono.empty();
			}
		}).subscribe();*/

		//Register commands
		registerCommandBestDriverLapCarTrackGame(applicationService);
		registerCommandLeaderBoardByCar(applicationService);
		registerCommandLeaderBoard(applicationService);
		registerCommandEndSession(applicationService);

		//Register command handlers (all chat commands go to one handler that dispatches the execution)
		gatewayDiscordClient.on(ChatInputInteractionEvent.class, this::handleCommand)
				.subscribe();
		/*gatewayDiscordClient.on(LaptimeEvent.class, this::handleNewLaptime)
				.subscribe();

		log.info("raising");
		Mono.just(new LaptimeEvent(gatewayDiscordClient, ShardInfo.create(1,1), new Laptime()));*/

	}

	private void registerCommandBestDriverLapCarTrackGame(ApplicationService applicationService){
		// Build our command's definition
		ApplicationCommandRequest cmdBestDriverLapCarTrackGame = ApplicationCommandRequest.builder()
				.name("search")
				.description("Search the best track's laptime of a driver with a specified car for a game")
				.addOption(ApplicationCommandOptionData.builder()
						.name("driver")
						.description("Driver name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("track")
						.description("Track name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("car")
						.description("Car name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("game")
						.description("Game name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).build();
		// Create the command with Discord
		applicationService.createGlobalApplicationCommand(BOTID, cmdBestDriverLapCarTrackGame)
				.doOnNext(ignore -> log.info("Successfully registered searchBest command"))
				.doOnError(e -> log.error("Failed to register searchBest command", e))
				.subscribe();
	}

	private void registerCommandLeaderBoard(ApplicationService applicationService){
		// Build our command's definition
		ApplicationCommandRequest cmdLeaderboard = ApplicationCommandRequest.builder()
				.name("leaderboard")
				.description("Display all the fastest laps on a game's track by driver regardless of the car")
				.addOption(ApplicationCommandOptionData.builder()
						.name("track")
						.description("Track name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("game")
						.description("Game name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).build();
		// Create the command with Discord
		applicationService.createGlobalApplicationCommand(BOTID, cmdLeaderboard)
				.doOnNext(ignore -> log.info("Successfully registered leaderboard command"))
				.doOnError(e -> log.error("Failed to register leaderboard command", e))
				.subscribe();
	}

	private void registerCommandLeaderBoardByCar(ApplicationService applicationService){
		// Build our command's definition
		ApplicationCommandRequest cmdLeaderboardByCar = ApplicationCommandRequest.builder()
				.name("leaderboardbycar")
				.description("Display all the fastest laps on a game's track by driver with a specific car")
				.addOption(ApplicationCommandOptionData.builder()
						.name("track")
						.description("Track name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("car")
						.description("Car name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("game")
						.description("Game name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).build();
		// Create the command with Discord
		applicationService.createGlobalApplicationCommand(BOTID, cmdLeaderboardByCar)
				.doOnNext(ignore -> log.info("Successfully registered leaderboardByCar command"))
				.doOnError(e -> log.error("Failed to register leaderboardByCar command", e))
				.subscribe();
	}

	private void registerCommandEndSession(ApplicationService applicationService){
		// Build our command's definition
		ApplicationCommandRequest cmdEndSession = ApplicationCommandRequest.builder()
				.name("endsession")
				.description("Display a summary of the fastest laps of a session")
				.addOption(ApplicationCommandOptionData.builder()
						.name("day")
						.description("Session's day. Default : today.")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(false)
						.build()
				).build();
		// Create the command with Discord
		applicationService.createGlobalApplicationCommand(BOTID, cmdEndSession)
				.doOnNext(ignore -> log.info("Successfully registered endSession command"))
				.doOnError(e -> log.error("Failed to register endSession command", e))
				.subscribe();
	}

	private Mono<Void> handleCommand(ChatInputInteractionEvent event){
		try{
			String r = commandHandler.handle(event.getCommandName(), event);
			return event.reply(r);
		}
		catch (Exception e){
			// Retry only once, trying to solve the error when sending a first command after a long time
			// (after db connection reset, takes more time)
			String r = commandHandler.handle(event.getCommandName(), event);
			return event.reply(r);
		}
	}

	/*private Mono<Void> handleNewLaptime(LaptimeEvent event){
		log.info("raised");
		return Mono.empty();
	}*/

}
