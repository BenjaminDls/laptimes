package fr.benjamindanlos.laptimes.F1.F12022.Handler;

import fr.benjamindanlos.laptimes.Entities.Games;
import fr.benjamindanlos.laptimes.Entities.Laptime;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.Driver;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.Track;
import fr.benjamindanlos.laptimes.F1.F12022.Packets.PacketLapData;
import fr.benjamindanlos.laptimes.F1.F12022.Packets.PacketSessionData;
import fr.benjamindanlos.laptimes.Utils.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
@EnableScheduling
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
