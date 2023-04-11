package fr.benjamindanlos.laptimes.AssettoCorsa.Data;

import io.netty.buffer.ByteBuf;

import java.io.Serializable;

public abstract class Packet implements Serializable {
	abstract public Packet fill(ByteBuf buffer);
}
