package frostpyro.frostapi.dataManage.stat.nonPlayer;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import org.bukkit.entity.LivingEntity;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public class EntityDataTmp implements StatProvider {
    @Override
    public LivingEntity getEntity() {
        return null;
    }

    @Override
    public double getStat(String stat) {
        return 0;
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
