package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.ParticipantData;
import lombok.Getter;

/**
 * Participants Packet
 * 
 * This is a list of participants in the race. If the vehicle is controlled by
 * AI, then the name will be the driver name. If this is a multiplayer game, the
 * names will be the Steam Id on PC, or the LAN name if appropriate.
 * Frequency: Every 5 seconds
 */
@Getter
public class PacketParticipantsData extends Packet {

    private short numActiveCars;
    private ParticipantData[] participants = new ParticipantData[PacketConstants.CARS];

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        this.numActiveCars = buffer.readUnsignedByte();
        for (int i = 0; i < this.numActiveCars; i++) {
            ParticipantData pd = new ParticipantData();
            this.participants[i] = pd.fill(buffer);
        }
        return this;
    }

}
