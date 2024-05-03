package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.yamlInterpret.SettingInterpret;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.ietf.jgss.GSSName;

import java.util.List;
import java.util.Map;

public class SkillArmorStand implements Action{
    private Configuration configuration;
    private TriggerData data;
    public SkillArmorStand(Configuration configuration, TriggerData data){
        this.configuration = configuration;
        this.data = data;
    }

    public void section(){
        new ArmorStandSummon(configuration.getList("skill.armorStand"), data).runTask(FrostAPI.getPlugin());
    }
    private static class ArmorStandSummon extends BukkitRunnable{
        private int actionDelay = 0;
        private List<?> act;
        private TriggerData data;
        ArmorStandSummon(List<?> act, TriggerData data){
            this.act = act;
            this.data = data;
        }
        @Override
        public void run() {
            if(act == null){
                this.cancel();
                return;
            }
            for(Object obj : act){
                if(obj instanceof Map<?,?> standData){
                    delay(standData);
                    scheduledTask(()->{
                        org.bukkit.entity.ArmorStand stand = baseStand();
                        standSummon(standData, stand, stand.getLocation());
                    }, actionDelay);
                }
            }
        }

        private void delay(Map<?, ?> standData){
            if(!standData.containsKey("delay")) return;
            actionDelay = (int) standData.get("delay");
        }

        private void standSummon(Map<?, ?> standData, org.bukkit.entity.ArmorStand stand, Location standLoc){
            if(!standData.containsKey("stand")) return;;
            List<?> objList = (List<?>) standData.get("stand");
            int standDelay = 0;
            double speed = 0.0;
            if(objList == null) return;
            for(Object standSetting : objList){
                if(standSetting instanceof Map<?, ?> valueSetting){


                    if(valueSetting.containsKey("delay")){
                        standDelay = (int) valueSetting.get("delay");
                    }

                    setting(valueSetting, stand, standLoc);

                    scheduledTask(()-> setHeadItem(valueSetting, stand), standDelay);

                    scheduledTask(()->headModel(valueSetting, stand), standDelay);
                }
                else if(standSetting instanceof String actSetting){
                    scheduledTask(()->{
                        if("remove".equals(actSetting)){
                            stand.remove();
                        }
                    }, standDelay);
                    return;
                }
            }
        }
        private void setting(Map<?, ?> valueSetting, ArmorStand stand, Location standLoc){
            if(!valueSetting.containsKey("setting")) return;
            SettingInterpret inter = new SettingInterpret((Map<?, ?>) valueSetting.get("setting"));
            stand.setInvisible(inter.isInvisible());
            stand.setGravity(inter.isGravity());

            standLoc.setYaw((float) inter.getAngle() + data.getCast().getEntity().getLocation().getYaw());
            Vector vector = standLoc.getDirection();
            vector.setY(0);
            if(inter.isVector()){
                EulerAngle angle = new EulerAngle(Math.toRadians(data.getCast().getEntity().getLocation().getPitch()), 0, 0);
                stand.setHeadPose(angle);
            }
            stand.teleport(standLoc);
            if(inter.getVelocity() != 0){
                stand.setMarker(false);
                stand.setVelocity(vector.normalize().multiply(inter.getVelocity()));
            }
        }

        private void setHeadItem(Map<?, ?> valueSetting, ArmorStand stand){
            if(!valueSetting.containsKey("setHeadItem")) return;
            ItemStack armorItem;
            try{
                armorItem = new ItemStack(Material.getMaterial((String) valueSetting.get("setHeadItem")));
            }
            catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "error while placing item on head of armor stand! perhaps wrote wrong material?");
                return;
            }
            stand.getEquipment().setHelmet(armorItem);
        }

        private void headModel(Map<?, ?> valueSetting, ArmorStand stand){
            if(!valueSetting.containsKey("setHeadModel")) return;
            ItemStack armorItem = stand.getEquipment().getHelmet();
            if (armorItem == null) return;

            ItemMeta itemMeta = armorItem.getItemMeta();
            itemMeta.setCustomModelData((int) valueSetting.get("setHeadModel"));
            armorItem.setItemMeta(itemMeta);
            stand.getEquipment().setHelmet(armorItem);
        }

        private org.bukkit.entity.ArmorStand baseStand(){
            org.bukkit.entity.ArmorStand stand = data.getCast().getEntity().getWorld().spawn(data.getCast().getEntity().getLocation(), org.bukkit.entity.ArmorStand.class);
            stand.setMarker(true);
            stand.setInvulnerable(true);
            return stand;
        }

        private void scheduledTask(Runnable runnable, int delay){
            new BukkitRunnable(){
                @Override
                public void run() {
                    runnable.run();
                }
            }.runTaskLater(FrostAPI.getPlugin(), delay);
        }
    }
}
