package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.DriverStatus;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.PitStatus;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.ResultStatus;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.Sector;
import lombok.Data;

@Data
public class LapData {

    public static final int SIZE = 53;

    private int lastLapTime;
    private int currentLapTime;
    private int sector1TimeInMS;
    private int sector2TimeInMS;
    private float lapDistance;
    private float totalDistance;
    private float safetyCarDelta;
    private short carPosition;
    private short currentLapNum;
    private PitStatus pitStatus;
    private short numPitstops;
    private Sector sector;
    private short currentLapInvalid;
    private short penalties;
    private short warnings;
    private short numUnservedDriveThroughPens;
    private short numUnservedStopGoPens;
    private short gridPosition;
    private DriverStatus driverStatus;
    private ResultStatus resultStatus;
	private short pitlaneTimerActive;
	private int pitlaneTimerInLaneInMs;
	private int pitstopTimerInMs;
	private short pitstopShouldServePen;

    /**
     * Fill the current LapData with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled LapData instance
     */
    public LapData fill(ByteBuf buffer) {
        this.lastLapTime = buffer.readIntLE();
        this.currentLapTime = buffer.readIntLE();
        this.sector1TimeInMS = buffer.readUnsignedShortLE();
        this.sector2TimeInMS = buffer.readUnsignedShortLE();
        this.lapDistance = buffer.readIntLE()/1000f;
        this.totalDistance = buffer.readIntLE()/1000f;
        this.safetyCarDelta = buffer.readIntLE()/1000f;
        this.carPosition = buffer.readUnsignedByte();
        this.currentLapNum = buffer.readUnsignedByte();
        this.pitStatus = PitStatus.valueOf(buffer.readUnsignedByte());
		this.numPitstops = buffer.readUnsignedByte();
        this.sector = Sector.valueOf(buffer.readUnsignedByte());
        this.currentLapInvalid = buffer.readUnsignedByte();
        this.penalties = buffer.readUnsignedByte();
		this.warnings = buffer.readUnsignedByte();
		this.numUnservedDriveThroughPens = buffer.readUnsignedByte();
		this.numUnservedStopGoPens = buffer.readUnsignedByte();
		this.gridPosition = buffer.readUnsignedByte();
        this.driverStatus = DriverStatus.valueOf(buffer.readUnsignedByte());
        this.resultStatus = ResultStatus.valueOf(buffer.readUnsignedByte());
		this.pitlaneTimerActive = buffer.readUnsignedByte();
		this.pitlaneTimerInLaneInMs = buffer.readUnsignedShortLE();
		this.pitstopTimerInMs = buffer.readUnsignedShortLE();
		this.pitstopShouldServePen = buffer.readUnsignedByte();
        return this;
    }

	@Override
	public String toString() {
		return "LapData{" +
				"lastLapTime=" + lastLapTime +
				", currentLapTime=" + currentLapTime +
				", sector1TimeInMS=" + sector1TimeInMS +
				", sector2TimeInMS=" + sector2TimeInMS +
				", lapDistance=" + lapDistance +
				", totalDistance=" + totalDistance +
				", safetyCarDelta=" + safetyCarDelta +
				", carPosition=" + carPosition +
				", currentLapNum=" + currentLapNum +
				", pitStatus=" + pitStatus +
				", numPitstops=" + numPitstops +
				", sector=" + sector +
				", currentLapInvalid=" + currentLapInvalid +
				", penalties=" + penalties +
				", warnings=" + warnings +
				", numUnservedDriveThroughPens=" + numUnservedDriveThroughPens +
				", numUnservedStopGoPens=" + numUnservedStopGoPens +
				", gridPosition=" + gridPosition +
				", driverStatus=" + driverStatus +
				", resultStatus=" + resultStatus +
				", pitlaneTimerActive=" + pitlaneTimerActive +
				", pitlaneTimerInLaneInMs=" + pitlaneTimerInLaneInMs +
				", pitstopTimerInMs=" + pitstopTimerInMs +
				", pitstopShouldServePen=" + pitstopShouldServePen +
				'}';
	}
}
