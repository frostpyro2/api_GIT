package frostpyro.frostapi.event;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.PlayerAttackEvent;
import frostpyro.frostapi.graphic_user_interface.EXP_GUI.ExpGUI;
import frostpyro.frostapi.graphic_user_interface.User_Interface;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.api.listeners.customEvents.ExpChangeEvent;
import frostpyro.frostapi.players.PlayerData;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerLoadEvent;

import java.util.*;


public class SkillTriggerListener implements Listener {
    private Set<String> uuidSet = new HashSet<>();
    public SkillTriggerListener(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    private Map<PlayerData, Long> shiftPressTime = new HashMap<>();
    @EventHandler
    private void click(PlayerInteractEvent event){
        PlayerData playerData = new PlayerData(event.getPlayer().getUniqueId().toString(), event.getPlayer().getName(), 0, 0, 0, 0);
        if(event.getAction() == Action.PHYSICAL) return;
        final boolean shift = event.getPlayer().isSneaking();
        final boolean left = event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK;
        final TriggerType type = shift ? (left ? TriggerType.SHIFT_LEFT_CLICK : TriggerType.SHIFT_RIGHT_CLICK) : (left ? TriggerType.LEFT_CLICK : TriggerType.RIGHT_CLICK);
        playerData.castSkill(type);
    }

    @EventHandler
    private void hit(AttackEvent event){
        event.getDamager().sendMessage("damaged!");
    }

    @EventHandler
    private void playerHit(PlayerAttackEvent event){
        event.getPlayer().sendMessage("Damage applied!");
    }

    @EventHandler
    private void entityRight(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = new PlayerData(player.getUniqueId().toString(), player.getName(), 0, 0, 0, 0);
        final boolean shift = event.getPlayer().isSneaking();
        final TriggerType type = shift ? TriggerType.ENTITY_RIGHT_SHIFT : TriggerType.ENTITY_RIGHT;
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
    private void shift(PlayerToggleSneakEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = new PlayerData(player.getUniqueId().toString(), player.getName(), 0, 0, 0, 0);
        if(event.isSneaking())
            playerData.castSkill(TriggerType.SHIFT);
    }

    @EventHandler
    private void reload(ServerLoadEvent event){
        for(Player player : Bukkit.getOnlinePlayers()){
            User_Interface userInterface = new ExpGUI(player);
            userInterface.show();
        }
    }

    @EventHandler
    private void join(PlayerJoinEvent event){
        Player player = event.getPlayer();
        User_Interface userInterface = new ExpGUI(player);
        userInterface.show();
    }

    @EventHandler
    private void left(PlayerQuitEvent event){
        Player player = event.getPlayer();
        User_Interface userInterface = new ExpGUI(player);
        userInterface.disappear();
    }

    @EventHandler
    private void dataChange(ExpChangeEvent event){
        PlayerData playerData = event.getPlayerData();
    }
}
