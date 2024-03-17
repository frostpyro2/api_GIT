package frostpyro.frostapi.dataManage.guild;


import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;

import java.util.HashMap;
import java.util.Map;

public class Guild {
    private static Map<PlayerDataTmp, Guild> playerGuild = new HashMap<>();
    private final PlayerDataTmp master;
    public Guild(PlayerDataTmp master){
        this.master = master;
    }

    public static void register(PlayerDataTmp member, Guild guild){
        playerGuild.put(member, guild);
    }
}
