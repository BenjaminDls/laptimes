package fr.benjamindanlos.laptimes.Repository;

import fr.benjamindanlos.laptimes.Entities.Laptime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LaptimeRepository extends JpaRepository<Laptime, Integer>{

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

}
