package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class Flashback {
	private long flashbackFrameIdentifier;
	private float flashbackSessionTime;

	public Flashback fill(ByteBuf buffer){
		this.flashbackFrameIdentifier = buffer.readIntLE();
		this.flashbackSessionTime = buffer.readFloatLE();
		return this;
	}
}
