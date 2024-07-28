package frostpyro.frostapi.dataManage.stat.nonPlayer;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.data.StatMap;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.*;

public class EntityDataTmp implements StatProvider {
    private LivingEntity entity;
    private UUID uuid;
    private FileConfiguration entityConfig;
    private FileConfiguration trigger;
    private final Map<Configuration, Long> coolDown = new HashMap<>();
    private final Map<Configuration, Long> duration = new HashMap<>();
    private final Map<FileConfiguration, Map<String, FileConfiguration>> toggle = new HashMap<>();
    private final Set<FileConfiguration> suppressSkill = new HashSet<>();

    public EntityDataTmp(LivingEntity skilledEntity){
        this.entity = skilledEntity;
        this.uuid = skilledEntity.getUniqueId();
    }

    public EntityDataTmp(UUID uuid){
        this.uuid = uuid;
        this.entity = (LivingEntity)Bukkit.getEntity(uuid);
    }
    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public double getStat(String stat) {
        return 0;
    }

    public void triggerSkill(TriggerData data){

    }

    public StatMap statMap(){
        return new StatMap(this);
    }

    public boolean isCoolDown(Configuration configuration){
        if(coolDown.get(configuration) == null) return false;
        return coolDown.get(configuration) >= System.currentTimeMillis();
    }

    public void setCoolDown(Configuration configuration, double coolDown){
        this.coolDown.put(configuration, (long)(System.currentTimeMillis() + 1000L * coolDown));
    }

    public void removeCoolDown(Configuration configuration){
        if(!coolDown.containsKey(configuration)) return;
        coolDown.remove(configuration);
    }

    private static Map<UUID, EntityDataTmp> entity_DT = new WeakHashMap<>();

    public static void register(UUID uuid, EntityDataTmp tmp){
        entity_DT.put(uuid, tmp);
    }

    public static EntityDataTmp get(UUID uuid){
        return entity_DT.get(uuid);
    }

    public static void flush(UUID uuid){
        entity_DT.remove(uuid);
    }
}
