package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.ResultStatus;
import lombok.Getter;

@Getter
public class FinalClassificationData {

    private short position;
    private short numLaps;
    private short gridPosition;
    private short points;
    private short numPitStops;
    private ResultStatus resultStatus;
    private int bestLapTimeInMS;
    private double totalRaceTime;
    private short penaltiesTime;
    private short numPenalties;
    private short numTyreStints;
    private short tyreStintsActual[] = new short[PacketConstants.TYRE_STINTS];
    private short tyreStintsVisual[] = new short[PacketConstants.TYRE_STINTS];
    private short tyreStintsEndLaps[] = new short[PacketConstants.TYRE_STINTS];

    /**
     * Fill the current FinalClassificationData with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled FinalClassificationData instance
     */
    public FinalClassificationData fill(ByteBuf buffer) {
        this.position = buffer.readUnsignedByte();
        this.numLaps = buffer.readUnsignedByte();
        this.gridPosition = buffer.readUnsignedByte();
        this.points = buffer.readUnsignedByte();
        this.numPitStops = buffer.readUnsignedByte();
        this.resultStatus = ResultStatus.valueOf(buffer.readUnsignedByte());
        this.bestLapTimeInMS = buffer.readIntLE();
        this.totalRaceTime = buffer.readDoubleLE();
        this.penaltiesTime = buffer.readUnsignedByte();
        this.numPenalties = buffer.readUnsignedByte();
        this.numTyreStints = buffer.readUnsignedByte();
        for (int j = 0; j < this.numTyreStints; j++) {
            this.tyreStintsActual[j] = buffer.readUnsignedByte();
        }
        for (int j = 0; j < this.numTyreStints; j++) {
            this.tyreStintsVisual[j] = buffer.readUnsignedByte();
        }
        for (int j = 0; j < this.numTyreStints; j++) {
            this.tyreStintsEndLaps[j] = buffer.readUnsignedByte();
        }
        return this;
    }
	
}
