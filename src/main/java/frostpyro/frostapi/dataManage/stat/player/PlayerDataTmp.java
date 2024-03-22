package frostpyro.frostapi.dataManage.stat.player;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.data.PlayerFile;
import frostpyro.frostapi.dataManage.stat.data.StatMap;
import frostpyro.frostapi.util.skill.skillItem.SkillItem;
import frostpyro.frostapi.util.skill.skillManager.SkillManager;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import frostpyro.frostapi.util.skill.ymlSkill.Skill;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PlayerDataTmp implements StatProvider {
    private Player player;
    private final UUID uuid;

    private final FileConfiguration configuration;

    private final Map<PlayerDataTmp, Long> coolDown = new HashMap<>();

    public PlayerDataTmp(Player player){
        this.player = player;
        uuid = player.getUniqueId();
        configuration = PlayerFile.getFile(Bukkit.getPlayer(uuid));
    }

    public PlayerDataTmp(UUID uuid){
        this.uuid = uuid;
        configuration = PlayerFile.getFile(Bukkit.getPlayer(uuid));
        player = Bukkit.getPlayer(uuid);
    }

    @Override
    public @NotNull LivingEntity getEntity() {
        return player;
    }

    @Override
    public double getStat(String stat) {
        return statMap().getStat(stat);
    }

    public void castSkill(TriggerType type) {

    }

    public void castSkill(TriggerData data){
        SkillManager manager = new SkillItem(data);
        manager.cast();
    }

    public FileConfiguration getConfiguration(){
        return configuration;
    }
    private static final Map<UUID, PlayerDataTmp> player_DT = new WeakHashMap<>();
    public static PlayerDataTmp get(UUID uuid){
        return player_DT.get(uuid);
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
