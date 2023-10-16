package fr.benjamindanlos.laptimes.Discord;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import discord4j.core.event.domain.interaction.ChatInputAutoCompleteEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.discordjson.json.ApplicationCommandOptionChoiceData;
import fr.benjamindanlos.laptimes.Service.GlobalService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AutoCompleteHandler {
    
	@Autowired
	private GlobalService service;

    public Mono<Void> handleAutoComplete(ChatInputAutoCompleteEvent event){
		String optionToHelpWith = event.getFocusedOption().getName();
		String userInput = event.getFocusedOption().getValue().map(ApplicationCommandInteractionOptionValue::asString).orElse("");
		
        log.debug("Autocomplete request : "+optionToHelpWith+" / "+userInput);

		List<ApplicationCommandOptionChoiceData> suggestions = new ArrayList<>();

        switch(optionToHelpWith.toLowerCase()){
            case "game":
                suggestions.addAll(this.fillGames(userInput));
                break;
            case "track":
                suggestions.addAll(this.fillTracks(userInput));
                break;
            case "car":
                suggestions.addAll(this.fillCars(userInput));
                break;
            case "driver":
                suggestions.addAll(this.fillDrivers(userInput));
                break;
            default:
                break;
        }

        log.debug("Autocomplete suggestions : "+suggestions.size());

		return event.respondWithSuggestions(suggestions);
	}

    private List<ApplicationCommandOptionChoiceData> fillGames(String userInput){
        List<ApplicationCommandOptionChoiceData> completions = new ArrayList<>();
        List<String> games = service.knownGames(userInput);
        games.forEach(game -> {
            ApplicationCommandOptionChoiceData completion = ApplicationCommandOptionChoiceData.builder()
                    .name(game)
                    .value(game)
                    .build();
            completions.add(completion);
        });
        return completions;
    }

    private List<ApplicationCommandOptionChoiceData> fillTracks(String userInput){
        List<ApplicationCommandOptionChoiceData> completions = new ArrayList<>();
        List<String> tracks = service.knownTracks(userInput);
        tracks.forEach(track -> {
            ApplicationCommandOptionChoiceData completion = ApplicationCommandOptionChoiceData.builder()
                    .name(track)
                    .value(track)
                    .build();
            completions.add(completion);
        });
        return completions;
    }

    private List<ApplicationCommandOptionChoiceData> fillCars(String userInput){
        List<ApplicationCommandOptionChoiceData> completions = new ArrayList<>();
        List<String> cars = service.knownCars(userInput);
        cars.forEach(car -> {
            ApplicationCommandOptionChoiceData completion = ApplicationCommandOptionChoiceData.builder()
                    .name(car)
                    .value(car)
                    .build();
            completions.add(completion);
        });
        return completions;
    }

    private List<ApplicationCommandOptionChoiceData> fillDrivers(String userInput){
        List<ApplicationCommandOptionChoiceData> completions = new ArrayList<>();
        List<String> drivers = service.knownDrivers(userInput);
        drivers.forEach(driver -> {
            ApplicationCommandOptionChoiceData completion = ApplicationCommandOptionChoiceData.builder()
                    .name(driver)
                    .value(driver)
                    .build();
            completions.add(completion);
        });
        return completions;
    }
}
