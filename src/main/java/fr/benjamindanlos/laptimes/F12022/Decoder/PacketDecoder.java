/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Decoder;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F12022.Enums.PacketId;
import fr.benjamindanlos.laptimes.F12022.Packets.Packet;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketCarSetupData;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketCarStatusData;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketCarTelemetryData;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketEventData;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketFinalClassificationData;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketHeader;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketLapData;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketLobbyInfoData;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketMotionData;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketParticipantsData;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketSessionData;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Packet decoder
 */
public class PacketDecoder {

    /**
     * Decode the input buffer containing the packet raw bytes filling
     * a new concrete Packet instance.
     * 
     * @param buffer buffer with the packet raw bytes
     * @return decoded Packet instance
     */
    public Packet decodeUsingHeader(ByteBuf buffer) {
        PacketHeader header = new PacketHeader().fill(buffer);
		if(header==null || header.getPacketId()==null){
			return null;
		}
		//we read some of the data, reset to start so the fill() will be able to set the header without overflow
		buffer = buffer.resetReaderIndex();
		PacketId packetId = PacketId.valueOf(header.getPacketId().getValue());
		Packet packet = switch (packetId) {
			case CAR_SETUPS -> new PacketCarSetupData().fill(buffer);
			case CAR_STATUS -> new PacketCarStatusData().fill(buffer);
			case CAR_TELEMETRY -> new PacketCarTelemetryData().fill(buffer);
			case EVENT -> new PacketEventData().fill(buffer);
			case FINAL_CLASSIFICATION -> new PacketFinalClassificationData().fill(buffer);
			case LAP_DATA -> new PacketLapData().fill(buffer);
			case LOBBY_INFO -> new PacketLobbyInfoData().fill(buffer);
			case MOTION -> new PacketMotionData().fill(buffer);
			case PARTICIPANTS -> new PacketParticipantsData().fill(buffer);
			case SESSION -> new PacketSessionData().fill(buffer);
		};
		return packet.setHeader(header);
    }

}
