package fr.benjamindanlos.laptimes.F1.F12023.Handler;

import fr.benjamindanlos.laptimes.F1.F12023.Models.Packets.*;
import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacketHandler {

	@Autowired
	private LapdataHandler lapdataHandler;

	
	@Autowired
	private ParticipantHandler participantHandler;

	@Autowired
	private SessionHandler sessionHandler;

	@Autowired
	private EventHandler eventHandler;

	public void handlePacket(ByteBuf buffer){

		PacketHeader header = new PacketHeader().fill(buffer);

		if(header.getPacketId()!=null){
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
				case CAR_DAMAGE -> handleCarDamageData(buffer, header);
				case SESSION_HISTORY -> handleSessionHistoryData(buffer, header);
				case TYRE_SETS -> handleTyreSetsData(buffer, header);
				case MOTION_EXTENDED -> handleMotionExtendedData(buffer, header);
			}
		}
	}

	private void handleSetupData(ByteBuf buffer, PacketHeader header){}

	private void handleStatusData(ByteBuf buffer, PacketHeader header){}

	private void handleTelemetryData(ByteBuf buffer, PacketHeader header){}

	private void handleEventData(ByteBuf buffer, PacketHeader header){
		PacketEventData packetEventData = new PacketEventData();
		packetEventData.setHeader(header);
		packetEventData.fill(buffer);
		eventHandler.handle(packetEventData);
	}
	
	private void handleFinalClassificationData(ByteBuf buffer, PacketHeader header){}
	
	private void handleLapData(ByteBuf buffer, PacketHeader header){
		PacketLapData packetLapData = new PacketLapData();
		packetLapData.setHeader(header);
		packetLapData.fill(buffer);
		lapdataHandler.handle(packetLapData);
	}

	private void handleLobbyInfoData(ByteBuf buffer, PacketHeader header){}

	private void handleMotionData(ByteBuf buffer, PacketHeader header){}

	private void handleParticipantsData(ByteBuf buffer, PacketHeader header){
		PacketParticipantsData packetParticipantsData = new PacketParticipantsData();
		packetParticipantsData.setHeader(header);
		packetParticipantsData.fill(buffer);
		participantHandler.handle(packetParticipantsData);
	}

	private void handleSessionData(ByteBuf buffer, PacketHeader header){
		PacketSessionData packetSessionData = new PacketSessionData();
		packetSessionData.setHeader(header);
		packetSessionData.fill(buffer);
		sessionHandler.handle(packetSessionData);
	}

	private void handleCarDamageData(ByteBuf buffer, PacketHeader header){}

	private void handleSessionHistoryData(ByteBuf buffer, PacketHeader header){}

	private void handleTyreSetsData(ByteBuf buffer, PacketHeader header){}

	private void handleMotionExtendedData(ByteBuf buffer, PacketHeader header){}

}
