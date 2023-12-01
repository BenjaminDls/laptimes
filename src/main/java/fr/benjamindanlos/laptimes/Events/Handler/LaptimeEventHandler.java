package fr.benjamindanlos.laptimes.Events.Handler;

import org.springframework.stereotype.Component;

import fr.benjamindanlos.laptimes.Events.LaptimeEvent;

@Component
public interface LaptimeEventHandler {
    public void handleEvent(LaptimeEvent event);
}
