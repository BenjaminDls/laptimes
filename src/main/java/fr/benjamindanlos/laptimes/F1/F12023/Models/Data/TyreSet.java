package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class TyreSet {
	private short actualTyreCompound; // Actual tyre compound used
	private short visualTyreCompound; // Visual tyre compound used
	private short wear; // Tyre wear (percentage)
	private short available; // Whether this set is currently available
	private short recommendedSession; // Recommended session for tyre set
	private short lifeSpan; // Laps left in this tyre set
	private short usableLife; // Max number of laps recommended for this compound
	private int lapDeltaTime; // Lap delta time in milliseconds compared to fitted set
	private short fitted; // Whether the set is fitted or not

	public TyreSet fill(ByteBuf buffer){
		this.actualTyreCompound = buffer.readUnsignedByte();
		this.visualTyreCompound = buffer.readUnsignedByte();
		this.wear = buffer.readUnsignedByte();
		this.available = buffer.readUnsignedByte();
		this.recommendedSession = buffer.readUnsignedByte();
		this.lifeSpan = buffer.readUnsignedByte();
		this.usableLife = buffer.readUnsignedByte();
		this.lapDeltaTime = buffer.readIntLE();
		this.fitted = buffer.readUnsignedByte();
		
		return this;
	}
}
