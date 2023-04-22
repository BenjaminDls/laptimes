package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class StopGoPenaltyServed {
	private short vehicleIdx;

	public StopGoPenaltyServed fill(ByteBuf buffer){
		this.vehicleIdx = buffer.readUnsignedByte();
		return this;
	}
}
