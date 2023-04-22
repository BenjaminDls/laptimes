package fr.benjamindanlos.laptimes.F1.F12022.Handler;

import fr.benjamindanlos.laptimes.F1.F12022.Data.ParticipantData;
import fr.benjamindanlos.laptimes.F1.F12022.Packets.PacketParticipantsData;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class ParticipantHandler extends Handler {

	public void handle(PacketParticipantsData packetParticipantsData){
		BigInteger player = packetParticipantsData.getHeader().getSessionUid();
		// update map to know this player is active
		getPlayerLastData().put(player, LocalDateTime.now(ZoneOffset.UTC));
		int index = packetParticipantsData.getHeader().getPlayerCarIndex();
		ParticipantData playersPacket = packetParticipantsData.getParticipants().get(index);
		String driverName = playersPacket.getName();
		this.getPlayerNames().put(player, driverName);
	}
}
