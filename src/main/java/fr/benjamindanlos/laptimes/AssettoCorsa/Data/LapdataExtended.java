package fr.benjamindanlos.laptimes.AssettoCorsa.Data;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LapdataExtended extends Packet{
	private int carIdentifierNumber;
	private int lap;
	private String driverName;
	private String carName;
	private int time;
	private String trackName;

	public LapdataExtended(ByteBuf buffer){
		this.fill(buffer);
	}
	@Override
	public LapdataExtended fill(ByteBuf buffer) {
		this.trackName = buffer.readBytes(100).toString(StandardCharsets.UTF_16LE).split("%")[0];
		this.carIdentifierNumber = buffer.readIntLE();
		this.lap = buffer.readIntLE();
		this.driverName = buffer.readBytes(100).toString(StandardCharsets.UTF_16LE).split("%")[0];
		this.carName = buffer.readBytes(100).toString(StandardCharsets.UTF_16LE).split("%")[0];
		this.time = buffer.readIntLE();
		return this;
	}
}
