package frostpyro.frostapi.event;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.player.PlayerAttackEvent;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

import java.util.*;


public class SkillTriggerListener implements Listener {
    private Set<String> uuidSet = new HashSet<>();
    public SkillTriggerListener(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    private Map<PlayerDataTmp, Long> shiftPressTime = new HashMap<>();
    @EventHandler
    private void click(PlayerInteractEvent event){

    }

    @EventHandler
    private void hit(AttackEvent event){
        event.getEntity().sendMessage("damaged!");
    }

    @EventHandler
    private void playerHit(PlayerAttackEvent event){
        event.getPlayerData().castSkill(TriggerType.ATTACK);
    }

    @EventHandler
    private void entityRight(PlayerInteractAtEntityEvent event){

    }

    @EventHandler
    private void getHit(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player player)) return;
    }

    @EventHandler
    private void shift_twice(PlayerToggleSneakEvent event){
        Player player = event.getPlayer();
        PlayerDataTmp playerDataTmp = new PlayerDataTmp(player.getUniqueId(), player.getName(), 0, 0, 0, 0);
        if(event.isSneaking()){
            if(shiftPressTime.containsKey(playerDataTmp)){
                long lastShift = shiftPressTime.get(playerDataTmp);
                long time = System.currentTimeMillis();
                if(time - lastShift < 200) {
                    playerDataTmp.castSkill(TriggerType.SHIFT_SHIFT);
                }
            }
            shiftPressTime.put(playerDataTmp, System.currentTimeMillis());
        }
    }

    @EventHandler
    private void shift(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        PlayerDataTmp playerDataTmp = new PlayerDataTmp(player.getUniqueId(), player.getName(), 0, 0, 0, 0);
        if (event.isSneaking())
            playerDataTmp.castSkill(TriggerType.SHIFT);
    }
}
