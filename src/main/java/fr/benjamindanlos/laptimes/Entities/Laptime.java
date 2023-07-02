package fr.benjamindanlos.laptimes.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "laptime")
public class Laptime {

	@Id
	@JsonIgnore
	@Column(nullable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "driver", nullable = false)
	private String driver;

	@Column(name = "game", nullable = false)
	private String game;

	@Column(name = "car", nullable = false)
	private String car;

	@Column(name = "track", nullable = false)
	private String track;

	@Column(nullable = false, name = "laptime")
	private int laptime;

	@Column(nullable = false, name = "laptimeString")
	private String laptimeString;

	@Column(nullable = false, name = "carNumber")
	private String carNumber;

	@CreatedDate
	@Column(nullable = false, name = "date")
	private LocalDateTime date;

	@Override
	public String toString() {
		return "Laptime{" +
				"driver='" + driver + '\'' +
				", game='" + game + '\'' +
				", car='" + car + '\'' +
				", track='" + track + '\'' +
				", laptime=" + laptime +
				", laptimeString='" + laptimeString + '\'' +
				", carNumber='" + carNumber + '\'' +
				", date=" + date +
				'}';
	}

	public String toDiscordString() {
		return driver + " did " + laptimeString + " at " + track + " driving " + car + " on " + game;
	}
}
