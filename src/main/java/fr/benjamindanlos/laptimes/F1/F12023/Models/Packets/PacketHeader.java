package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.PacketUtils;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.PacketId;
import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;

/**
 * Packet header
 */
@Getter
@ToString(includeFieldNames = true)
public class PacketHeader {

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

}
