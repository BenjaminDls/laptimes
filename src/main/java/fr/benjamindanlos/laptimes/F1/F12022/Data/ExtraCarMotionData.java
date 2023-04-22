package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketConstants;
import lombok.Data;

import java.util.Arrays;

@Data
public class ExtraCarMotionData {

    public static final int SIZE = 120;

    // Wheels in order: RL, RR, FL, FR.
    private float[] suspensionPosition = new float[PacketConstants.WHEELS];
    private float[] suspensionVelocity = new float[PacketConstants.WHEELS];
    private float[] suspensionAcceleration = new float[PacketConstants.WHEELS];
    private float[] wheelSpeed = new float[PacketConstants.WHEELS];
    private float[] wheelSlip = new float[PacketConstants.WHEELS];
    private float localVelocityX;
    private float localVelocityY;
    private float localVelocityZ;
    private float angularVelocityX;
    private float angularVelocityY;
    private float angularVelocityZ;
    private float angularAccelerationX;
    private float angularAccelerationY;
    private float angularAccelerationZ;
    private float frontWheelsAngle;

    /**
     * Fill the current ExtraCarMotionData with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled ExtraCarMotionData instance
     */
    public ExtraCarMotionData fill(ByteBuf buffer) {
        for (int i = 0; i < PacketConstants.WHEELS; i++) {
            this.suspensionPosition[i] = buffer.readIntLE();
        }
        for (int i = 0; i < PacketConstants.WHEELS; i++) {
            this.suspensionVelocity[i] = buffer.readIntLE();
        }
        for (int i = 0; i < PacketConstants.WHEELS; i++) {
            this.suspensionAcceleration[i] = buffer.readIntLE();
        }
        for (int i = 0; i < PacketConstants.WHEELS; i++) {
            this.wheelSpeed[i] = buffer.readIntLE();
        }
        for (int i = 0; i < PacketConstants.WHEELS; i++) {
            this.wheelSlip[i] = buffer.readIntLE();
        }
        this.localVelocityX = buffer.readIntLE();
        this.localVelocityY = buffer.readIntLE();
        this.localVelocityZ = buffer.readIntLE();
        this.angularVelocityX = buffer.readIntLE();
        this.angularVelocityY = buffer.readIntLE();
        this.angularVelocityZ = buffer.readIntLE();
        this.angularAccelerationX = buffer.readIntLE();
        this.angularAccelerationY = buffer.readIntLE();
        this.angularAccelerationZ = buffer.readIntLE();
        this.frontWheelsAngle = buffer.readIntLE();
        return this;
    }

    @Override
    public String toString() {
        return "ExtraCarMotionData[suspensionPosition=" + Arrays.toString(this.suspensionPosition) +
                ",suspensionVelocity=" + Arrays.toString(this.suspensionVelocity) +
                ",suspensionAcceleration= " + Arrays.toString(this.suspensionAcceleration) +
                ",wheelSpeed=" + Arrays.toString(this.wheelSpeed) +
                ",wheelSlip=" + Arrays.toString(this.wheelSlip) +
                ",localVelocityX=" + this.localVelocityX +
                ",localVelocityY=" + this.localVelocityY +
                ",localVelocityZ=" + this.localVelocityZ +
                ",angularVelocityX=" + this.angularVelocityX +
                ",angularVelocityY=" + this.angularVelocityY +
                ",angularVelocityZ=" + this.angularVelocityZ +
                ",angularAccelerationX=" + this.angularAccelerationX +
                ",angularAccelerationY=" + this.angularAccelerationY +
                ",angularAccelerationZ=" + this.angularAccelerationZ +
                ",frontWheelsAngle=" + this.frontWheelsAngle +
                "]";
    }
}
