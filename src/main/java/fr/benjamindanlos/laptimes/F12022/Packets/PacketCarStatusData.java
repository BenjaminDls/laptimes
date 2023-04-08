/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F12022.Data.CarStatusData;
import lombok.Data;

/**
 * Car Status Packet
 * 
 * This packet details car statuses for all the cars in the race. It includes
 * values such as the damage readings on the car.
 * Frequency: Rate as specified in menus
 */
@Data
public class PacketCarStatusData extends Packet {

    // 1344
    public static final int SIZE = PacketHeader.SIZE
                                    + CarStatusData.SIZE * PacketConstants.CARS;
    
    private List<CarStatusData> carStatusData = new ArrayList<>(PacketConstants.CARS);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CarStatus[");
        sb.append(super.toString());
        sb.append(",carStatusData=");
        for (CarStatusData c : carStatusData) {
            sb.append(c.toString() + ",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarStatusData csd = new CarStatusData();
            this.carStatusData.add(csd.fill(buffer));
        }
        return this;
    }

    @Override
    public ByteBuf fillBuffer(ByteBuf buffer) {
        super.fillBuffer(buffer);
        for (CarStatusData csd : this.carStatusData) {
            csd.fillBuffer(buffer);
        }
        return buffer;
    }
}
