package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.FinalClassificationData;
import lombok.Getter;

/**
 * Final Classification Packet
 * 
 * This packet details the final classification at the end of the race, and the
 * data will match with the post race results screen. This is especially useful
 * for multiplayer games where it is not always possible to send lap times on
 * the final frame because of network delay.
 * Frequency: Once at the end of a race
 */
@Getter
public class PacketFinalClassificationData extends Packet {
    
    private short numCars;
    private FinalClassificationData[] finalClassificationData = new FinalClassificationData[PacketConstants.CARS];

    @Override
    public Packet fill(ByteBuf buffer) {
        this.numCars = buffer.readUnsignedByte();
        for (int i = 0; i < this.numCars; i++) {
            FinalClassificationData fcd = new FinalClassificationData();
            this.finalClassificationData[i] = fcd.fill(buffer);
        }
        return this;
    }

}
