package fr.benjamindanlos.laptimes.F1.F12022.Packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12022.Data.ParticipantData;
import lombok.Data;

/**
 * Participants Packet
 * 
 * This is a list of participants in the race. If the vehicle is controlled by
 * AI, then the name will be the driver name. If this is a multiplayer game, the
 * names will be the Steam Id on PC, or the LAN name if appropriate.
 * Frequency: Every 5 seconds
 */
@Data
public class PacketParticipantsData extends Packet {

    // 1213
    public static final int SIZE = PacketHeader.SIZE + 
                                    1 + 
                                    ParticipantData.SIZE * PacketConstants.CARS;
    
    private short numActiveCars;
    private List<ParticipantData> participants = new ArrayList<>(PacketConstants.CARS);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Participants[");
        sb.append(super.toString());
        sb.append(",numActiveCars=" + this.numActiveCars);
        sb.append(",participants=");
        for (ParticipantData p : participants) {
            sb.append(p.toString() + ",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        this.numActiveCars = buffer.readUnsignedByte();
        for (int i = 0; i < this.numActiveCars; i++) {
            ParticipantData pd = new ParticipantData();
            this.participants.add(pd.fill(buffer));
        }
        return this;
    }

}
