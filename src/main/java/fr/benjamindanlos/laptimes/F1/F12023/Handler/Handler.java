package fr.benjamindanlos.laptimes.F1.F12023.Handler;

import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Handler {

	protected final static Map<BigInteger, Integer> playerCurrentLapNumber = new HashMap<>();

	protected final static Map<BigInteger, LocalDateTime> playerLastData = new HashMap<>();

	protected final static Map<BigInteger, String> playerNames = new HashMap<>();

	protected final static Map<BigInteger, String> playerCars = new HashMap<>();

	protected final static Map<BigInteger, String> playerTracks = new HashMap<>();

	protected final static Map<BigInteger, Boolean> playerLapInvalid = new HashMap<>();

}
