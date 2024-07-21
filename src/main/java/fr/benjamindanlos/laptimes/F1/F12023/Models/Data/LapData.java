package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.DriverStatus;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.PitStatus;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.ResultStatus;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Sector;
import lombok.Getter;

@Getter
public class LapData {

    private int lastLapTime;
    private int currentLapTime;
    private int sector1TimeInMS;
    private short sector1TimeInMin;
    private int sector2TimeInMS;
    private short sector2TimeInMin;
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
    private short totalWarnings;
	private short cornerCuttingWarnings;
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
        this.sector1TimeInMin = buffer.readUnsignedByte();
        this.sector2TimeInMS = buffer.readUnsignedShortLE();
        this.sector2TimeInMin = buffer.readUnsignedByte();
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
		this.totalWarnings = buffer.readUnsignedByte();
		this.cornerCuttingWarnings = buffer.readUnsignedByte();
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

}
