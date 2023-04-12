package fr.benjamindanlos.laptimes.Utils;

import org.apache.commons.lang3.StringUtils;

public class Tools {
	public static String laptimeToString(float v){
		String result = "";
		int seconds = (int)v;
		int milliseconds = (int)((v-seconds)*1000.0f);
		int minutes = seconds/60;
		seconds = seconds-minutes*60; //keep the rest of the %
		String sMin = StringUtils.leftPad(String.valueOf(minutes), 2, "0");
		String sSec = StringUtils.leftPad(String.valueOf(seconds), 2, "0");
		String sMSec = StringUtils.rightPad(String.valueOf(milliseconds), 3, "0");
		result = String.format("%s:%s.%s",sMin, sSec, sMSec);
		return result;
	}
}
