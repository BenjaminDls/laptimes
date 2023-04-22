package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
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

    @Override
    public String toString() {
        return "FastestLap[vehicleIdx=" + this.vehicleIdx +
                ",lapTime=" + this.lapTime +
                "]";
    }
}
