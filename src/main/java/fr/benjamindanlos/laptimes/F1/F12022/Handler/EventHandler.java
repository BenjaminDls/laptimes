package fr.benjamindanlos.laptimes.F1.F12022.Handler;

import fr.benjamindanlos.laptimes.F1.F12022.Enums.EventCode;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.Track;
import fr.benjamindanlos.laptimes.F1.F12022.Packets.PacketEventData;
import fr.benjamindanlos.laptimes.F1.F12022.Packets.PacketSessionData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
@EnableScheduling
public class EventHandler extends Handler {

	public void handle(PacketEventData packetEventData){
		BigInteger player = packetEventData.getHeader().getSessionUid();
		// update map to know this player is active
		playerLastData.put(player, LocalDateTime.now(ZoneOffset.UTC));

		if(packetEventData.getEventCode() == EventCode.PENALTY_ISSUED){
			playerLapInvalid.put(player, true);
		}
	}

}
