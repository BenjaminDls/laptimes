package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.CarStatusData;
import lombok.Getter;

/**
 * Car Status Packet
 * 
 * This packet details car statuses for all the cars in the race. It includes
 * values such as the damage readings on the car.
 * Frequency: Rate as specified in menus
 */
@Getter
public class PacketCarStatusData extends Packet {
    
    private CarStatusData[] carStatusData = new CarStatusData[PacketConstants.CARS];


    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarStatusData csd = new CarStatusData();
            this.carStatusData[i] = csd.fill(buffer);
        }
        return this;
    }

}
