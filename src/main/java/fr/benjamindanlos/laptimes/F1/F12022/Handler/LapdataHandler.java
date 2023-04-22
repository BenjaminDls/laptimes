package fr.benjamindanlos.laptimes.F1.F12022.Handler;

import fr.benjamindanlos.laptimes.F1.F12022.Packets.PacketLapData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
@EnableScheduling
public class LapdataHandler extends Handler {

	public void handle(PacketLapData packetLapData){
		BigInteger player = packetLapData.getHeader().getSessionUid();
		// update map to know this player is active
		getPlayerLastData().put(player, LocalDateTime.now(ZoneOffset.UTC));

		int index = packetLapData.getHeader().getPlayerCarIndex();
		int currentLap = packetLapData.getLapData().get(index).getCurrentLapNum();
		Integer lastRegisteredLap = getPlayerCurrentLapNumber().get(player);
		if(lastRegisteredLap==null||lastRegisteredLap<currentLap){
			// first lap or new lap
			getPlayerCurrentLapNumber().put(player, currentLap);
			log.info("Player {} just did : {}s", player, packetLapData.getLapData().get(index).getLastLapTime());
		}
	}

}
