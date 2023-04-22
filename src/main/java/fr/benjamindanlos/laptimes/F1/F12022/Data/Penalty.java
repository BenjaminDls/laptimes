package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.InfringementType;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.PenaltyType;
import lombok.Data;

@Data
public class Penalty {

    private PenaltyType penaltyType;
    private InfringementType infringementType;
    private short vehicleIdx;
    private short otherVehicleIdx;
    private short time;
    private short lapNum;
    private short placesGained;

    /**
     * Fill the current Penalty with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled Penalty instance
     */
    public Penalty fill(ByteBuf buffer) {
        this.penaltyType = PenaltyType.valueOf(buffer.readUnsignedByte());
        this.infringementType = InfringementType.valueOf(buffer.readUnsignedByte());
        this.vehicleIdx = buffer.readUnsignedByte();
        this.otherVehicleIdx = buffer.readUnsignedByte();
        this.time = buffer.readUnsignedByte();
        this.lapNum = buffer.readUnsignedByte();
        this.placesGained = buffer.readUnsignedByte();
        return this;
    }

    @Override
    public String toString() {
        return "Penalty[penaltyType=" + this.penaltyType +
                ",infringementType=" + this.infringementType +
                ",vehicleIdx=" + this.vehicleIdx +
                ",otherVehicleIdx=" + this.otherVehicleIdx +
                ",time=" + this.time +
                ",lapNum=" + this.lapNum +
                ",placesGained=" + this.placesGained +
                "]";
    }
}
