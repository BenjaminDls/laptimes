package fr.benjamindanlos.laptimes.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F12022.Enums.ZoneFlag;
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
        this.zoneStart = buffer.readFloatLE();
        this.zoneFlag = ZoneFlag.valueOf(buffer.readByte());
        return this;
    }

    /**
     * Fill the buffer with the raw bytes representation of the current MarshalZone instance
     *
     * @param buffer buffer to fill
     * @return filled buffer
     */
    public ByteBuf fillBuffer(ByteBuf buffer) {
        buffer.writeFloatLE(this.zoneStart);
        buffer.writeByte(this.zoneFlag.getValue());
        return buffer;
    }

    @Override
    public String toString() {
        return "MarshalZone[zoneStart=" + this.zoneStart +
                ",zoneFlag=" + this.zoneFlag +
                "]";
    }
}
