package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class Buttons {
	private long buttonStatus;

	public Buttons fill(ByteBuf buffer){
		this.buttonStatus=buffer.readIntLE();
		return this;
	}
}
