package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class DriveThroughPenaltyServed {
	private short vehicleIdx;

	public DriveThroughPenaltyServed fill(ByteBuf buffer){
		this.vehicleIdx = buffer.readUnsignedByte();
		return this;
	}
}
