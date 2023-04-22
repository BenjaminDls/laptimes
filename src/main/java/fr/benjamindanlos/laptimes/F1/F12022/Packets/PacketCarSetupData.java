package fr.benjamindanlos.laptimes.F1.F12022.Packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12022.Data.CarSetupData;
import lombok.Getter;
import lombok.Setter;

/**
 * Car Setups Packet
 * 
 * This packet details the car setups for each vehicle in the session. Note that
 * in multiplayer games, other player cars will appear as blank, you will only
 * be able to see your car setup and AI cars.
 * Frequency: 2 per second
 */
public class PacketCarSetupData extends Packet {

    // 1102
    public static final int SIZE = PacketHeader.SIZE +
                                    CarSetupData.SIZE * PacketConstants.CARS ;

	@Getter
	@Setter
    private List<CarSetupData> carSetupData = new ArrayList<>(PacketConstants.CARS);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CarSetup[");
        sb.append(super.toString());
        sb.append(",carSetupData=");
        for (CarSetupData c: carSetupData) {
            sb.append(c.toString() + ",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarSetupData csd = new CarSetupData();
            this.carSetupData.add(csd.fill(buffer));
        }
        return this;
    }

}
