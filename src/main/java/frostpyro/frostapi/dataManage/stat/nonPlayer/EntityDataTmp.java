package frostpyro.frostapi.dataManage.stat.nonPlayer;

import frostpyro.frostapi.dataManage.stat.StatProvider;
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

    public void triggerSkill(){

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
