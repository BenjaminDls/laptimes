package fr.benjamindanlos.laptimes;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.rest.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@ComponentScan
public class LaptimesApplication {
	public static void main(String[] args) {
		SpringApplication.run(LaptimesApplication.class, args);
	}

	@Value("${discord.token}")
	private String TOKEN;
	@Bean
	public GatewayDiscordClient gatewayDiscordClient() {
		return DiscordClientBuilder.create(TOKEN).build()
				.gateway()
				.setInitialPresence(ignore -> ClientPresence.online(ClientActivity.listening("to /commands")))
				.login()
				.block();
	}

	@Bean
	public RestClient discordRestClient(GatewayDiscordClient client) {
		return client.getRestClient();
	}
}
