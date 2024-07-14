package frostpyro.frostapi.dataManage.stat.nonPlayer;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.*;

public class EntityDataTmp implements StatProvider {
    private Entity entity;
    private UUID uuid;
    private FileConfiguration configuration;
    private FileConfiguration trigger;
    private final Map<Configuration, Long> coolDown = new HashMap<>();
    private final Map<Configuration, Long> duration = new HashMap<>();
    private final Map<FileConfiguration, Map<String, FileConfiguration>> toggle = new HashMap<>();
    private final Set<FileConfiguration> suppressSkill = new HashSet<>();

    public EntityDataTmp(Entity entity){
        this.entity = entity;
        uuid = entity.getUniqueId();
    }

    public EntityDataTmp(UUID uuid){
        this.uuid = uuid;
        entity = Bukkit.getEntity(uuid);
    }
    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public double getStat(String stat) {
        return 0;
    }

    public void triggerSkill(TriggerData data){

    }

    public boolean isCoolDown(Configuration configuration){
        if(coolDown.get(configuration) == null) return false;
        return coolDown.get(configuration) >= System.currentTimeMillis();
    }

    private static Map<UUID, EntityDataTmp> entity_DT = new WeakHashMap<>();

    public static EntityDataTmp get(UUID uuid){
        return entity_DT.get(uuid);
    }

    public static void upload(UUID uuid, EntityDataTmp tmp){
        entity_DT.put(uuid, tmp);
    }

    public static void flush(UUID uuid){
        entity_DT.remove(uuid);
    }
}
