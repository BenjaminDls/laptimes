/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F12022.PacketConfig;
import fr.benjamindanlos.laptimes.F12022.PacketUtils;
import fr.benjamindanlos.laptimes.F12022.Enums.Driver;
import fr.benjamindanlos.laptimes.F12022.Enums.Nationality;
import fr.benjamindanlos.laptimes.F12022.Enums.Team;
import lombok.Data;

@Data
public class ParticipantData {

    public static final int SIZE = 54;

    public static final int NAME_LENGTH = 48;

    private short aiControlled;
    private Driver driverId;
    private Team teamId;
    private short raceNumber;
    private Nationality nationality;
    private String name;
    private short yourTelemetry;

    /**
     * Fill the current ParticipantData with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled ParticipantData instance
     */
    public ParticipantData fill(ByteBuf buffer) {
        this.aiControlled = buffer.readUnsignedByte();
        this.driverId = Driver.valueOf(PacketConfig.getSeason(), buffer.readUnsignedByte());
        this.teamId = Team.valueOf(PacketConfig.getSeason(), buffer.readUnsignedByte());
        this.raceNumber = buffer.readUnsignedByte();
        this.nationality = Nationality.valueOf(buffer.readUnsignedByte());
        this.name = PacketUtils.readString(buffer, ParticipantData.NAME_LENGTH);
        this.yourTelemetry = buffer.readUnsignedByte();
        return this;
    }

    /**
     * Fill the buffer with the raw bytes representation of the current ParticipantData instance
     *
     * @param buffer buffer to fill
     * @return filled buffer
     */
    public ByteBuf fillBuffer(ByteBuf buffer) {
        buffer.writeByte(this.aiControlled);
        buffer.writeByte(this.driverId.getValue());
        buffer.writeByte(this.teamId.getValue());
        buffer.writeByte(this.raceNumber);
        buffer.writeByte(this.nationality.getValue());
        PacketUtils.writeString(this.name, buffer, ParticipantData.NAME_LENGTH);
        buffer.writeByte(this.yourTelemetry);
        return buffer;
    }

    @Override
    public String toString() {
        return "ParticipantData[aiControlled=" + this.aiControlled +
                ",driverId=" + this.driverId +
                ",teamId=" + this.teamId +
                ",raceNumber=" + this.raceNumber +
                ",nationality=" + this.nationality +
                ",name=" + this.name +
                ",yourTelemetry=" + this.yourTelemetry +
                "]";
    }
}
