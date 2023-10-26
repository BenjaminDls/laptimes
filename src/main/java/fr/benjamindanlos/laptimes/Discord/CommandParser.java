package fr.benjamindanlos.laptimes.Discord;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CommandParser {

	private final Pattern commandRegex = Pattern.compile("^!|\\\\([a-zA-Z]+)", Pattern.CASE_INSENSITIVE);
	private final Pattern argsRegex = Pattern.compile("(([a-zA-Z]+):(\\w+))\\s*", Pattern.CASE_INSENSITIVE);

	public BotCommand decomposeCommand(String command){
		BotCommand parsedCommand = new BotCommand();
		Matcher commandMatcher = commandRegex.matcher(command);
		if(commandMatcher.find()){
			parsedCommand.setCommand(commandMatcher.group(1));//get command without the '!'
		}

		Matcher argsMatcher = argsRegex.matcher(command);
		parsedCommand.setArgs(new HashMap<>());
		//loop through all matches (each match has the groups)
		while(argsMatcher.find() && argsMatcher.groupCount()==3){
			//group 1 is key:value, group 2 is key, group 3 is value
			parsedCommand.getArgs().put(argsMatcher.group(2), argsMatcher.group(3));
		}
		return parsedCommand;
	}
}
