package fr.benjamindanlos.laptimes.DTO

import lombok.AllArgsConstructor
import lombok.Data

@Data
@AllArgsConstructor
class ErrorDTO (
	val message: String,
	val code: Int
)
