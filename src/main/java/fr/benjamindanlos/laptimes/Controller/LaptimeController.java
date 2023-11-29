package fr.benjamindanlos.laptimes.Controller;

import fr.benjamindanlos.laptimes.DTO.SearchDTO;
import fr.benjamindanlos.laptimes.Entities.Laptime;
import fr.benjamindanlos.laptimes.Exceptions.BadRequestException;
import fr.benjamindanlos.laptimes.Exceptions.NotFoundException;
import fr.benjamindanlos.laptimes.Repository.LaptimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Controller
@RequestMapping("/laptimes")
public class LaptimeController
{
	@Autowired
	private LaptimeRepository laptimeRepository;

	@PostMapping("/bestByDriver")
	public ResponseEntity<List<Laptime>> getAllBestLaptimesByDriver(@RequestBody SearchDTO body)
	{
		if(body==null || body.getDriver() == null || body.getDriver().isEmpty()){
			throw new BadRequestException("no driver provided");
		}
		List<Laptime> results = laptimeRepository.findAllBestByDriver(body.getDriver());
		if(results.isEmpty()){
			throw new NotFoundException("No entries found");
		}
		return ResponseEntity.ok().body(results);
	}

	@PostMapping("/bestByTrack")
	public ResponseEntity<List<Laptime>> getAllBestLaptimesByTrack(@RequestBody SearchDTO body)
	{
		if(body==null || body.getTrack() == null || body.getTrack().isEmpty()){
			throw new BadRequestException("no track provided");
		}
		List<Laptime> results = laptimeRepository.findAllBestByTrack(body.getTrack());
		if(results.isEmpty()){
			throw new NotFoundException("No entries found");
		}
		return ResponseEntity.ok().body(results);
	}
}
