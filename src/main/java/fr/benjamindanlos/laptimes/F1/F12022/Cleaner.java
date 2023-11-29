package fr.benjamindanlos.laptimes.F1.F12022;

import fr.benjamindanlos.laptimes.F1.F12022.Handler.Handler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class Cleaner {

	@Scheduled(fixedDelay = 5*60*1000)//5min
	void cleanup(){
		List<BigInteger> playersToRemove = new ArrayList<>();
		//get list of keys to remove
		Handler.getPlayerLastData().forEach((p, date)->{
			if(Duration.between(date, LocalDateTime.now(ZoneOffset.UTC)).toHours()>6){
				playersToRemove.add(p);
			}
		});


		//remove in lapnumber map
		Handler.getPlayerCurrentLapNumber().forEach((p, l)->{
			if(playersToRemove.contains(p)){
				Handler.getPlayerCurrentLapNumber().remove(p);
			}
		});

		//remove in playernames
		Handler.getPlayerNames().forEach((p, n)->{
			if(playersToRemove.contains(p)){
				Handler.getPlayerNames().remove(p);
			}
		});

		//remove in activity map
		Handler.getPlayerLastData().forEach((p, l)->{
			if(playersToRemove.contains(p)){
				Handler.getPlayerLastData().remove(p);
			}
		});
	}

}
