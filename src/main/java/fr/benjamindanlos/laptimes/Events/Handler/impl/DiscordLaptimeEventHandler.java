package fr.benjamindanlos.laptimes.Events.Handler.impl;

import discord4j.core.spec.EmbedCreateFields;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.benjamindanlos.laptimes.Discord.LaptimesBot;
import fr.benjamindanlos.laptimes.Events.LaptimeEvent;
import fr.benjamindanlos.laptimes.Events.Handler.LaptimeEventHandler;
import fr.benjamindanlos.laptimes.Utils.Tools;
import lombok.RequiredArgsConstructor;

@Component
@Qualifier("DiscordLaptimeEventHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscordLaptimeEventHandler implements LaptimeEventHandler{
    
    @Value("${discord.events.channelId}")
    private String channelId;

    private final LaptimesBot laptimesBot;

    @Override
    public void handleEvent(LaptimeEvent event){

        this.laptimesBot.sendMessageEmbeded(channelId, "New "+event.type().toString(), 
                EmbedCreateFields.Field.of("Driver", event.driver(), true),
                EmbedCreateFields.Field.of("Car", event.car(), true),
                EmbedCreateFields.Field.of("Track", event.track(), true),
                EmbedCreateFields.Field.of("Game", event.game(), true),
                EmbedCreateFields.Field.of("Laptime", Tools.laptimeToString(event.laptime()), true)
        );
    }
}
