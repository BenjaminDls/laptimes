package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.ZoneFlag;
import lombok.Data;

@Data
public class MarshalZone {

    public static final int SIZE = 5;

    private float zoneStart;
    public ZoneFlag zoneFlag;

    /**
     * Fill the current MarshalZone with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled MarshalZone instance
     */
    public MarshalZone fill(ByteBuf buffer) {
        this.zoneStart = buffer.readIntLE();
        this.zoneFlag = ZoneFlag.valueOf(buffer.readByte());
        return this;
    }

    @Override
    public String toString() {
        return "MarshalZone[zoneStart=" + this.zoneStart +
                ",zoneFlag=" + this.zoneFlag +
                "]";
    }
}
