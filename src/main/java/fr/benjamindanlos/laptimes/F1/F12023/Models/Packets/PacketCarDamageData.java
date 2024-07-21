package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.CarDamageData;
import io.netty.buffer.ByteBuf;

public class PacketCarDamageData extends Packet {

	private CarDamageData[] carDamageData = new CarDamageData[PacketConstants.CARS];

    @Override
    public Packet fill(ByteBuf buffer) {
		for (int i = 0; i < PacketConstants.CARS; i++) {
            CarDamageData data = new CarDamageData();
            this.carDamageData[i] = data.fill(buffer);
        }
		return this;
	}	
}
