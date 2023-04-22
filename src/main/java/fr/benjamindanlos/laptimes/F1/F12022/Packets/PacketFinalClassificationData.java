package fr.benjamindanlos.laptimes.F1.F12022.Packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12022.Data.FinalClassificationData;
import lombok.Data;

/**
 * Final Classification Packet
 * 
 * This packet details the final classification at the end of the race, and the
 * data will match with the post race results screen. This is especially useful
 * for multiplayer games where it is not always possible to send lap times on
 * the final frame because of network delay.
 * Frequency: Once at the end of a race
 */
@Data
public class PacketFinalClassificationData extends Packet {

    // 839
    public static final int SIZE = PacketHeader.SIZE +
                                    1 +
                                    FinalClassificationData.SIZE * PacketConstants.CARS;
    
    private short numCars;
    private List<FinalClassificationData> finalClassificationData = new ArrayList<>(PacketConstants.CARS);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FinalClassification[");
        sb.append(super.toString());
        sb.append("numCars=" + this.numCars);
        sb.append(",finalClassificationData=");
        for (FinalClassificationData f : finalClassificationData) {
            sb.append(f.toString() + ",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return  sb.toString();
    }

    @Override
    public Packet fill(ByteBuf buffer) {
        this.numCars = buffer.readUnsignedByte();
        for (int i = 0; i < this.numCars; i++) {
            FinalClassificationData fcd = new FinalClassificationData();
            this.finalClassificationData.add(fcd.fill(buffer));
        }
        return this;
    }

}
