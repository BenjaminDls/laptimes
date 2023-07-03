package fr.benjamindanlos.laptimes.F1.F12022.Handler;

import fr.benjamindanlos.laptimes.Entities.Games;
import fr.benjamindanlos.laptimes.Entities.Laptime;
import fr.benjamindanlos.laptimes.F1.F12022.Packets.PacketLapData;
import fr.benjamindanlos.laptimes.Repository.LaptimeRepository;
import fr.benjamindanlos.laptimes.Utils.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
@EnableScheduling
public class LapdataHandler extends Handler {

	@Autowired
	private LaptimeRepository laptimeRepository;

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
			String playername = playerNames.get(player);
			String playerCar = playerCars.get(player);
			String playerTrack = playerTracks.get(player);
			int time = packetLapData.getLapData().get(index).getLastLapTime();
			log.info("{} just did {}s on {} driving {} (invalid:{})",
					playername, time, playerTrack, playerCar, playerLapInvalid.get(player));

			if(playername!=null && !playername.isEmpty() &&
					playerCar!=null && !playerCar.isEmpty() &&
					playerTrack!=null && !playerTrack.isEmpty() &&
					time > 0.0f && !playerLapInvalid.get(player)
			)
			{
				Laptime laptime = new Laptime();
				laptime.setGame(Games.F12022);
				laptime.setDriver(playername);
				laptime.setCar(playerCar);
				laptime.setTrack(playerTrack);
				laptime.setLaptime(time);
				laptime.setLaptimeString(Tools.laptimeToString(time));
				laptime.setDate(LocalDateTime.now(ZoneOffset.UTC));
				laptimeRepository.save(laptime);
			}
			Boolean newLapInvalid = packetLapData.getLapData().get(index).getCurrentLapInvalid()==1;
			playerLapInvalid.put(player, newLapInvalid);
		}
	}

}
