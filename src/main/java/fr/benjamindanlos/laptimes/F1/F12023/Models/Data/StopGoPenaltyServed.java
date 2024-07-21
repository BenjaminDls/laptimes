package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class StopGoPenaltyServed {
	private short vehicleIdx;

	public StopGoPenaltyServed fill(ByteBuf buffer){
		this.vehicleIdx = buffer.readUnsignedByte();
		return this;
	}
}
