package fr.benjamindanlos.laptimes.F12022.Handler;

import fr.benjamindanlos.laptimes.F12022.Packets.PacketLapData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@EnableScheduling
public class LapdataHandler {
	private Map<BigInteger, Integer> playerCurrentLapNumber = new HashMap<>();
	private Map<BigInteger, LocalDateTime> playerLastData = new HashMap<>();

	public void handle(PacketLapData packetLapData){
		BigInteger player = packetLapData.getHeader().getSessionUid();
		// update map to know this player is active
		playerLastData.put(player, LocalDateTime.now(ZoneOffset.UTC));

		int index = packetLapData.getHeader().getPlayerCarIndex();
		int currentLap = packetLapData.getLapData().get(index).getCurrentLapNum();
		Integer lastRegisteredLap = playerCurrentLapNumber.get(player);
		if(lastRegisteredLap==null||lastRegisteredLap<currentLap){
			// first lap or new lap
			playerCurrentLapNumber.put(player, currentLap);
			log.info("Player {} just did : {}s", player, packetLapData.getLapData().get(index).getLastLapTime());
		}
	}


	@Scheduled(fixedDelay = 5*60*1000)
	public void cleanup(){
		log.info("Starting cleanup");
		List<BigInteger> playersToRemove = new ArrayList<>();
		playerLastData.forEach((p, date)->{
			if(Duration.between(date, LocalDateTime.now(ZoneOffset.UTC)).toHours()>6){
				playersToRemove.add(p);
			}
		});
		//remove in lapnumber map
		playerCurrentLapNumber.forEach((p, l)->{
			if(playersToRemove.contains(p)){
				playerCurrentLapNumber.remove(p);
			}
		});
		//remove in activity map
		playerLastData.forEach((p, l)->{
			if(playersToRemove.contains(p)){
				playerLastData.remove(p);
			}
		});
		log.info("{} removed : {}", playersToRemove.size(), playersToRemove);
		log.info("Finished cleanup");
	}

}
