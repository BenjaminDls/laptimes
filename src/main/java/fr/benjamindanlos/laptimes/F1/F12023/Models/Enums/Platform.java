package fr.benjamindanlos.laptimes.F1.F12023.Models.Enums;

import lombok.Getter;

/**
 * Platform the game is running on
 */
@Getter
public enum Platform {
    STEAM(1),
	PLAYSTATION(3),
	XBOX(4),
	ORIGIN(6),
	UNKNOWN(25);

    private int value;
    
    Platform(int value) {
        this.value = value;
    }

    public static Platform valueOf(int value) {
		for(Platform p : Platform.values()){
			if(p.value == value){
				return p;
			}
		}
		return null;
    }

}
