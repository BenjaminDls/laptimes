package fr.benjamindanlos.laptimes.F1.F12022.Handler;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Getter
public class Handler {

	private final Map<BigInteger, Integer> playerCurrentLapNumber = new HashMap<>();
	private final Map<BigInteger, LocalDateTime> playerLastData = new HashMap<>();
	private final Map<BigInteger, String> playerNames = new HashMap<>();

}
