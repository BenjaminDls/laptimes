package fr.benjamindanlos.laptimes.F1.F12023.Handler;

import fr.benjamindanlos.laptimes.F1.F12023.Models.Packets.PacketEventData;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class EventHandler extends Handler {

	public void handle(PacketEventData packetEventData){
		BigInteger player = packetEventData.getHeader().getSessionUid();
		// update map to know this player is active
		playerLastData.put(player, LocalDateTime.now(ZoneOffset.UTC));
	}

}
