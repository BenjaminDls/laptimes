package fr.benjamindanlos.laptimes.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.benjamindanlos.laptimes.Events.Watcher.PersonnalBestWatcher;
import fr.benjamindanlos.laptimes.Events.Watcher.SessionBestWatcher;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "laptime")
@EntityListeners(value = {PersonnalBestWatcher.class, SessionBestWatcher.class})
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
