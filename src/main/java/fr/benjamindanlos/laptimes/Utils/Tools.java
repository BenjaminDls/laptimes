package fr.benjamindanlos.laptimes.Utils;

public class Tools {
	public static String laptimeToString(float v){
		String result = "";
		int seconds = (int)v;
		float miliseconds = v-seconds;
		int minutes = seconds%60;
		seconds = seconds-minutes*60; //keep the rest of the %
		result = String.format("%d:%d.f",minutes, seconds, miliseconds);
		return result;
	}
}
