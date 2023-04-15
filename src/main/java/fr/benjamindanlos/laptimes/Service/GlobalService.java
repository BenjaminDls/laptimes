package fr.benjamindanlos.laptimes.Service;

import fr.benjamindanlos.laptimes.Entities.Laptime;
import fr.benjamindanlos.laptimes.Repository.LaptimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GlobalService {
	@Autowired
	private LaptimeRepository laptimeRepository;

	public Laptime search(String driverName, String trackName, String carName, String gameName){
		try {
			log.debug("Searching for "+driverName+"/"+trackName+"/"+carName+"/"+gameName);
			Laptime l = laptimeRepository.findLastByDriverAndGameAndTrackAndCar(driverName, gameName, trackName, carName);
			log.debug("Found ? %b".formatted(l!=null));
			return l;
		}
		catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public List<Laptime> generateLeaderBoard(String trackName, String gameName){
		try{
			log.debug("Generating leaderboard for "+trackName+"/"+gameName);
			List<Laptime> laptimes = laptimeRepository.findAllBestByTrackAndGame(trackName, gameName);
			log.debug("Found "+laptimes.size()+" entries");
			return laptimes;
		}
		catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public List<Laptime> generateLeaderBoardByCar(String trackName, String gameName, String carName){
		try{
			log.debug("Generating leaderboard for "+trackName+"/"+gameName);
			List<Laptime> laptimes = laptimeRepository.findAllBestByTrackAndGameAndCar(trackName, gameName, carName);
			log.debug("Found "+laptimes.size()+" entries");
			return laptimes;
		}
		catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
