package fr.benjamindanlos.laptimes.Utils;

import org.apache.commons.lang3.StringUtils;

public class Tools {
	// expected format:
	// 1min23s456ms -> 83456
	// 0min23s456ms -> 23456
	// 0m1s234ms 	-> 1234
	// 0m1s200ms	-> 1200
	// 0m10s345ms	-> 10345
	// 0m10s000ms	-> 10000
	// 0m0s123ms	-> 123
	// 0m0s100ms	-> 100
	public static String laptimeToString(int v){
		String result = String.valueOf(v);
		if(result.length()<4) {
			// value is only 3 char : it's only milliseconds
			// fill missing 0s in the left
			result = StringUtils.leftPad(result, 4, "0");
		}
		else {
			result = StringUtils.leftPad(result, 4, "0");
		}
		// last 3 digits are milliseconds
		int milliseconds = Integer.parseInt(result.substring(result.length()-3));
		// time in seconds is from start to end-3
		int seconds = Integer.parseInt(result.substring(0, result.length()-3));
		// calculate the minutes and get the seconds from the remaining
		int minutes = seconds/60;
		seconds = seconds-minutes*60; // keep the rest of the division
		String sMin = StringUtils.leftPad(String.valueOf(minutes), 2, "0");
		String sSec = StringUtils.leftPad(String.valueOf(seconds), 2, "0");
		String sMSec = StringUtils.leftPad(String.valueOf(milliseconds), 3, "0");
		result = String.format("%s:%s.%s",sMin, sSec, sMSec);
		return result;
	}
}
