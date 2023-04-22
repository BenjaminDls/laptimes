package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
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

    @Override
    public String toString() {
        return "RaceWinner[vehicleIdx=" + this.vehicleIdx +
                "]";
    }
}
