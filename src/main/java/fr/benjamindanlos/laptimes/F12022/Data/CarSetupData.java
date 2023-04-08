/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Data;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class CarSetupData {

    public static final int SIZE = 49;

    private short frontWing;
    private short rearWing;
    private short onThrottle;
    private short offThrottle;
    private float frontCamber;
    private float rearCamber;
    private float frontToe;
    private float rearToe;
    private short frontSuspension;
    private short rearSuspension;
    private short frontAntiRollBar;
    private short rearAntiRollBar;
    private short frontSuspensionHeight;
    private short rearSuspensionHeight;
    private short brakePressure;
    private short brakeBias;
    private float rearLeftTyrePressure;
    private float rearRightTyrePressure;
    private float frontLeftTyrePressure;
    private float frontRightTyrePressure;
    private short ballast;
    private float fuelLoad;

    /**
     * Fill the current CarSetupData with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled CarSetupData instance
     */
    public CarSetupData fill(ByteBuf buffer) {
        this.frontWing = buffer.readUnsignedByte();
        this.rearWing = buffer.readUnsignedByte();
        this.onThrottle = buffer.readUnsignedByte();
        this.offThrottle = buffer.readUnsignedByte();
        this.frontCamber = buffer.readFloatLE();
        this.rearCamber = buffer.readFloatLE();
        this.frontToe = buffer.readFloatLE();
        this.rearToe = buffer.readFloatLE();
        this.frontSuspension = buffer.readUnsignedByte();
        this.rearSuspension = buffer.readUnsignedByte();
        this.frontAntiRollBar = buffer.readUnsignedByte();
        this.rearAntiRollBar = buffer.readUnsignedByte();
        this.frontSuspensionHeight = buffer.readUnsignedByte();
        this.rearSuspensionHeight = buffer.readUnsignedByte();
        this.brakePressure = buffer.readUnsignedByte();
        this.brakeBias = buffer.readUnsignedByte();
        this.rearLeftTyrePressure = buffer.readFloatLE();
        this.rearRightTyrePressure = buffer.readFloatLE();
        this.frontLeftTyrePressure = buffer.readFloatLE();
        this.frontRightTyrePressure = buffer.readFloatLE();
        this.ballast = buffer.readUnsignedByte();
        this.fuelLoad = buffer.readFloatLE();
        return this;
    }

    /**
     * Fill the buffer with the raw bytes representation of the current CarSetupData instance
     *
     * @param buffer buffer to fill
     * @return filled buffer
     */
    public ByteBuf fillBuffer(ByteBuf buffer) {
        buffer.writeByte(this.frontWing);
        buffer.writeByte(this.rearWing);
        buffer.writeByte(this.onThrottle);
        buffer.writeByte(this.offThrottle);
        buffer.writeFloatLE(this.frontCamber);
        buffer.writeFloatLE(this.rearCamber);
        buffer.writeFloatLE(this.frontToe);
        buffer.writeFloatLE(this.rearToe);
        buffer.writeByte(this.frontSuspension);
        buffer.writeByte(this.rearSuspension);
        buffer.writeByte(this.frontAntiRollBar);
        buffer.writeByte(this.rearAntiRollBar);
        buffer.writeByte(this.frontSuspensionHeight);
        buffer.writeByte(this.rearSuspensionHeight);
        buffer.writeByte(this.brakePressure);
        buffer.writeByte(this.brakeBias);
        buffer.writeFloatLE(this.rearLeftTyrePressure);
        buffer.writeFloatLE(this.rearRightTyrePressure);
        buffer.writeFloatLE(this.frontLeftTyrePressure);
        buffer.writeFloatLE(this.frontRightTyrePressure);
        buffer.writeByte(this.ballast);
        buffer.writeFloatLE(this.fuelLoad);
        return buffer;
    }

    @Override
    public String toString() {
        return "CarSetupData[frontWing=" + this.frontWing +
                ",rearWing=" + this.rearWing +
                ",onThrottle=" + this.onThrottle +
                ",offThrottle=" + this.offThrottle +
                ",frontCamber=" + this.frontCamber +
                ",rearCamber=" + this.rearCamber +
                ",frontToe=" + this.frontToe +
                ",rearToe=" + this.rearToe +
                ",frontSuspension=" + this.frontSuspension +
                ",rearSuspension=" + this.rearSuspension +
                ",frontAntiRollBar=" + this.frontAntiRollBar +
                ",rearAntiRollBar=" + this.rearAntiRollBar +
                ",frontSuspensionHeight=" + this.frontSuspensionHeight +
                ",rearSuspensionHeight=" + this.rearSuspensionHeight +
                ",brakePressure=" + this.brakePressure +
                ",brakeBias=" + this.brakeBias +
                ",rearLeftTyrePressure=" + this.rearLeftTyrePressure +
                ",rearRightTyrePressure=" + this.rearRightTyrePressure +
                ",frontLeftTyrePressure=" + this.frontLeftTyrePressure +
                ",frontRightTyrePressure=" + this.frontRightTyrePressure +
                ",ballast=" + this.ballast +
                ",fuelLoad=" + this.fuelLoad +
                "]";
    }
}
