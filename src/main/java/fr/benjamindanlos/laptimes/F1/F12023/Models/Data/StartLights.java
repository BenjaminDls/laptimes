package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartLights {

	private short numLights;
	/**
	 * Fill the current StartLights with the raw bytes representation
	 *
	 * @param buffer buffer with the raw bytes representation
	 * @return current filled StartLights instance
	 */
	public StartLights fill(ByteBuf buffer) {
		this.numLights = buffer.readUnsignedByte();
		return this;
	}
}
