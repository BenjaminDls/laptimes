/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Data;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class SpeedTrap {

    private short vehicleIdx;
    private float speed;

    /**
     * Fill the current SpeedTrap with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled SpeedTrap instance
     */
    public SpeedTrap fill(ByteBuf buffer) {
        this.vehicleIdx = buffer.readUnsignedByte();
        this.speed = buffer.readFloatLE();
        return this;
    }

    /**
     * Fill the buffer with the raw bytes representation of the current SpeedTrap instance
     *
     * @param buffer buffer to fill
     * @return filled buffer
     */
    public ByteBuf fillBuffer(ByteBuf buffer) {
        buffer.writeByte(this.vehicleIdx);
        buffer.writeFloatLE(this.speed);
        return buffer;
    }

    @Override
    public String toString() {
        return "SpeedTrap[vehicleIdx=" + this.vehicleIdx +
                ",speed=" + this.speed +
                "]";
    }
}
