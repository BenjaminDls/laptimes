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

import java.util.HashMap;

@Slf4j
@Service
public class LaptimesBot {

	@Value("${discord.bot.id}")
	private Long BOTID;

	@Autowired
	private RestClient restClient;

	@Autowired
	private GatewayDiscordClient gatewayDiscordClient;

	@PostConstruct
	public void init(){
		final ApplicationService applicationService = restClient.getApplicationService();

		// Build our command's definition
		ApplicationCommandRequest greetCmdRequest = ApplicationCommandRequest.builder()
				.name("test")
				.description("Simple parsing test")
				.addOption(ApplicationCommandOptionData.builder()
						.name("driver")
						.description("Driver criteria")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("track")
						.description("Track criteria")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("car")
						.description("Car criteria")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).addOption(ApplicationCommandOptionData.builder()
						.name("game")
						.description("Game criteria")
						.type(ApplicationCommandOption.Type.STRING.getValue())
						.required(true)
						.build()
				).build();
		// Create the command with Discord

		applicationService.createGlobalApplicationCommand(BOTID, greetCmdRequest)
				.doOnNext(ignore -> log.info("Successfully registered Global Commands"))
				.doOnError(e -> log.error("Failed to register global commands", e))
				.subscribe();

		gatewayDiscordClient.on(ChatInputInteractionEvent.class, this::handleCommand)
				.doOnNext(i -> log.info("okok"))
				.doOnError(e -> log.error("???????", e))
				.subscribe();
		/*.getEventDispatcher().on(ChatInputInteractionEvent.class)
				.flatMap(event -> Mono.justOrEmpty(event.toString())
				.flatMap(content -> {
					BotCommand command = commandParser.decomposeCommand(content);
					handleCommand(event, command);
					return Mono.empty();
				})).subscribe();*/
	}


	private Mono<Void> handleCommand(ChatInputInteractionEvent event){
		BotCommand command = new BotCommand("/test", new HashMap<>());//commandParser.decomposeCommand(event.getInteraction().toString());
		StringBuilder msg = new StringBuilder();
		command.setCommand(event.getCommandName());

		command.getArgs().put("driver", event.getOption("driver").get().getValue().get().asString());
		command.getArgs().put("track", event.getOption("track").get().getValue().get().asString());
		command.getArgs().put("car",event.getOption("car").get().getValue().get().asString());
		command.getArgs().put("game",event.getOption("game").get().getValue().get().asString());

		msg.append("Received command \""+command.getCommand()+"\" with args : ");
		command.getArgs().forEach((key, value)-> msg.append("\""+key+":"+value+"\""));
		return event.reply(msg.toString());//.withEmbeds();
		//return Mono.empty();
	}
}
