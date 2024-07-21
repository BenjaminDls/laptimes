package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.ZoneFlag;
import lombok.Getter;

@Getter
public class MarshalZone {

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

}
