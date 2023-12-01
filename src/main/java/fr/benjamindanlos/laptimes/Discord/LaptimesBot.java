package fr.benjamindanlos.laptimes.Discord;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputAutoCompleteEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.spec.EmbedCreateFields;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import discord4j.rest.util.Color;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LaptimesBot {

	@Value("${discord.bot.id}")
	private Long BOTID;

	@Value("${discord.devmode:false}")
	private String devMode;

	private final RestClient restClient;

	private final GatewayDiscordClient gatewayDiscordClient;

	private final CommandHandler commandHandler;

	@Autowired
	private AutoCompleteHandler autoCompleteHandler;

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
		gatewayDiscordClient.on(ChatInputInteractionEvent.class, (event)->{
			return event.deferReply().then(event.createFollowup(this.handleCommand(event)));
		}).subscribe();

		if("true".equalsIgnoreCase(devMode)){
			ApplicationCommandRequest devCmd = ApplicationCommandRequest.builder()
					.name("dev")
					.description("null")
					.build();
			// Create the command with Discord
			applicationService.createGlobalApplicationCommand(BOTID, devCmd)
					.doOnNext(ignore -> log.info("Successfully registered dev command"))
					.doOnError(e -> log.error("Failed to register dev command", e))
					.subscribe();
		}

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
						.autocomplete(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("track")
						.description("Track name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.autocomplete(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("car")
						.description("Car name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.autocomplete(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("game")
						.description("Game name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.autocomplete(true)
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
						.autocomplete(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("game")
						.description("Game name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.autocomplete(true)
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
						.autocomplete(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("car")
						.description("Car name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.autocomplete(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("game")
						.description("Game name")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.autocomplete(true)
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

	private String handleCommand(ChatInputInteractionEvent event){
		String r = commandHandler.handle(event.getCommandName(), event);
		return r;
	}

	public void sendMessage(String channelId, String message){
		this.gatewayDiscordClient.getChannelById(Snowflake.of(channelId))
			.ofType(GuildMessageChannel.class)
			.flatMap(channel -> channel.createMessage(message))
			.doOnError(error->log.error(error.getMessage()))
			.subscribe();
	}

	public void sendMessageEmbeded(String channelId, String title, EmbedCreateFields.Field... fields){
		EmbedCreateSpec embed = EmbedCreateSpec.builder()
			.color(Color.of(0xF48FB1))
			.title(title)
			.addFields(fields)
			.timestamp(Instant.now())
			.build();

		log.info(embed.toString());
		this.gatewayDiscordClient.getChannelById(Snowflake.of(channelId))
			.ofType(GuildMessageChannel.class)
			.flatMap(channel -> channel.createMessage(embed))
			.doOnError(error->log.error(error.getMessage()))
			.subscribe();
	}

}
