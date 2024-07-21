package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
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

}
