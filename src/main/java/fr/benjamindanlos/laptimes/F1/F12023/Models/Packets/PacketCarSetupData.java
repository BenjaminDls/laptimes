package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.CarSetupData;
import lombok.Getter;

/**
 * Car Setups Packet
 * 
 * This packet details the car setups for each vehicle in the session. Note that
 * in multiplayer games, other player cars will appear as blank, you will only
 * be able to see your car setup and AI cars.
 * Frequency: 2 per second
 */
@Getter
public class PacketCarSetupData extends Packet {

    private CarSetupData[] carSetupData = new CarSetupData[PacketConstants.CARS];

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarSetupData csd = new CarSetupData();
            this.carSetupData[i] = csd.fill(buffer);
        }
        return this;
    }

}
