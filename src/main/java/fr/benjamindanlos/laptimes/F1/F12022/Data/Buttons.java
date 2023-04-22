package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class Buttons {
	private long buttonStatus;

	public Buttons fill(ByteBuf buffer){
		this.buttonStatus=buffer.readIntLE();
		return this;
	}
}
