package fr.benjamindanlos.laptimes.F1.F12022.Packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12022.Data.LobbyInfoData;
import lombok.Data;

/**
 * Lobby Info Packet
 * 
 * This packet details the players currently in a multiplayer lobby. It details
 * each player’s selected car, any AI involved in the game and also the ready
 * status of each of the participants.
 * Frequency: Two every second when in the lobby
 */
@Data
public class PacketLobbyInfoData extends Packet {

    // 1169
    public static final int SIZE = PacketHeader.SIZE +
                                    1 +
                                    LobbyInfoData.SIZE * PacketConstants.LOBBY_PLAYERS;
    
    private short numPlayers;
    private List<LobbyInfoData> lobbyInfoData = new ArrayList<>(PacketConstants.LOBBY_PLAYERS);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LobbyInfo[");
        sb.append(super.toString());
        sb.append(",numPlayers=" + this.numPlayers);
        sb.append(",lobbyInfoData=");
        for (LobbyInfoData l : lobbyInfoData) {
            sb.append(l.toString() + ",");
        }
        sb.replace(sb.length() - 1, sb.length() - 1, "]");
        return sb.toString();
    }

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        this.numPlayers = buffer.readUnsignedByte();
        for (int i = 0; i < this.numPlayers; i++) {
            LobbyInfoData lid = new LobbyInfoData();
            this.lobbyInfoData.add(lid.fill(buffer));
        }
        return this;
    }

}
