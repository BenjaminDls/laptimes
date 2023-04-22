package fr.benjamindanlos.laptimes.F1.F12022.Handler;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class Handler {

	@Getter
	protected final static Map<BigInteger, Integer> playerCurrentLapNumber = new HashMap<>();

	@Getter
	protected final static Map<BigInteger, LocalDateTime> playerLastData = new HashMap<>();

	@Getter
	protected final static Map<BigInteger, String> playerNames = new HashMap<>();

	@Getter
	protected final static Map<BigInteger, String> playerCars = new HashMap<>();

	@Getter
	protected final static Map<BigInteger, String> playerTracks = new HashMap<>();

	@Getter
	protected final static Map<BigInteger, Boolean> playerLapInvalid = new HashMap<>();

}
