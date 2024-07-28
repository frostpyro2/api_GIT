package frostpyro.frostapi.dataManage.stat.nonPlayer;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class EntityData implements StatProvider {
    private EntityDataTmp tmp;
    private LivingEntity entity;
    public EntityData(EntityDataTmp tmp, EquipSlot slot){
        this.tmp = tmp;
        entity = tmp.getEntity();
    }

    public EntityDataTmp getTmp(){
        return tmp;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public double getStat(String stat) {
        return tmp.getStat(stat);
    }

    public void triggerSkill(TriggerData data){
        tmp.triggerSkill(data);
    }

    public void setCoolDown(Configuration configuration, double coolDown){
        tmp.setCoolDown(configuration, coolDown);
    }

    public void removeCoolDown(Configuration configuration){
        tmp.removeCoolDown(configuration);
    }

    public boolean isCoolDown(Configuration configuration){
        return tmp.isCoolDown(configuration);
    }
}
