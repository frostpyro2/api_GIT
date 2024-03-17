package frostpyro.frostapi.dataManage.stat.player;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.data.PlayerFile;
import frostpyro.frostapi.dataManage.stat.data.StatMap;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerDataTmp implements StatProvider {
    private Player player;
    private final UUID uuid;

    private final FileConfiguration configuration;

    private final Map<PlayerDataTmp, Long> coolDown = new HashMap<>();

    public PlayerDataTmp(Player player){
        uuid = player.getUniqueId();
        configuration = PlayerFile.getFile((LivingEntity) Bukkit.getEntity(uuid));
    }

    public PlayerDataTmp(UUID uuid){
        this.uuid = uuid;
        configuration = PlayerFile.getFile((LivingEntity) Bukkit.getEntity(uuid));
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) Bukkit.getEntity(uuid);
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

    public static void flush(UUID uuid){
        player_DT.remove(uuid);
    }

    public StatMap statMap(){
        return new StatMap(this);
    }
}
