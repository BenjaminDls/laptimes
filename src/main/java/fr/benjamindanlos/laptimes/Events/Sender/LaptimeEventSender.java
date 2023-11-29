package fr.benjamindanlos.laptimes.Events.Sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import fr.benjamindanlos.laptimes.Events.LaptimeEvent;
import fr.benjamindanlos.laptimes.Events.Handler.LaptimeEventHandler;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LaptimeEventSender {

    @Qualifier(value = "DiscordLaptimeEventHandler")
    private final LaptimeEventHandler handler;

    @Async
    public void sendLaptimeEvent(LaptimeEvent event){
        handler.handleEvent(event);
    }
}
