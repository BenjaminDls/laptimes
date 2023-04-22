package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class Retirement {

    private short vehicleIdx;

    /**
     * Fill the current Retirement with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled Retirement instance
     */
    public Retirement fill(ByteBuf buffer) {
        this.vehicleIdx = buffer.readUnsignedByte();
        return this;
    }

    @Override
    public String toString() {
        return "Retirement[vehicleIdx=" + this.vehicleIdx +
                "]";
    }
}
