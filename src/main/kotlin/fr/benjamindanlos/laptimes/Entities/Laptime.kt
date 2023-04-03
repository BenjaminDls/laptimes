package fr.benjamindanlos.laptimes.Entities

import jakarta.persistence.*
import lombok.Data
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Data
@Entity
@Table(name = "laptime")
class Laptime(

	@Id
	@Column(nullable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	val id: Int,

	@Column(name = "driver", nullable = false)
	val driver: String,

	@Column(name = "game", nullable = false)
	val game: String,

	@Column(name = "car", nullable = false)
	val car: String,

	@Column(name = "track", nullable = false)
	val track: String,

	@Column(nullable = false, name = "laptime")
	val laptime: Int,

	@Column(nullable = false, name = "laptimeString")
	val laptimeString: String,

	@CreatedDate
	@Column(nullable = false, name = "date")
	val date: LocalDateTime

)
