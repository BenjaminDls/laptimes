package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class TyreStintHistory {
	private short endLap; // Lap the tyre usage ends on (255 of current tyre)
	private short tyreActualCompound; // Actual tyres used by this driver
	private short tyreVisualCompound; // Visual tyres used by this driver

	public TyreStintHistory fill(ByteBuf buffer){
		this.endLap = buffer.readUnsignedByte();
		this.tyreActualCompound = buffer.readUnsignedByte();
		this.tyreVisualCompound = buffer.readUnsignedByte();

		return this;
	}
}
