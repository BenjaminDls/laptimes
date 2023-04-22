package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketUtils;
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
        this.worldPositionX = buffer.readIntLE();
        this.worldPositionY = buffer.readIntLE();
        this.worldPositionZ = buffer.readIntLE();
        this.worldVelocityX = buffer.readIntLE();
        this.worldVelocityY = buffer.readIntLE();
        this.worldVelocityZ = buffer.readIntLE();
        this.worldForwardDirX = PacketUtils.normalizedVectorToFloat(buffer.readShortLE());
        this.worldForwardDirY = PacketUtils.normalizedVectorToFloat(buffer.readShortLE());
        this.worldForwardDirZ = PacketUtils.normalizedVectorToFloat(buffer.readShortLE());
        this.worldRightDirX = PacketUtils.normalizedVectorToFloat(buffer.readShortLE());
        this.worldRightDirY = PacketUtils.normalizedVectorToFloat(buffer.readShortLE());
        this.worldRightDirZ = PacketUtils.normalizedVectorToFloat(buffer.readShortLE());
        this.gForceLateral = buffer.readIntLE();
        this.gForceLongitudinal = buffer.readIntLE();
        this.gForceVertical = buffer.readIntLE();
        this.yaw = buffer.readIntLE();
        this.pitch = buffer.readIntLE();
        this.roll = buffer.readIntLE();
        return this;
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
