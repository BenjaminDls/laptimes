package fr.benjamindanlos.laptimes.F1.F12022.Packets;

import io.netty.buffer.ByteBuf;

/**
 * Base class for all packets
 */
public abstract class Packet {

    // header
    protected PacketHeader header = new PacketHeader();

    public PacketHeader getHeader() {
        return header;
    }

    public Packet setHeader(PacketHeader header) {
        this.header = header;
		return this;
    }

    /**
     * Fill the current Packet instance decoding the proper bytes from the buffer
     * 
     * @param buffer buffer with the packet raw bytes
     * @return current instance
     */
    public Packet fill(ByteBuf buffer) {
        this.header.fill(buffer);
        return this;
    }

    @Override
    public String toString() {
        return this.header.toString();
    }
}
