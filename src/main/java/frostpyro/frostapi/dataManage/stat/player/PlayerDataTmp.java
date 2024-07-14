package frostpyro.frostapi.dataManage.stat.player;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.data.PlayerFile;
import frostpyro.frostapi.dataManage.stat.data.StatMap;
import frostpyro.frostapi.util.skill.casting.AnotherTrigger;
import frostpyro.frostapi.util.skill.casting.SkillItem;
import frostpyro.frostapi.util.skill.SkillManager;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PlayerDataTmp implements StatProvider {
    private Player player;
    private final UUID uuid;

    private final FileConfiguration configuration;
    private FileConfiguration trigger;
    private final Map<Configuration, Long> coolDown = new HashMap<>();
    private final Map<Configuration, Long> duration = new HashMap<>();
    private final Map<FileConfiguration, Map<String, FileConfiguration>> toggle = new HashMap<>();
    private final Set<FileConfiguration> suppressSkill = new HashSet<>();
    private Map<String, FileConfiguration> tmp = new HashMap<>();
    private Set<Entity> skillDamagedEntity = new HashSet<>();
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

    public void castSkill(PlayerTriggerData data, EquipSlot slot){
        if(slot != EquipSlot.MAIN_HAND) return;
        SkillManager skillManager;
        if(data.getCast().getEntity().getItemInUse() == null)
            skillManager = new AnotherTrigger(data);
        else
            skillManager  = new SkillItem(data);
        skillManager.cast();
    }

    public void castSkill(PlayerTriggerData data){
        if(data.getSlot() == null){
            SkillManager manager = new AnotherTrigger(data);
            manager.cast();
            return;
        }
        castSkill(data, data.getSlot());
    }

    public void setCoolDown(Configuration config, double sec){
        coolDown.put(config, (long) (System.currentTimeMillis() + sec * 1000L));
    }

    public void clearToggleCache(){
        toggle.clear();
    }
    public boolean isCoolDown(Configuration skill){
        if(coolDown.get(skill) == null) return false;
        return coolDown.get(skill) >= System.currentTimeMillis();
    }

    public void removeCoolDown(Configuration config){
        if(coolDown.get(config) == null) return;
        coolDown.remove(config);
    }

    public boolean notDuration(Configuration configuration){
        return this.duration.get(configuration) <= System.currentTimeMillis();
    }

    public void setToggle(FileConfiguration configuration){
        this.trigger = configuration;
    }

    public void registerToggle(FileConfiguration configuration, Map<String, FileConfiguration> tmp){
        toggle.put(configuration, tmp);
    }

    public Map<FileConfiguration, Map<String, FileConfiguration>> getToggleMap(){
        return toggle;
    }
    public void removeToggle(FileConfiguration configuration){
        toggle.remove(configuration);
    }

    public FileConfiguration getToggle(){
        return trigger;
    }
    public void addSuppress(FileConfiguration data){
        suppressSkill.add(data);
    }

    public void removeSuppress(FileConfiguration data){
        suppressSkill.remove(data);
    }

    public boolean isSuppressed(FileConfiguration data){
        return suppressSkill.contains(data);
    }

    public void setDuration(Configuration configuration, double duration){
        this.duration.put(configuration, (long) (System.currentTimeMillis() + duration * 1000) );
    }

    public void addDamage(Entity entity){
        skillDamagedEntity.add(entity);
    }

    public void removeDamage(Entity entity){
        skillDamagedEntity.remove(entity);
    }

    public boolean isDamaged(Entity entity){
        return skillDamagedEntity.contains(entity);
    }

    public void removeDuration(Configuration configuration){
        if(configuration == null) return;
        this.duration.remove(configuration);
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
