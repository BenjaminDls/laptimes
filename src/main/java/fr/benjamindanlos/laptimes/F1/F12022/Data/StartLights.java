package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
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
