package fr.benjamindanlos.laptimes.F1.F12023.Handler;

import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Track;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Packets.PacketSessionData;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class SessionHandler extends Handler {

	public void handle(PacketSessionData packetSessionData){
		BigInteger player = packetSessionData.getHeader().getSessionUid();
		// update map to know this player is active
		playerLastData.put(player, LocalDateTime.now(ZoneOffset.UTC));

		Track track = packetSessionData.getTrackId();
		//log.info("track={}", track.name());
		playerTracks.put(player, track.name());
	}

}
