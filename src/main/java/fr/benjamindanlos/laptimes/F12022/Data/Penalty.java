/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F12022.Enums.InfringementType;
import fr.benjamindanlos.laptimes.F12022.Enums.PenaltyType;
import lombok.Data;

@Data
public class Penalty {

    private PenaltyType penaltyType;
    private InfringementType infringementType;
    private short vehicleIdx;
    private short otherVehicleIdx;
    private short time;
    private short lapNum;
    private short placesGained;

    /**
     * Fill the current Penalty with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled Penalty instance
     */
    public Penalty fill(ByteBuf buffer) {
        this.penaltyType = PenaltyType.valueOf(buffer.readUnsignedByte());
        this.infringementType = InfringementType.valueOf(buffer.readUnsignedByte());
        this.vehicleIdx = buffer.readUnsignedByte();
        this.otherVehicleIdx = buffer.readUnsignedByte();
        this.time = buffer.readUnsignedByte();
        this.lapNum = buffer.readUnsignedByte();
        this.placesGained = buffer.readUnsignedByte();
        return this;
    }

    /**
     * Fill the buffer with the raw bytes representation of the current Penalty instance
     *
     * @param buffer buffer to fill
     * @return filled buffer
     */
    public ByteBuf fillBuffer(ByteBuf buffer) {
        buffer.writeByte(this.penaltyType.getValue());
        buffer.writeByte(this.infringementType.getValue());
        buffer.writeByte(this.vehicleIdx);
        buffer.writeByte(this.otherVehicleIdx);
        buffer.writeByte(this.time);
        buffer.writeByte(this.lapNum);
        buffer.writeByte(this.placesGained);
        return buffer;
    }

    @Override
    public String toString() {
        return "Penalty[penaltyType=" + this.penaltyType +
                ",infringementType=" + this.infringementType +
                ",vehicleIdx=" + this.vehicleIdx +
                ",otherVehicleIdx=" + this.otherVehicleIdx +
                ",time=" + this.time +
                ",lapNum=" + this.lapNum +
                ",placesGained=" + this.placesGained +
                "]";
    }
}
