package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.yamlInterpret.PathName;
import frostpyro.frostapi.util.skill.ymlSkill.yamlInterpret.RadiusInterpret;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class SkillAction implements Action{
    private FileConfiguration configuration;
    private PlayerTriggerData data;
    private String path;
    public SkillAction(FileConfiguration configuration, PlayerTriggerData data){
        this.configuration = configuration;
        this.data = data;
        path = PathName.ACTION.getActualPath();
    }

    public SkillAction(FileConfiguration configuration, PlayerTriggerData data, String path){
        this.configuration = configuration;
        this.data = data;
        this.path = path;
    }

    public void section(){
        WeakReference<Action> skillAction = new WeakReference<>(new Action(data, configuration.getMapList(path)));
        if(skillAction.get() == null) return;
        skillAction.get().runTask(FrostAPI.getPlugin());
    }

    private static class Action extends BukkitRunnable{
        private PlayerTriggerData data;
        private List<Map<?, ?>> act;
        private int delay = 0;
        Action(PlayerTriggerData data, List<Map<?, ?>> act){
            this.data = data;
            this.act = act;
        }


        @Override
        public void run() {
            if(act == null){
                this.cancel();
                return;
            }


            for(Map<?, ?> action : act){
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        damage(action);
                    }
                }.runTaskLater(FrostAPI.getPlugin(), delay);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        heal(action);
                    }
                }.runTaskLater(FrostAPI.getPlugin(), delay);
                if(action.containsKey("chat")){
                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            data.getCast().getEntity().sendMessage((String) action.get("chat"));
                        }
                    }.runTaskLater(FrostAPI.getPlugin(), delay);
                }
                else if(action.containsKey("delay")){
                    delay = (int) action.get("delay");
                }
            }
        }

        private void damage(Map<?, ?> action){
            if(!action.containsKey("damage")) return;
            Map<?, ?> damageInfo = (Map<?, ?>) action.get("damage");
            if(damageInfo == null) return;
            double amount;

            try{
                amount = (Double) damageInfo.get("amount");
            }
            catch (Exception any){
                amount = 0;
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

            for(Entity entity : center.getWorld().getNearbyEntities(center, interpret.getX(), interpret.getY(), interpret.getZ())){
                if(!(entity instanceof LivingEntity living)){
                    continue;
                }
                if(living == data.getCast().getEntity()){
                    continue;
                }
                data.getCast().addDamage(living);
                living.damage(finalAmount, data.getCast().getEntity());
            }
        }

        private void heal(Map<?, ?> action){
            if(!action.containsKey("heal")) return;
            Map<?, ?> healInfo = (Map<?, ?>) action.get("heal");
            double amount;
            boolean condition;

            try{
                amount = (Double) healInfo.get("amount");
            }
            catch (Exception any){
                amount = 0;
            }

            if(healInfo.get("target") == null){
                return;
            }

            try{
                condition = (Boolean) healInfo.get("onDamage");
            }
            catch (Exception any){
                condition = false;
            }

            boolean damage = true;

            if(condition){
                damage = data.getEvent() == null;
            }

            if(!damage) return;

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
