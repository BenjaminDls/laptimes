/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F12022.Enums.DriverStatus;
import fr.benjamindanlos.laptimes.F12022.Enums.PitStatus;
import fr.benjamindanlos.laptimes.F12022.Enums.ResultStatus;
import fr.benjamindanlos.laptimes.F12022.Enums.Sector;
import lombok.Data;

@Data
public class LapData {

    public static final int SIZE = 53;

    private float lastLapTime;
    private float currentLapTime;
    private int sector1TimeInMS;
    private int sector2TimeInMS;
    private float bestLapTime;
    private int bestLapNum;
    private int bestLapSector1TimeInMS;
    private int bestLapSector2TimeInMS;
    private int bestLapSector3TimeInMS;
    private int bestOverallSector1TimeInMS;
    private short bestOverallSector1LapNum;
    private int bestOverallSector2TimeInMS;
    private short bestOverallSector2LapNum;
    private int bestOverallSector3TimeInMS;
    private short bestOverallSector3LapNum;
    private float lapDistance;
    private float totalDistance;
    private float safetyCarDelta;
    private short carPosition;
    private short currentLapNum;
    private PitStatus pitStatus;
    private Sector sector;
    private short currentLapInvalid;
    private short penalties;
    private short gridPosition;
    private DriverStatus driverStatus;
    private ResultStatus resultStatus;

    /**
     * Fill the current LapData with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled LapData instance
     */
    public LapData fill(ByteBuf buffer) {
        this.lastLapTime = buffer.readFloatLE();
        this.currentLapTime = buffer.readFloatLE();
        this.sector1TimeInMS = buffer.readUnsignedShortLE();
        this.sector2TimeInMS = buffer.readUnsignedShortLE();
        this.bestLapTime = buffer.readFloatLE();
        this.bestLapNum = buffer.readUnsignedByte();
        this.bestLapSector1TimeInMS = buffer.readUnsignedShortLE();
        this.bestLapSector2TimeInMS = buffer.readUnsignedShortLE();
        this.bestLapSector3TimeInMS = buffer.readUnsignedShortLE();
        this.bestOverallSector1TimeInMS = buffer.readUnsignedShortLE();
        this.bestOverallSector1LapNum = buffer.readUnsignedByte();
        this.bestOverallSector2TimeInMS = buffer.readUnsignedShortLE();
        this.bestOverallSector2LapNum = buffer.readUnsignedByte();
        this.bestOverallSector3TimeInMS = buffer.readUnsignedShortLE();
        this.bestOverallSector3LapNum = buffer.readUnsignedByte();
        this.lapDistance = buffer.readFloatLE();
        this.totalDistance = buffer.readFloatLE();
        this.safetyCarDelta = buffer.readFloatLE();
        this.carPosition = buffer.readUnsignedByte();
        this.currentLapNum = buffer.readUnsignedByte();
        this.pitStatus = PitStatus.valueOf(buffer.readUnsignedByte());
        this.sector = Sector.valueOf(buffer.readUnsignedByte());
        this.currentLapInvalid = buffer.readUnsignedByte();
        this.penalties = buffer.readUnsignedByte();
        this.gridPosition = buffer.readUnsignedByte();
        this.driverStatus = DriverStatus.valueOf(buffer.readUnsignedByte());
        this.resultStatus = ResultStatus.valueOf(buffer.readUnsignedByte());
        return this;
    }

    /**
     * Fill the buffer with the raw bytes representation of the current LapData instance
     *
     * @param buffer buffer to fill
     * @return filled buffer
     */
    public ByteBuf fillBuffer(ByteBuf buffer) {
        buffer.writeFloatLE(this.lastLapTime);
        buffer.writeFloatLE(this.currentLapTime);
        buffer.writeShortLE(this.sector1TimeInMS);
        buffer.writeShortLE(this.sector2TimeInMS);
        buffer.writeFloatLE(this.bestLapTime);
        buffer.writeByte(this.bestLapNum);
        buffer.writeShortLE(this.bestLapSector1TimeInMS);
        buffer.writeShortLE(this.bestLapSector2TimeInMS);
        buffer.writeShortLE(this.bestLapSector3TimeInMS);
        buffer.writeShortLE(this.bestOverallSector1TimeInMS);
        buffer.writeByte(this.bestOverallSector1LapNum);
        buffer.writeShortLE(this.bestOverallSector2TimeInMS);
        buffer.writeByte(this.bestOverallSector2LapNum);
        buffer.writeShortLE(this.bestOverallSector3TimeInMS);
        buffer.writeByte(this.bestOverallSector3LapNum);
        buffer.writeFloatLE(this.lapDistance);
        buffer.writeFloatLE(this.totalDistance);
        buffer.writeFloatLE(this.safetyCarDelta);
        buffer.writeByte(this.carPosition);
        buffer.writeByte(this.currentLapNum);
        buffer.writeByte(this.pitStatus.getValue());
        buffer.writeByte(this.sector.getValue());
        buffer.writeByte(this.currentLapInvalid);
        buffer.writeByte(this.penalties);
        buffer.writeByte(this.gridPosition);
        buffer.writeByte(this.driverStatus.getValue());
        buffer.writeByte(this.resultStatus.getValue());
        return buffer;
    }

    @Override
    public String toString() {
        return "LapData[lastLapTime=" + this.lastLapTime +
                ",currentLapTime=" + this.currentLapTime +
                ",sector1TimeInMS=" + this.sector1TimeInMS +
                ",sector2TimeInMS=" + this.sector2TimeInMS +
                ",bestLapTime=" + this.bestLapTime +
                ",bestLapNum=" + this.bestLapNum +
                ",bestLapSector1TimeInMS=" + this.bestLapSector1TimeInMS +
                ",bestLapSector2TimeInMS=" + this.bestLapSector2TimeInMS +
                ",bestLapSector3TimeInMS=" + this.bestLapSector3TimeInMS +
                ",bestOverallSector1TimeInMS=" + this.bestOverallSector1TimeInMS +
                ",bestOverallSector1LapNum=" + this.bestOverallSector1LapNum +
                ",bestOverallSector2TimeInMS=" + this.bestOverallSector2TimeInMS +
                ",bestOverallSector2LapNum=" + this.bestOverallSector2LapNum +
                ",bestOverallSector3TimeInMS=" + this.bestOverallSector3TimeInMS +
                ",bestOverallSector3LapNum=" + this.bestOverallSector3LapNum +
                ",lapDistance=" + this.lapDistance +
                ",totalDistance=" + this.totalDistance +
                ",safetyCarDelta=" + this.safetyCarDelta +
                ",carPosition=" + this.carPosition +
                ",currentLapNum=" + this.currentLapNum +
                ",pitStatus=" + this.pitStatus +
                ",sector=" + this.sector +
                ",currentLapInvalid=" + this.currentLapInvalid +
                ",penalties=" + this.penalties +
                ",gridPosition=" + this.gridPosition +
                ",driverStatus=" + this.driverStatus +
                ",resultStatus=" + this.resultStatus +
                "]";
    }
}
