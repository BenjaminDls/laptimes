package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class TeamMateInPits {

    private short vehicleIdx;

    /**
     * Fill the current TeamMateInPits with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled TeamMateInPits instance
     */
    public TeamMateInPits fill(ByteBuf buffer) {
        this.vehicleIdx = buffer.readUnsignedByte();
        return this;
    }

}
