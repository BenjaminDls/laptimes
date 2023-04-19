package fr.benjamindanlos.laptimes.F12022.Handler;

import fr.benjamindanlos.laptimes.F12022.Packets.PacketHeader;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketLapData;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PacketEventHandler {

	@Autowired
	private LapdataHandler lapdataHandler;

	public void handlePacket(PacketHeader header, ByteBuf buffer){
		if(header==null||header.getPacketId()==null){
			return;
		}
		switch (header.getPacketId()) {
			case CAR_SETUPS -> handleSetupData(buffer, header);
			case CAR_STATUS -> handleStatusData(buffer, header);
			case CAR_TELEMETRY -> handleTelemetryData(buffer, header);
			case EVENT -> handleEventData(buffer, header);
			case FINAL_CLASSIFICATION -> handleFinalClassificationData(buffer, header);
			case LAP_DATA -> handleLapData(buffer, header);
			case LOBBY_INFO -> handleLobbyInfoData(buffer, header);
			case MOTION -> handleMotionData(buffer, header);
			case PARTICIPANTS -> handleParticipantsData(buffer, header);
			case SESSION -> handleSessionData(buffer, header);
		}
	}

	private void handleSetupData(ByteBuf buffer, PacketHeader header){}
	private void handleStatusData(ByteBuf buffer, PacketHeader header){}
	private void handleTelemetryData(ByteBuf buffer, PacketHeader header){}
	private void handleEventData(ByteBuf buffer, PacketHeader header){}
	private void handleFinalClassificationData(ByteBuf buffer, PacketHeader header){}
	private void handleLapData(ByteBuf buffer, PacketHeader header){
		PacketLapData packetLapData = new PacketLapData();
		packetLapData.setHeader(header);
		packetLapData.fill(buffer);
		lapdataHandler.handle(packetLapData);
	}
	private void handleLobbyInfoData(ByteBuf buffer, PacketHeader header){}
	private void handleMotionData(ByteBuf buffer, PacketHeader header){}
	private void handleParticipantsData(ByteBuf buffer, PacketHeader header){}
	private void handleSessionData(ByteBuf buffer, PacketHeader header){}

}
