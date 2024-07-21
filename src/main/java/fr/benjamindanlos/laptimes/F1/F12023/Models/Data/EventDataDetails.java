package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDataDetails {

    private FastestLap fastestLap;
    private Retirement retirement;
    private TeamMateInPits teamMateInPits;
    private RaceWinner raceWinner;
    private Penalty penalty;
    private SpeedTrap speedTrap;
	private StartLights startLights;
	private DriveThroughPenaltyServed driverThroughPenaltyServed;
	private StopGoPenaltyServed stopGoPenaltyServed;
	private Flashback flashback;
	private Buttons buttons;
	private Overtake overtake;

}


