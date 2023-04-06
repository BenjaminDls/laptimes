package fr.benjamindanlos.laptimes.DTO

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data

@Data
class SearchDTO (

	@JsonProperty(value = "track", required = false)
	val track: String?,

	@JsonProperty(value = "game", required = false)
	val game: String?,

	@JsonProperty(value = "car", required = false)
	val car: String?,

	@JsonProperty(value = "driver", required = false)
	val driver: String?,

	@JsonProperty(value = "searchBest", required = false)
	val searchBest: Boolean?

)
