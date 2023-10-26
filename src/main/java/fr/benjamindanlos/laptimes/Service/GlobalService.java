package fr.benjamindanlos.laptimes.Service;

import fr.benjamindanlos.laptimes.Entities.Laptime;
import fr.benjamindanlos.laptimes.Repository.LaptimeRepository;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GlobalService {
	@Autowired
	private LaptimeRepository laptimeRepository;

	/**
	 * Search for the best laptime
	 * @param driverName driver to search
	 * @param trackName track to search
	 * @param carName car to search
	 * @param gameName game to search
	 * @return the best laptime matching the criteria
	 */
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

	/**
	 * lists the best laptimes on a track of a game
	 * @param trackName track name
	 * @param gameName game name
	 * @return list of best laptimes
	 */
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

	/**
	 * lists the best laptimes on a track of a game limited to one car
	 * @param trackName track name
	 * @param gameName game name
	 * @param carName car name
	 * @return list of best laptimes with the car
	 */
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

	/**
	 * get all the best laptimes of a session (=day)
	 * @param day day of the session
	 * @return list of best laptimes for this day ordered
	 */
	public List<Laptime> sessionBests(LocalDate day){
		try{
			log.debug("Getting "+day+"'s session bests");
			List<Laptime> times = laptimeRepository.findAllBestByDayGroupByTrackAndDriver(day);
			log.debug("Found "+times.size()+" entries");
			return times;
		}
		catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * get all laptimes of a day
	 * @param day day
	 * @return list of laptimes ordered
	 */
	public List<Laptime> allLaptimesOfSession(LocalDate day){
		try{
			log.debug("Getting all times of session :"+day);
			List<Laptime> times = laptimeRepository.findAllBySession(day);
			log.debug("Found "+times.size()+" entries");
			return times;
		}
		catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public List<String> knownGames(String nameLike){
		List<String> games = new ArrayList<>();
		if(StringUtils.isEmpty(nameLike)){
			games.addAll(laptimeRepository.gamesList());
		}
		else{
			games.addAll(laptimeRepository.gamesLike(nameLike));
		}
		return games;
	}

	public List<String> knownTracks(String nameLike){
		List<String> tracks = new ArrayList<>();
		if(StringUtils.isEmpty(nameLike)){
			tracks.addAll(laptimeRepository.tracksList());
		}
		else{
			tracks.addAll(laptimeRepository.tracksLike(nameLike));
		}
		return tracks;
	}

	public List<String> knownCars(String nameLike){
		List<String> cars = new ArrayList<>();
		if(StringUtils.isEmpty(nameLike)){
			cars.addAll(laptimeRepository.carsList());
		}
		else{
			cars.addAll(laptimeRepository.carsLike(nameLike));
		}
		return cars;
	}

	public List<String> knownDrivers(String nameLike){
		List<String> drivers = new ArrayList<>();
		if(StringUtils.isEmpty(nameLike)){
			drivers.addAll(laptimeRepository.driversList());
		}
		else{
			drivers.addAll(laptimeRepository.driversLike(nameLike));
		}
		return drivers;
	}
}
