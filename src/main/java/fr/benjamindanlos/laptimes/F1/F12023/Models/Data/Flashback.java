package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class Flashback {
	private long flashbackFrameIdentifier;
	private float flashbackSessionTime;

	public Flashback fill(ByteBuf buffer){
		this.flashbackFrameIdentifier = buffer.readIntLE();
		this.flashbackSessionTime = buffer.readFloatLE();
		return this;
	}
}
