package fr.benjamindanlos.laptimes.Controller;

import fr.benjamindanlos.laptimes.DTO.SearchDTO;
import fr.benjamindanlos.laptimes.Entities.Laptime;
import fr.benjamindanlos.laptimes.Exceptions.BadRequestException;
import fr.benjamindanlos.laptimes.Exceptions.NotFoundException;
import fr.benjamindanlos.laptimes.Repository.LaptimeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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

	@Operation(description = "Search laptimes from the name of a driver",
		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(
				contentSchema = @Schema(
					implementation = SearchDTO.class
				)
			)
		),
		responses = {
			@ApiResponse(responseCode = "200", 
				description = "Matching laptimes",
				content = @Content(
					array = @ArraySchema(
						contains = @Schema(
							implementation = Laptime.class
						)
					)
				)
			),
			@ApiResponse(responseCode = "400",
				description = "Invalid request"
			)
		}
	)
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

	@Operation(description = "Search laptimes from the name of a track",
		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(
				contentSchema = @Schema(
					implementation = SearchDTO.class
				)
			)
		),
		responses = {
			@ApiResponse(responseCode = "200",
				description = "Matching laptimes",
				content = @Content(
					array = @ArraySchema(
						contains = @Schema(
							implementation = Laptime.class
						)
					)
				)
			),
			@ApiResponse(responseCode = "400",
				description = "Invalid request"
			)
		}
	)
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

	@Operation(description = "Import laptimes",
		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(
				array = @ArraySchema(
					schema = @Schema(
						implementation = Laptime.class
					)
				)
			)
		),
		responses = {
			@ApiResponse(responseCode = "200",
				description = "Operation Success"
			),
			@ApiResponse(responseCode = "400",
				description = "Invalid request"
			)
		}
	)
	@PostMapping("/import")
	public void importLaptimes(@RequestBody List<Laptime> laptimes)
	{
		if(laptimes==null){
			throw new IllegalArgumentException("Null body");
		}
		laptimeRepository.saveAll(laptimes);
	}
}
