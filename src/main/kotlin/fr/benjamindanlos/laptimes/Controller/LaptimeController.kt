package fr.benjamindanlos.laptimes.Controller

import fr.benjamindanlos.laptimes.Entities.Laptime
import fr.benjamindanlos.laptimes.Repository.LaptimeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
@RequestMapping("/laptimes")
class LaptimeController (
	@Autowired
	internal val laptimeRepository: LaptimeRepository
){
	@GetMapping("/bestByDriver/{driverName}")
	fun getAllBestLaptimesByDriver(@PathVariable("driverName") driverName: String)
	: ResponseEntity<List<Laptime>>
	{
		val results: List<Laptime> = laptimeRepository.findAllBestByDriver(driverName)
		return ResponseEntity.ok().body(results)
	}
}
