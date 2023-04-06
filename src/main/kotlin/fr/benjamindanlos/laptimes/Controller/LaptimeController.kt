package fr.benjamindanlos.laptimes.Controller

import fr.benjamindanlos.laptimes.DTO.SearchDTO
import fr.benjamindanlos.laptimes.Entities.Laptime
import fr.benjamindanlos.laptimes.Exceptions.BadRequestException
import fr.benjamindanlos.laptimes.Exceptions.NotFoundException
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
	@PostMapping("/bestByDriver")
	fun getAllBestLaptimesByDriver(@RequestBody body: SearchDTO?)
	: ResponseEntity<List<Laptime>>
	{
		if(body==null || body.driver.isNullOrEmpty()){
			throw BadRequestException("no driver provided")
		}
		val results: List<Laptime> = laptimeRepository.findAllBestByDriver(body.driver)
		if(results.isEmpty()){
			throw NotFoundException("No entries found")
		}
		return ResponseEntity.ok().body(results)
	}

	@PostMapping("/bestByTrack")
	fun getAllBestLaptimesByTrack(@RequestBody body: SearchDTO?)
	: ResponseEntity<List<Laptime>>
	{
		if(body==null || body.track.isNullOrEmpty()){
			throw BadRequestException("no track provided")
		}
		val results: List<Laptime> = laptimeRepository.findAllBestByTrack(body.track)
		if(results.isEmpty()){
			throw NotFoundException("No entries found")
		}
		return ResponseEntity.ok().body(results)
	}
}
