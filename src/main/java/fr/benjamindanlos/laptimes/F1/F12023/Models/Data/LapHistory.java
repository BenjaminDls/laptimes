package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class LapHistory {
	private int lapTimeInMS; // Lap time in milliseconds
	private short sector1TimeInMS; // Sector 1 time in milliseconds
	private short sector1TimeMinutes; // Sector 1 whole minute part
	private short sector2TimeInMS; // Sector 2 time in milliseconds
	private short sector2TimeMinutes; // Sector 2 whole minute part
	private short sector3TimeInMS; // Sector 3 time in milliseconds
	private short sector3TimeMinutes; // Sector 3 whole minute part
	private short lapValidBitFlags; // 0x01 bit set-lap valid, 0x02 bit set-sector 1 valid
	// 0x04 bit set-sector 2 valid, 0x08 bit set-sector 3 valid

	public LapHistory fill(ByteBuf buffer){
		this.lapTimeInMS = buffer.readIntLE();
		this.sector1TimeInMS = buffer.readUnsignedByte();
		this.sector1TimeMinutes = buffer.readUnsignedByte();
		this.sector2TimeInMS = buffer.readUnsignedByte();
		this.sector2TimeMinutes = buffer.readUnsignedByte();
		this.sector3TimeInMS = buffer.readUnsignedByte();
		this.sector3TimeMinutes = buffer.readUnsignedByte();
		this.lapValidBitFlags = buffer.readUnsignedByte();
		
		return this;
	}
}
