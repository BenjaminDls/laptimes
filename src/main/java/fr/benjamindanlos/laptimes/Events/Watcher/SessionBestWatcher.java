package fr.benjamindanlos.laptimes.Events.Watcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.benjamindanlos.laptimes.Entities.Laptime;
import fr.benjamindanlos.laptimes.Events.LaptimeEvent;
import fr.benjamindanlos.laptimes.Events.SessionBestEvent;
import fr.benjamindanlos.laptimes.Events.Sender.LaptimeEventSender;
import fr.benjamindanlos.laptimes.Repository.LaptimeRepository;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class SessionBestWatcher {

    //@Autowired
    private final LaptimeEventSender laptimeEventSender;

    //@Autowired
    private final LaptimeRepository laptimeRepository;
    
    @PrePersist
    public void triggerSessionBestWatcher(Laptime laptime){
        // Detect if session's best (not best if first lap)
        Laptime sessionBestLatime = this.laptimeRepository.findSessionBestByGameAndTrackAndCar(
                laptime.getGame(), 
                laptime.getTrack(), 
                laptime.getCar());

        if(sessionBestLatime != null && laptime.getLaptime()<sessionBestLatime.getLaptime()){
            log.info("Triggering new SessionBestEvent");

            LaptimeEvent event = new SessionBestEvent();
                event.car(laptime.getCar());
                event.track(laptime.getTrack());
                event.driver(laptime.getDriver());
                event.game(laptime.getGame());
                event.laptime(laptime.getLaptime());

            this.laptimeEventSender.sendLaptimeEvent(event);
        }
    }

}
