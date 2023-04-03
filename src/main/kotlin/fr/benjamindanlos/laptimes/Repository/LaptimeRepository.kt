package fr.benjamindanlos.laptimes.Repository

import fr.benjamindanlos.laptimes.Entities.Laptime
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface LaptimeRepository: JpaRepository<Laptime, Int>{

	@Query("select l.* from laptime l  " +
			"join (select id " +
			"      from laptime l2 " +
			"      where l2.driver = :driver " +
			"        and l2.track = :track " +
			"      group by l2.laptime " +
			"      having l2.laptime = min(l2.laptime) " +
			") l2 on l.id=l2.id", nativeQuery = true)
	fun findBestByDriverAndTrack(@Param("driver") driver: String, @Param("track") track: String): Laptime

	@Query("select l.* from laptime l\n" +
			"join (select id\n" +
			"      from laptime l2\n" +
			"      where l2.driver = :driver\n" +
			"      group by l2.track\n" +
			"      having min(l2.laptime)\n" +
			") l2 on l.id=l2.id", nativeQuery = true)
	fun findAllBestByDriver(@Param("driver") driver: String): List<Laptime>

	@Query("select l.* from laptime l\n" +
			"join (select id\n" +
			"      from laptime l2\n" +
			"      where l2.track = :track\n" +
			"      group by l2.driver\n" +
			"      having min(l2.laptime)\n" +
			") l2 on l.id=l2.id", nativeQuery = true)
	fun findAllBestByTrack(@Param("track") track: String): List<Laptime>

}
