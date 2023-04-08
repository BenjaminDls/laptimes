package fr.benjamindanlos.laptimes.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {

	@JsonProperty(value = "track", required = false)
	private String track;

	@JsonProperty(value = "game", required = false)
	private String game;

	@JsonProperty(value = "car", required = false)
	private String car;

	@JsonProperty(value = "driver", required = false)
	private String driver;

	@JsonProperty(value = "searchBest", required = false)
	private Boolean searchBest;

}
