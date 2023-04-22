package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.SurfaceType;
import lombok.Data;

import java.util.Arrays;

@Data
public class CarTelemetryData {

    public static final int SIZE = 58;

    private int speed;
    private float throttle;
    private float steer;
    private float brake;
    private short clutch;
    private short gear;
    private int engineRPM;
    private short drs;
    private short revLightsPercent;
    private short revLightsBitValue;
    private int[] brakesTemperature = new int[PacketConstants.TYRES];
    private short[] tyresSurfaceTemperature = new short[PacketConstants.TYRES];
    private short[] tyresInnerTemperature = new short[PacketConstants.TYRES];
    private int engineTemperature;
    private float[] tyresPressure = new float[PacketConstants.TYRES];
    private SurfaceType[] surfaceType = new SurfaceType[PacketConstants.TYRES];

    /**
     * Fill the current CarTelemetryData with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled CarTelemetryData instance
     */
    public CarTelemetryData fill(ByteBuf buffer) {
        this.speed = buffer.readUnsignedShortLE();
        this.throttle = buffer.readFloatLE();
        this.steer = buffer.readFloatLE();
        this.brake = buffer.readFloatLE();
        this.clutch = buffer.readUnsignedByte();
        this.gear = buffer.readByte();
        this.engineRPM = buffer.readUnsignedShortLE();
        this.drs = buffer.readUnsignedByte();
        this.revLightsPercent = buffer.readUnsignedByte();
        this.revLightsBitValue = buffer.readUnsignedByte();
        for (int j = 0; j < PacketConstants.TYRES; j++) {
            this.brakesTemperature[j] = buffer.readUnsignedShortLE();
        }
        for (int j = 0; j < PacketConstants.TYRES; j++) {
            this.tyresSurfaceTemperature[j] = buffer.readUnsignedByte();
        }
        for (int j = 0; j < PacketConstants.TYRES; j++) {
            this.tyresInnerTemperature[j] = buffer.readUnsignedByte();
        }
        this.engineTemperature = buffer.readUnsignedShortLE();
        for (int j = 0; j < PacketConstants.TYRES; j++) {
            this.tyresPressure[j] = buffer.readFloatLE();
        }
        for (int j = 0; j < PacketConstants.TYRES; j++) {
            this.surfaceType[j] = SurfaceType.valueOf(buffer.readUnsignedByte());
        }
        return this;
    }

    @Override
    public String toString() {
        return "CarTelemetryData[speed=" + this.speed +
                ",throttle=" + this.throttle +
                ",steer=" + this.steer +
                ",brake=" + this.brake +
                ",clutch=" + this.clutch +
                ",gear=" + this.gear +
                ",engineRPM=" + this.engineRPM +
                ",drs=" + this.drs +
                ",revLightsPercent=" + this.revLightsPercent +
                ",brakesTemperature=" + Arrays.toString(this.brakesTemperature) +
                ",tyresSurfaceTemperature=" + Arrays.toString(this.tyresSurfaceTemperature) +
                ",tyresInnerTemperature=" + Arrays.toString(this.tyresInnerTemperature) +
                ",engineTemperature=" + this.engineTemperature +
                ",tyresPressure=" + Arrays.toString(this.tyresPressure) +
                ",surfaceType=" + Arrays.toString(this.surfaceType) +
                "]";
    }
}
