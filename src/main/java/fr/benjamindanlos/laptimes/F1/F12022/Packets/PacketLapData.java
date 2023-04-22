package fr.benjamindanlos.laptimes.F1.F12022.Packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12022.Data.LapData;
import lombok.Data;

/**
 * Lap Data Packet
 * 
 * The lap data packet gives details of all the cars in the session.
 * Frequency: Rate as specified in menus
 */
@Data
public class PacketLapData extends Packet {

    public static final int SIZE = PacketHeader.SIZE +
                                    LapData.SIZE * PacketConstants.CARS;

    private List<LapData> lapData = new ArrayList<>(PacketConstants.CARS);
    private short timeTrialPBCarIndex;
    private short timeTrialRivalCarIndex;

    @Override
    public Packet fill(ByteBuf buffer) {
        for (int i = 0; i < PacketConstants.CARS; i++) {
            LapData ld = new LapData();
            this.lapData.add(ld.fill(buffer));
        }
        this.timeTrialPBCarIndex = buffer.readUnsignedByte();
        this.timeTrialRivalCarIndex = buffer.readUnsignedByte();
        return this;
    }

    @Override
    public String toString() {
        return "PacketLapData{" +
                "lapData=" + lapData +
                ", timeTrialPBCarIndex=" + timeTrialPBCarIndex +
                ", timeTrialRivalCarIndex=" + timeTrialRivalCarIndex +
                '}';
    }
}
