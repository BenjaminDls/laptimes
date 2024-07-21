package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.PacketUtils;
import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Driver;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Nationality;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Platform;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Team;
import lombok.Getter;

@Getter
public class ParticipantData {

    private short aiControlled;
    private Driver driverId;
	private short networkId;
    private Team teamId;
	private short myTeam;
    private short raceNumber;
    private Nationality nationality;
    private String name;
    private short yourTelemetry;
	private short showOnlineNames;
	private Platform platform;

    /**
     * Fill the current ParticipantData with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled ParticipantData instance
     */
    public ParticipantData fill(ByteBuf buffer) {
        this.aiControlled = buffer.readUnsignedByte();
        this.driverId = Driver.valueOf(buffer.readUnsignedByte());
        this.networkId = buffer.readUnsignedByte();
		this.teamId = Team.valueOf(buffer.readUnsignedByte());
		this.myTeam = buffer.readUnsignedByte();
        this.raceNumber = buffer.readUnsignedByte();
        this.nationality = Nationality.valueOf(buffer.readUnsignedByte());
        this.name = PacketUtils.readString(buffer, PacketConstants.NAME_LENGTH);
        this.yourTelemetry = buffer.readUnsignedByte();
		this.showOnlineNames = buffer.readUnsignedByte();
		this.platform = Platform.valueOf(buffer.readUnsignedByte());
		
        return this;
    }

}
