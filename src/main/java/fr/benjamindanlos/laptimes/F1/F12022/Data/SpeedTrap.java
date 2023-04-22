package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class SpeedTrap {

    private short vehicleIdx;
    private float speed;
	private short isOverallFastestInSession;
	private short isDriverFastestInSession;
	private short fastestehicleIdxInSession;
	private float fastestSpeedInSession;

    /**
     * Fill the current SpeedTrap with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled SpeedTrap instance
     */
    public SpeedTrap fill(ByteBuf buffer) {
        this.vehicleIdx = buffer.readUnsignedByte();
        this.speed = buffer.readFloatLE();
		this.isOverallFastestInSession = buffer.readUnsignedByte();
		this.isDriverFastestInSession = buffer.readUnsignedByte();
		this.fastestehicleIdxInSession = buffer.readUnsignedByte();
		this.fastestSpeedInSession = buffer.readFloatLE();
        return this;
    }

    @Override
    public String toString() {
        return "SpeedTrap[vehicleIdx=" + this.vehicleIdx +
                ",speed=" + this.speed +
                "]";
    }
}
