package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class RaceWinner {

    private short vehicleIdx;

    /**
     * Fill the current RaceWinner with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled RaceWinner instance
     */
    public RaceWinner fill(ByteBuf buffer) {
        this.vehicleIdx = buffer.readUnsignedByte();
        return this;
    }
	
}
