package fr.benjamindanlos.laptimes.Events.Watcher;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.benjamindanlos.laptimes.Entities.Laptime;
import fr.benjamindanlos.laptimes.Events.LaptimeEvent;
import fr.benjamindanlos.laptimes.Events.PersonnalBestEvent;
import fr.benjamindanlos.laptimes.Events.Sender.LaptimeEventSender;
import fr.benjamindanlos.laptimes.Repository.LaptimeRepository;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class PersonnalBestWatcher {
    
    //@Autowired
    private final LaptimeEventSender laptimeEventSender;

    //@Autowired
    private final LaptimeRepository laptimeRepository;

	@PrePersist
	public void triggerPersonnalBestWatcher(Laptime laptime){
		// Detect if personnal best
        Laptime currentPersonnalBest = this.laptimeRepository.findPersonnalBestByDriverAndGameAndTrackAndCar(
                laptime.getDriver(),
                laptime.getGame(),
                laptime.getTrack(),
                laptime.getCar());

        if (currentPersonnalBest != null && laptime.getLaptime()<currentPersonnalBest.getLaptime()) {
            log.info("Triggering new PersonnalBestEvent");

            LaptimeEvent event = new PersonnalBestEvent();
            event.car(laptime.getCar());
            event.track(laptime.getTrack());
            event.driver(laptime.getDriver());
            event.game(laptime.getGame());
            event.laptime(laptime.getLaptime());

            this.laptimeEventSender.sendLaptimeEvent(event);
        }
	}

}
