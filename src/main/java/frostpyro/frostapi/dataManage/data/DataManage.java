package frostpyro.frostapi.dataManage.data;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import org.bukkit.entity.LivingEntity;

public interface DataManage {
    StatProvider getEntityData(LivingEntity livingEntity);
    void createData(LivingEntity livingEntity);
}
