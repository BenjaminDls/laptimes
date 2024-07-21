package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.PacketUtils;
import lombok.Getter;

@Getter
public class CarMotionData {

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
	
}
