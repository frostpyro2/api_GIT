package frostpyro.frostapi.dataManage.stat.player;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.data.StatMap;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerDataTmp implements StatProvider {
    private Player player;

    public PlayerDataTmp(Player player){
        this.player = player;
    }

    public PlayerDataTmp(UUID uuid){
        this.player = Bukkit.getPlayer(uuid);
    }

    @Override
    public LivingEntity getEntity() {
        return player;
    }

    @Override
    public double getStat(String stat) {
        return 0;
    }

    public void castSkill(TriggerType type) {

    }
    private static final Map<UUID, PlayerDataTmp> player_DT = new WeakHashMap<>();
    public static PlayerDataTmp get(Player player){
        return player_DT.get(player.getUniqueId());
    }

    public static void upload(UUID uuid, PlayerDataTmp tmp){
        player_DT.put(uuid, tmp);
    }

    public StatMap statMap(){
        return new StatMap(this);
    }
}
