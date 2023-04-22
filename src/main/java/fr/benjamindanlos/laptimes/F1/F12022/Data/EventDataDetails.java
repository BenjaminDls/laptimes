package fr.benjamindanlos.laptimes.F1.F12022.Data;

import lombok.Data;

@Data
public class EventDataDetails {

    public static final int SIZE = 7;

    private FastestLap fastestLap;
    private Retirement retirement;
    private TeamMateInPits teamMateInPits;
    private RaceWinner raceWinner;
    private Penalty penalty;
    private SpeedTrap speedTrap;
	private StartLights startLights;
	private DriverThroughPenaltyServed driverThroughPenaltyServed;
	private StopGoPenaltyServed stopGoPenaltyServed;
	private Flashback flashback;
	private Buttons buttons;

}


