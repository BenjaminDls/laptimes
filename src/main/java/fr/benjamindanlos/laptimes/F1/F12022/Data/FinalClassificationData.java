package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.ResultStatus;
import lombok.Data;

import java.util.Arrays;

@Data
public class FinalClassificationData {

    public static final int SIZE = 37;

    private short position;
    private short numLaps;
    private short gridPosition;
    private short points;
    private short numPitStops;
    private ResultStatus resultStatus;
    private float bestLapTime;
    private double totalRaceTime;
    private short penaltiesTime;
    private short numPenalties;
    private short numTyreStints;
    private short tyreStintsActual[] = new short[PacketConstants.TYRE_STINTS];
    private short tyreStintsVisual[] = new short[PacketConstants.TYRE_STINTS];

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
        this.bestLapTime = buffer.readFloatLE();
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
        return this;
    }

    @Override
    public String toString() {
        return "FinalClassificationData[" +
                ",position=" + this.position +
                ",numLaps=" + this.numLaps +
                ",gridPosition=" + this.gridPosition +
                ",points=" + this.points +
                ",numPitStops=" + this.numPitStops +
                ",resultStatus=" + this.resultStatus +
                ",bestLapTime=" + this.bestLapTime +
                ",totalRaceTime=" + this.totalRaceTime +
                ",penaltiesTime=" + this.penaltiesTime +
                ",numPenalties=" + this.numPenalties +
                ",numTyreStints=" + this.numTyreStints +
                ",tyreStintsActual=" + Arrays.toString(tyreStintsActual) +
                ",tyreStintsVisual=" + Arrays.toString(this.tyreStintsVisual) +
                "]";
    }
}
