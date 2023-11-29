package fr.benjamindanlos.laptimes.Repository;

import fr.benjamindanlos.laptimes.Entities.Laptime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface LaptimeRepository extends CrudRepository<Laptime, Integer>{

	@Query(value = "select l.* from laptime l  " +
			"join (select id " +
			"      from laptime l2 " +
			"      where l2.driver = :driver " +
			"        and l2.track = :track " +
			"      group by l2.laptime " +
			"      having l2.laptime = min(l2.laptime) " +
			") l2 on l.id=l2.id limit 1", nativeQuery = true)
	Laptime findBestByDriverAndTrack(@Param("driver") String driver, @Param("track") String track);

	@Query(value = "select l.* from laptime l " +
			"join (select id " +
			"      from laptime l2 " +
			"      where l2.driver = :driver " +
			"      group by l2.track " +
			"      having min(l2.laptime) " +
			") l2 on l.id=l2.id", nativeQuery = true)
	List<Laptime> findAllBestByDriver(@Param("driver") String driver);

	@Query(value = "select l.* from laptime l " +
			"join (select id " +
			"      from laptime l2 " +
			"      where l2.track = :track " +
			"      group by l2.driver " +
			"      having min(l2.laptime) " +
			") l2 on l.id=l2.id", nativeQuery = true)
	List<Laptime> findAllBestByTrack(@Param("track") String track);

	@Query(value = "select l.* from laptime l " +
			"join (select id " +
			"      from laptime l2 " +
			"      where l2.track = :track " +
			"      and l2.game = :game " +
			"      group by l2.driver " +
			"      having min(l2.laptime) " +
			") l2 on l.id=l2.id", nativeQuery = true)
	List<Laptime> findAllBestByTrackAndGame(@Param("track") String track, @Param("game") String game);

	@Query(value = "select l.* from laptime l " +
			"join (select id " +
			"      from laptime l2 " +
			"      where l2.track = :track " +
			"      and l2.game = :game " +
			"      and l2.car = :car " +
			"      group by l2.driver " +
			"      having min(l2.laptime) " +
			") l2 on l.id=l2.id", nativeQuery = true)
	List<Laptime> findAllBestByTrackAndGameAndCar(@Param("track") String track, @Param("game") String game, @Param("car") String car);

	@Query(value = "select l.* from laptime l " +
			"where driver=:driver " +
			"and game=:game " +
			"and track=:track " +
			"and car=:car " +
			"group by l.driver, l.game, l.track, l.car " +
			"having max(l.date) limit 1", nativeQuery = true)
	Laptime findLastByDriverAndGameAndTrackAndCar(@Param("driver") String driver,
											  @Param("game") String game,
											  @Param("track") String track,
											  @Param("car") String car);

	@Query(value = "select l.* " +
			"from laptime l " +
			"where date between :date and :date + interval '1' day " +
			"group by l.driver, l.track " +
			"having min(l.laptime) " +
			"order by game, track, laptime ", nativeQuery = true)
	List<Laptime> findAllBestByDayGroupByTrackAndDriver(@Param("date") LocalDate date);

	@Query(value = "select l.* " +
			"from laptime l " +
			"where date between :date and :date + interval '1' day " +
			"order by game, track, driver, car, laptime ", nativeQuery = true)
	List<Laptime> findAllBySession(@Param("date") LocalDate date);

	@Query(value = "select game from laptime group by game", nativeQuery = true)
	List<String> gamesList();

	@Query(value = "select game from laptime where game like CONCAT('%', :name, '%') group by game", nativeQuery = true)
	List<String> gamesLike(@Param("name") String name);

	@Query(value = "select track from laptime group by track", nativeQuery = true)
	List<String> tracksList();

	@Query(value = "select track from laptime where track like CONCAT('%', :name, '%') group by track", nativeQuery = true)
	List<String> tracksLike(@Param("name") String name);

	@Query(value = "select car from laptime group by car", nativeQuery = true)
	List<String> carsList();

	@Query(value = "select car from laptime where car like CONCAT('%', :name, '%') group by car", nativeQuery = true)
	List<String> carsLike(@Param("name") String name);

	@Query(value = "select driver from laptime group by driver", nativeQuery = true)
	List<String> driversList();

	@Query(value = "select driver from laptime where driver like CONCAT('%', :name, '%') group by driver", nativeQuery = true)
	List<String> driversLike(@Param("name") String name);

	@Query(value = "select l.* from laptime l " +
			"where driver=:driver " +
			"and game=:game " +
			"and track=:track " +
			"and car=:car " +
			"group by l.driver, l.game, l.track, l.car " +
			"having min(l.laptime) limit 1", nativeQuery = true)
	Laptime findPersonnalBestByDriverAndGameAndTrackAndCar(@Param("driver") String driver,
											  @Param("game") String game,
											  @Param("track") String track,
											  @Param("car") String car);

	@Query(value = "select l.* from laptime l " +
			"where game=:game " +
			"and track=:track " +
			"and car=:car " +
			"group by l.game, l.track, l.car " +
			"having min(l.laptime) limit 1", nativeQuery = true)
	Laptime findSessionBestByGameAndTrackAndCar(
											  @Param("game") String game,
											  @Param("track") String track,
											  @Param("car") String car);

	@Query(value = "delete from laptime where driver='driver';", nativeQuery = true)
	void deleteDev();
}
