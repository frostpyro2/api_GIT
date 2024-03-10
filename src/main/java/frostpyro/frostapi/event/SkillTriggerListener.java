package frostpyro.frostapi.event;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.damageManager.DamageType;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.player.PlayerAttackEvent;
import frostpyro.frostapi.dataManage.player.DataManage;
import frostpyro.frostapi.dataManage.player.YamlData;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.dataManage.player.PlayerData;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

import java.util.*;


public class SkillTriggerListener implements Listener {
    private Set<String> uuidSet = new HashSet<>();
    private DataManage manage = new YamlData();
    public SkillTriggerListener(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    private Map<PlayerData, Long> shiftPressTime = new HashMap<>();
    @EventHandler
    private void click(PlayerInteractEvent event){
        PlayerData playerData = manage.getPlayerData(event.getPlayer());
        if(event.getAction() == Action.PHYSICAL) return;
        boolean isSneaking = playerData.getPlayer().isSneaking();
        boolean isLeftClick = event.getAction() == Action.LEFT_CLICK_AIR;
        TriggerType type = isLeftClick ? (isSneaking ? TriggerType.SHIFT_LEFT_CLICK : TriggerType.LEFT_CLICK) : (isSneaking ? TriggerType.SHIFT_RIGHT_CLICK : TriggerType.RIGHT_CLICK);
        playerData.castSkill(type);
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
        PlayerData playerData = manage.getPlayerData(event.getPlayer());
        TriggerType type = TriggerType.ENTITY_RIGHT;
        playerData.castSkill(type);
    }

    @EventHandler
    private void getHit(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player player)) return;
        PlayerData playerData = new PlayerData(player.getUniqueId().toString(), player.getName(), 0, 0, 0, 0);
        playerData.castSkill(TriggerType.DAMAGED);
    }

    @EventHandler
    private void shift_twice(PlayerToggleSneakEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = new PlayerData(player.getUniqueId().toString(), player.getName(), 0, 0, 0, 0);
        if(event.isSneaking()){
            if(shiftPressTime.containsKey(playerData)){
                long lastShift = shiftPressTime.get(playerData);
                long time = System.currentTimeMillis();
                if(time - lastShift < 200) {
                    playerData.castSkill(TriggerType.SHIFT_SHIFT);
                }
            }
            shiftPressTime.put(playerData, System.currentTimeMillis());
        }
    }

    @EventHandler
    private void shift(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = new PlayerData(player.getUniqueId().toString(), player.getName(), 0, 0, 0, 0);
        if (event.isSneaking())
            playerData.castSkill(TriggerType.SHIFT);
    }
}
