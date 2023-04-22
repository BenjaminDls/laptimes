package fr.benjamindanlos.laptimes.F1.F12022.Packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12022.Data.CarMotionData;
import fr.benjamindanlos.laptimes.F1.F12022.Data.ExtraCarMotionData;
import lombok.Data;

/**
 * Motion Packet
 * 
 * The motion packet gives physics data for all the cars being driven. 
 * There is additional data for the car being driven with the goal of being able to drive a motion platform setup.
 * Frequency: Rate as specified in menus
 */
@Data
public class PacketMotionData extends Packet {

    // 1464
    public static final int SIZE = PacketHeader.SIZE + 
                                    CarMotionData.SIZE * PacketConstants.CARS +
                                    ExtraCarMotionData.SIZE;

	private List<CarMotionData> carMotionData = new ArrayList<>(PacketConstants.CARS);
	private ExtraCarMotionData extraCarMotionData = new ExtraCarMotionData();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Motion[");
        sb.append(super.toString());
        sb.append(",carMotionData=");
        for (CarMotionData cmd : this.carMotionData) {
            sb.append(cmd.toString() + ",");
        }
        sb.append("extraCarMotionData=" + this.extraCarMotionData.toString());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarMotionData cmd = new CarMotionData();
            this.carMotionData.add(cmd.fill(buffer));
        }
        this.extraCarMotionData.fill(buffer);
        return this;
    }

}
