package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.yamlInterpret.RadiusInterpret;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.w3c.dom.Attr;

import java.util.List;
import java.util.Map;

public class SkillAction {
    private FileConfiguration configuration;
    private TriggerData data;
    public SkillAction(FileConfiguration configuration, TriggerData data){
        this.configuration = configuration;
        this.data = data;
    }

    public void actionSection(){
        new Action(data, configuration.getMapList("skill.action")).runTaskTimer(FrostAPI.getPlugin(), 0, 1);
    }

    private static class Action extends BukkitRunnable{
        private int delay = 0;
        private int index = 0;
        private TriggerData data;
        private List<Map<?, ?>> act;
        Action(TriggerData data, List<Map<?, ?>> act){
            this.data = data;
            this.act = act;
        }


        @Override
        public void run() {
            if(act == null){
                this.cancel();
                return;
            }
            if(delay > 0){
                delay--;
                return;
            }

            if(index >= act.size()){
                this.cancel();
                return;
            }

            Map<?, ?> action = act.get(index++);

            if(action.containsKey("damage")){
                Map<?, ?> damageInfo = (Map<?, ?>) action.get("damage");
                damage(damageInfo);
            }
            else if(action.containsKey("heal")){
                Map<?, ?> healInfo = (Map<?, ?>) action.get("heal");
                heal(healInfo);
            }
            else if(action.containsKey("delay")){
                delay = (Integer) action.get("delay");
            }
        }

        private void damage(Map<?, ?> damageInfo){
            double amount;

            try{
                amount = (Double) damageInfo.get("amount");
            }
            catch (Exception any){
                amount = .01;
            }
            if(damageInfo.get("target") == null) return;
            if("self".equals(damageInfo.get("target"))){
                data.getCast().getEntity().damage(amount);
                return;
            }

            RadiusInterpret interpret = new RadiusInterpret((Map<?, ?>) damageInfo.get("radius"));

            Location center = data.getCast().getEntity().getLocation();

            double finalAmount = amount;

            if(damageInfo.get("radius.dist") != null){
                center = center.add(center.getDirection().normalize().multiply((double) damageInfo.get("radius.dist")));
            }

            if(center.getWorld() == null) return;


            center.getWorld().getNearbyEntities(center, interpret.getX(), interpret.getY(), interpret.getZ()).forEach(entity -> {
                if(entity instanceof LivingEntity living){
                    if(living != data.getCast().getEntity()){
                           FrostAPI.getPlugin().entityDamagedKey(living);
                           living.damage(finalAmount, data.getCast().getEntity());
                    }
                }
            });
        }

        private void heal(Map<?, ?> healInfo){
            double amount;

            try{
                amount = (Double) healInfo.get("amount");
            }
            catch (Exception any){
                amount = .01;
            }

            if(healInfo.get("target") == null){
                return;
            }

            if("self".equals(healInfo.get("target"))){
                AttributeInstance healthAtb = data.getCast().getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH);
                data.getCast().getEntity().setHealth(Math.min(amount + data.getCast().getEntity().getHealth(), healthAtb.getValue()));
                return;
            }

            RadiusInterpret interpret = new RadiusInterpret((Map<?, ?>) healInfo.get("radius"));

            Location center = data.getCast().getEntity().getLocation();
            if(center.getWorld() == null) return;

            double finalAmount = amount;

            center.getWorld().getNearbyEntities(center, interpret.getX(), interpret.getY(), interpret.getZ()).forEach(entity -> {
                if(entity instanceof LivingEntity living){
                    AttributeInstance healthAtb = living.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                    living.setHealth(Math.min(finalAmount + living.getHealth(), healthAtb.getValue()));
                }
            });
        }
    }
}
