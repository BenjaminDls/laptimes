package fr.benjamindanlos.laptimes.F1.F12022.Enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Event code
 */
public enum EventCode {
    // Sent when the session starts
    SESSION_STARTED("SSTA"),
    // Sent when the session ends
    SESSION_ENDED("SEND"),
    // When a driver achieves the fastest lap
    FASTEST_LAP("FTLP"),
    // When a driver retires
    RETIREMENT("RTMT"),
    // Race control have enabled DRS
    DRS_ENABLED("DRSE"),
    // Race control have disabled DRS
    DRS_DISABLED("DRSD"),
    // Your team mate has entered the pits
    TEAM_MATE_IN_PITS("TMPT"),
    // The chequered flag has been waved
    CHEQUERED_FLAG("CHQF"),
    // The race winner is announced
    RACE_WINNER("RCWN"),
    // A penalty has been issued – details in event
    PENALTY_ISSUED("PENA"),
    // Speed trap has been triggered by fastest speed
    SPEED_TRAP_TRIGGERED("SPTP"),
	START_LIGHTS("STLG"),
	LIGHTS_OUT("LGOT"),
	DRIVE_THROUGH_SERVED("DTSV"),
	STOP_GO_SERVED("SGSV"),
	FLASHBACK("FLBK"),
	BUTTONS_STATUS("BUTN");


    private static Map<String, EventCode> map = new HashMap<>();

    static {
        for (EventCode eventCode : EventCode.values()) {
            map.put(eventCode.value, eventCode);
        }
    }

    private String value;
    
    EventCode(String value) {
        this.value = value;
    }
    
    public static EventCode valueFrom(String value) {
        return map.get(value);
    }

    public String getValue() {
        return value;
    }
}
