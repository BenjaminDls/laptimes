package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class FastestLap {

    private short vehicleIdx;
    private float lapTime;

    /**
     * Fill the current FastestLap with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled FastestLap instance
     */
    public FastestLap fill(ByteBuf buffer) {
        this.vehicleIdx = buffer.readUnsignedByte();
        this.lapTime = buffer.readIntLE()/1000f;
        return this;
    }

}
