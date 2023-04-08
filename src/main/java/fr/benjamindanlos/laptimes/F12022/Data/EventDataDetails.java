/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Data;

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

    @Override
    public String toString() {
        return "EventDataDetails[fastestLap=" + this.fastestLap +
                ",retirement=" + this.retirement +
                ",teamMateInPits=" + this.teamMateInPits +
                ",raceWinner=" + this.raceWinner +
                ",penalty=" + this.penalty +
                ",speedTrap=" + this.speedTrap +
                "]";
    }
}


