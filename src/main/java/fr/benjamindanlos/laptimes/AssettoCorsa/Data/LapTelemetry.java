package fr.benjamindanlos.laptimes.AssettoCorsa.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;

import java.io.Serializable;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LapTelemetry extends Packet implements Serializable {
	private int carIdentifierNumber;
	private int lap;
	private String driverName;
	private String carName;
	private int time;
}
