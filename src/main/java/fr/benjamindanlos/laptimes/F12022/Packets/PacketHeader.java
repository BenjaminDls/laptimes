/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Packets;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F12022.PacketUtils;
import fr.benjamindanlos.laptimes.F12022.Enums.PacketId;
import lombok.Data;

import java.math.BigInteger;

/**
 * Packet header
 */
@Data
public class PacketHeader {

    public static final int SIZE = 24;

    // offset in the raw bytes where the packetId is
    public static final int PACKET_ID_OFFSET = 5;

    private int packetFormat;
    private short gameMajorVersion;
    private short gameMinorVersion;
    private short packetVersion;
    private PacketId packetId;
    private BigInteger sessionUid;
    private float sessionTime;
    private long frameIdentifier;
    private short playerCarIndex;
    private short secondaryPlayerCarIndex;

    /**
     * Fill the current PacketHeader with the raw bytes representation
     * 
     * @param buffer buffer with the raw bytes representation
     * @return current filled PacketHeader instance
     */
    public PacketHeader fill(ByteBuf buffer) {
        this.packetFormat = buffer.readUnsignedShortLE();
        this.gameMajorVersion = buffer.readUnsignedByte();
        this.gameMinorVersion = buffer.readUnsignedByte();
        this.packetVersion = buffer.readUnsignedByte();
        this.packetId = PacketId.valueOf(buffer.readUnsignedByte());
        this.sessionUid = PacketUtils.toUnsignedBigInteger(buffer.readLongLE());
        this.sessionTime = buffer.readFloatLE();
        this.frameIdentifier = buffer.readUnsignedIntLE();
        this.playerCarIndex = buffer.readUnsignedByte();
        this.secondaryPlayerCarIndex = buffer.readUnsignedByte();
        return this;
    }

    /**
     * Fill the buffer with the raw bytes representation of the current PacketHeader instance
     * 
     * @param buffer buffer to fill
     * @return filled buffer
     */
    public ByteBuf fillBuffer(ByteBuf buffer) {
        buffer.writeShortLE(this.packetFormat);
        buffer.writeByte(this.gameMajorVersion);
        buffer.writeByte(this.gameMinorVersion);
        buffer.writeByte(this.packetVersion);
        buffer.writeByte(this.packetId.getValue());
        buffer.writeLongLE(this.sessionUid.longValue());
        buffer.writeFloatLE(this.sessionTime);
        buffer.writeIntLE((int)this.frameIdentifier);
        buffer.writeByte(this.playerCarIndex);
        buffer.writeByte(this.secondaryPlayerCarIndex);
        return buffer;
    }

    @Override
    public String toString() {
        return "Header[format=" + this.packetFormat +
                ",major=" + this.gameMajorVersion +
                ",minor=" + this.gameMinorVersion +
                ",version=" + this.packetVersion + 
                ",id=" + this.packetId +
                ",sessionUid=" + this.sessionUid +
                ",sessionTime=" + this.sessionTime +
                ",frameIdentifier=" + this.frameIdentifier +
                ",playerCarIndex=" + this.playerCarIndex +
                ",secondaryPlayerCarIndex=" + this.secondaryPlayerCarIndex +
                "]";
    }
}
