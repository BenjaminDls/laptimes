package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class Overtake {
	private short overtakingVehicleIdx;
	private short beingOvertakenVehicleIdx;

	public Overtake fill(ByteBuf buffer){
		this.overtakingVehicleIdx=buffer.readUnsignedByte();
		this.beingOvertakenVehicleIdx=buffer.readUnsignedByte();
		return this;
	}
}
