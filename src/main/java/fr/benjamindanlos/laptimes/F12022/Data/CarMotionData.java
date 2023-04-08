/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F12022.PacketUtils;
import lombok.Data;

@Data
public class CarMotionData {

    public static final int SIZE = 60;

    private float worldPositionX;
    private float worldPositionY;
    private float worldPositionZ;
    private float worldVelocityX;
    private float worldVelocityY;
    private float worldVelocityZ;
    private float worldForwardDirX;
    private float worldForwardDirY;
    private float worldForwardDirZ;
    private float worldRightDirX;
    private float worldRightDirY;
    private float worldRightDirZ;
    private float gForceLateral;
    private float gForceLongitudinal;
    private float gForceVertical;
    private float yaw;
    private float pitch;
    private float roll;

    /**
     * Fill the current CarMotionData with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled CarMotionData instance
     */
    public CarMotionData fill(ByteBuf buffer) {
        this.worldPositionX = buffer.readFloatLE();
        this.worldPositionY = buffer.readFloatLE();
        this.worldPositionZ = buffer.readFloatLE();
        this.worldVelocityX = buffer.readFloatLE();
        this.worldVelocityY = buffer.readFloatLE();
        this.worldVelocityZ = buffer.readFloatLE();
        this.worldForwardDirX = PacketUtils.normalizedVectorToFloat(buffer.readShortLE());
        this.worldForwardDirY = PacketUtils.normalizedVectorToFloat(buffer.readShortLE());
        this.worldForwardDirZ = PacketUtils.normalizedVectorToFloat(buffer.readShortLE());
        this.worldRightDirX = PacketUtils.normalizedVectorToFloat(buffer.readShortLE());
        this.worldRightDirY = PacketUtils.normalizedVectorToFloat(buffer.readShortLE());
        this.worldRightDirZ = PacketUtils.normalizedVectorToFloat(buffer.readShortLE());
        this.gForceLateral = buffer.readFloatLE();
        this.gForceLongitudinal = buffer.readFloatLE();
        this.gForceVertical = buffer.readFloatLE();
        this.yaw = buffer.readFloatLE();
        this.pitch = buffer.readFloatLE();
        this.roll = buffer.readFloatLE();
        return this;
    }

    /**
     * Fill the buffer with the raw bytes representation of the current CarMotionData instance
     *
     * @param buffer buffer to fill
     * @return filled buffer
     */
    public ByteBuf fillBuffer(ByteBuf buffer) {
        buffer.writeFloatLE(this.worldPositionX);
        buffer.writeFloatLE(this.worldPositionY);
        buffer.writeFloatLE(this.worldPositionZ);
        buffer.writeFloatLE(this.worldVelocityX);
        buffer.writeFloatLE(this.worldVelocityY);
        buffer.writeFloatLE(this.worldVelocityZ);
        buffer.writeShortLE(PacketUtils.floatToNormalizedVector(this.worldForwardDirX));
        buffer.writeShortLE(PacketUtils.floatToNormalizedVector(this.worldForwardDirY));
        buffer.writeShortLE(PacketUtils.floatToNormalizedVector(this.worldForwardDirZ));
        buffer.writeShortLE(PacketUtils.floatToNormalizedVector(this.worldRightDirX));
        buffer.writeShortLE(PacketUtils.floatToNormalizedVector(this.worldRightDirY));
        buffer.writeShortLE(PacketUtils.floatToNormalizedVector(this.worldRightDirZ));
        buffer.writeFloatLE(this.gForceLateral);
        buffer.writeFloatLE(this.gForceLongitudinal);
        buffer.writeFloatLE(this.gForceVertical);
        buffer.writeFloatLE(this.yaw);
        buffer.writeFloatLE(this.pitch);
        buffer.writeFloatLE(this.roll);
        return buffer;
    }

    @Override
    public String toString() {
        return "CarMotionData[worldPositionX=" + this.worldPositionX +
                ",worldPositionY=" + this.worldPositionY +
                ",worldPositionZ=" + this.worldPositionZ +
                ",worldVelocityX=" + this.worldVelocityX +
                ",worldVelocityY=" + this.worldVelocityY +
                ",worldVelocityZ=" + this.worldVelocityZ +
                ",worldForwardDirX=" + this.worldForwardDirX +
                ",worldForwardDirY=" + this.worldForwardDirY +
                ",worldForwardDirZ=" + this.worldForwardDirZ +
                ",worldRightDirX=" + this.worldRightDirX +
                ",worldRightDirY=" + this.worldRightDirY +
                ",worldRightDirZ=" + this.worldRightDirZ +
                ",gForceLateral=" + this.gForceLateral +
                ",gForceLongitudinal=" + this.gForceLongitudinal +
                ",gForceVertical=" + this.gForceVertical +
                ",yaw=" + this.yaw +
                ",pitch=" + this.pitch +
                ",roll=" + this.roll +
                "]";
    }
}
