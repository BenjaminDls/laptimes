package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.LapData;
import lombok.Getter;

/**
 * Lap Data Packet
 * 
 * The lap data packet gives details of all the cars in the session.
 * Frequency: Rate as specified in menus
 */
@Getter
public class PacketLapData extends Packet {

    private LapData[] lapData = new LapData[PacketConstants.CARS];
    private short timeTrialPBCarIndex;
    private short timeTrialRivalCarIndex;

    @Override
    public Packet fill(ByteBuf buffer) {
        for (int i = 0; i < PacketConstants.CARS; i++) {
            LapData ld = new LapData();
            this.lapData[i] = ld.fill(buffer);
        }
        this.timeTrialPBCarIndex = buffer.readUnsignedByte();
        this.timeTrialRivalCarIndex = buffer.readUnsignedByte();
        return this;
    }

}
