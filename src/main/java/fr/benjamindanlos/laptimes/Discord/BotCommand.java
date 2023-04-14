package fr.benjamindanlos.laptimes.Discord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotCommand {
	private String command;
	private Map<String, String> args;
}
