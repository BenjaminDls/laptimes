package fr.benjamindanlos.laptimes.F1.F12022;

import fr.benjamindanlos.laptimes.F1.F12022.Handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private Handler handler;

	@Scheduled(fixedDelay = 5*60*1000)//5min
	void cleanup(){
		List<BigInteger> playersToRemove = new ArrayList<>();
		//get list of keys to remove
		handler.getPlayerLastData().forEach((p, date)->{
			if(Duration.between(date, LocalDateTime.now(ZoneOffset.UTC)).toHours()>6){
				playersToRemove.add(p);
			}
		});


		//remove in lapnumber map
		handler.getPlayerCurrentLapNumber().forEach((p, l)->{
			if(playersToRemove.contains(p)){
				handler.getPlayerCurrentLapNumber().remove(p);
			}
		});

		//remove in playernames
		handler.getPlayerNames().forEach((p, n)->{
			if(playersToRemove.contains(p)){
				handler.getPlayerNames().remove(p);
			}
		});

		//remove in activity map
		handler.getPlayerLastData().forEach((p, l)->{
			if(playersToRemove.contains(p)){
				handler.getPlayerLastData().remove(p);
			}
		});
	}

}
