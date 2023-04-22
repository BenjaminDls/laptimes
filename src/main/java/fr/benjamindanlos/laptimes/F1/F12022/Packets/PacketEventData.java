package fr.benjamindanlos.laptimes.F1.F12022.Packets;

import fr.benjamindanlos.laptimes.F1.F12022.Data.*;
import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketUtils;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.EventCode;
import lombok.Data;

/**
 * Event Packet
 * 
 * This packet gives details of events that happen during the course of a session.
 * Frequency: When the event occurs
 */
@Data
public class PacketEventData extends Packet {

    // 35
    public static final int SIZE = PacketHeader.SIZE + 
                                    4 + 
                                    EventDataDetails.SIZE;

    private EventCode eventCode;

	private EventDataDetails eventDataDetails = new EventDataDetails();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Event[");
        sb.append(super.toString());
        sb.append(",eventStringCode=" +  this.eventCode);
        sb.append(",eventDataDetails=" + this.eventDataDetails);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        this.eventCode = EventCode.valueFrom(PacketUtils.readString(buffer, 4));
		if(eventCode!=null){
			switch (this.eventCode) {
				case SESSION_STARTED:
					break;
				case SESSION_ENDED:
					break;
				case FASTEST_LAP:
					FastestLap fl = new FastestLap();
					this.eventDataDetails.setFastestLap(fl.fill(buffer));
					break;
				case RETIREMENT:
					Retirement r = new Retirement();
					this.eventDataDetails.setRetirement(r.fill(buffer));
					break;
				case DRS_ENABLED:
					break;
				case DRS_DISABLED:
					break;
				case TEAM_MATE_IN_PITS:
					TeamMateInPits tmip = new TeamMateInPits();
					this.eventDataDetails.setTeamMateInPits(tmip.fill(buffer));
					break;
				case CHEQUERED_FLAG:
					break;
				case RACE_WINNER:
					RaceWinner rw = new RaceWinner();
					this.eventDataDetails.setRaceWinner(rw.fill(buffer));
					break;
				case PENALTY_ISSUED:
					Penalty p = new Penalty();
					this.eventDataDetails.setPenalty(p.fill(buffer));
					break;
				case SPEED_TRAP_TRIGGERED:
					SpeedTrap st = new SpeedTrap();
					this.eventDataDetails.setSpeedTrap(st.fill(buffer));
					break;
				case START_LIGHTS:
					StartLights stLights = new StartLights();
					this.eventDataDetails.setStartLights(stLights.fill(buffer));
					break;
				case LIGHTS_OUT:
					StartLights lightsOut = new StartLights();
					lightsOut.setNumLights((short) 0);
					this.eventDataDetails.setStartLights(lightsOut);
					break;
				case DRIVE_THROUGH_SERVED:
					DriverThroughPenaltyServed dtsv = new DriverThroughPenaltyServed();
					this.eventDataDetails.setDriverThroughPenaltyServed(dtsv.fill(buffer));
					break;
				case STOP_GO_SERVED:
					StopGoPenaltyServed sgsv = new StopGoPenaltyServed();
					this.eventDataDetails.setStopGoPenaltyServed(sgsv.fill(buffer));
					break;
				case FLASHBACK:
					Flashback flashback = new Flashback();
					this.eventDataDetails.setFlashback(flashback.fill(buffer));
					break;
				case BUTTONS_STATUS:
					Buttons buttons = new Buttons();
					this.eventDataDetails.setButtons(buttons.fill(buffer));
					break;
				default:
					throw new IllegalArgumentException("EventCode=" + this.eventCode + " not supported");
			}
		}
        return this;
    }

}
