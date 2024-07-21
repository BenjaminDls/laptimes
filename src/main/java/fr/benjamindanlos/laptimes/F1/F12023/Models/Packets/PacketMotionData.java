package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.CarMotionData;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.ExtraCarMotionData;
import lombok.Getter;

/**
 * Motion Packet
 * 
 * The motion packet gives physics data for all the cars being driven. 
 * There is additional data for the car being driven with the goal of being able to drive a motion platform setup.
 * Frequency: Rate as specified in menus
 */
@Getter
public class PacketMotionData extends Packet {

	private CarMotionData[] carMotionData = new CarMotionData[PacketConstants.CARS];
	private ExtraCarMotionData extraCarMotionData = new ExtraCarMotionData();

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarMotionData cmd = new CarMotionData();
            this.carMotionData[i] = cmd.fill(buffer);
        }
        this.extraCarMotionData.fill(buffer);
        return this;
    }

}
