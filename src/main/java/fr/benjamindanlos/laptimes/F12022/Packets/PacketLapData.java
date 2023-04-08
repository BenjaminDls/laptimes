/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F12022.Data.LapData;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Lap Data Packet
 * 
 * The lap data packet gives details of all the cars in the session.
 * Frequency: Rate as specified in menus
 */
@Data
public class PacketLapData extends Packet {

    // 1190
    public static final int SIZE = PacketHeader.SIZE +
                                    LapData.SIZE * PacketConstants.CARS;

    private List<LapData> lapData = new ArrayList<>(PacketConstants.CARS);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LapData[");
        sb.append(super.toString());
        sb.append(",lapData=");
        for (LapData l : lapData) {
            sb.append(l.toString() + ",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        for (int i = 0; i < PacketConstants.CARS; i++) {
            LapData ld = new LapData();
            this.lapData.add(ld.fill(buffer));
        }
        return this;
    }

    @Override
    public ByteBuf fillBuffer(ByteBuf buffer) {
        super.fillBuffer(buffer);
        for (LapData ld : this.lapData) {
            ld.fillBuffer(buffer);
        }
        return buffer;
    }
}
