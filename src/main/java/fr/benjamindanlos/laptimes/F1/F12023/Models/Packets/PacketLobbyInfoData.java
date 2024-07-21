package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.LobbyInfoData;
import lombok.Getter;

/**
 * Lobby Info Packet
 * 
 * This packet details the players currently in a multiplayer lobby. It details
 * each player’s selected car, any AI involved in the game and also the ready
 * status of each of the participants.
 * Frequency: Two every second when in the lobby
 */
@Getter
public class PacketLobbyInfoData extends Packet {
    
    private short numPlayers;
    private LobbyInfoData[] lobbyInfoData = new LobbyInfoData[PacketConstants.LOBBY_PLAYERS];

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        this.numPlayers = buffer.readUnsignedByte();
        for (int i = 0; i < this.numPlayers; i++) {
            LobbyInfoData lid = new LobbyInfoData();
            this.lobbyInfoData[i] = lid.fill(buffer);
        }
        return this;
    }

}
