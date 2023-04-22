package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
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

    @Override
    public String toString() {
        return "TeamMateInPits[vehicleIdx=" + this.vehicleIdx +
                "]";
    }
}
