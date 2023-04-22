package fr.benjamindanlos.laptimes.F1.F12022.Handler;

import fr.benjamindanlos.laptimes.F1.F12022.Cleaner;
import fr.benjamindanlos.laptimes.F1.F12022.Packets.PacketCarTelemetryData;
import fr.benjamindanlos.laptimes.F1.F12022.Packets.PacketLapData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@EnableScheduling
public class TelemetryHandler extends Handler {

	public void handle(PacketCarTelemetryData packetCarTelemetryData){
		BigInteger player = packetCarTelemetryData.getHeader().getSessionUid();
		// update map to know this player is active
		getPlayerLastData().put(player, LocalDateTime.now(ZoneOffset.UTC));

		int index = packetCarTelemetryData.getHeader().getPlayerCarIndex();
		int speed = packetCarTelemetryData.getCarTelemetryData().get(index).getSpeed();
		log.info("Player {} : {}km/h", player, speed);
	}

}
