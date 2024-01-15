package frostpyro.frostapi.listeners;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.listeners.customEvents.AttackEvent;
import frostpyro.frostapi.players.PlayerData;
import frostpyro.frostapi.skill.handler.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.*;


public class Event implements Listener {
    private Set<String> uuidSet = new HashSet<>();
    Set<Entity> entitySet = new HashSet<>();
    public Event(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    private Map<PlayerData, Long> shiftPressTime = new HashMap<>();
    @EventHandler
    private void click(PlayerInteractEvent event){
        PlayerData playerData = new PlayerData(event.getPlayer().getUniqueId().toString(), event.getPlayer().getName(), 0, 0, 0, 0);
        uuidSet.add(playerData.getUuid());
        if(event.getAction() == Action.PHYSICAL) return;
        final boolean shift = event.getPlayer().isSneaking();
        final boolean left = event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK;
        final TriggerType type = shift ? (left ? TriggerType.SHIFT_LEFT_CLICK : TriggerType.SHIFT_RIGHT_CLICK) : (left ? TriggerType.LEFT_CLICK : TriggerType.RIGHT_CLICK);
        playerData.castSkill(type);
        uuidSet.remove(playerData.getUuid());
    }

    @EventHandler
    private void hit(AttackEvent event){
        PlayerData playerData = new PlayerData(event.getPlayer().getUniqueId().toString(), event.getPlayer().getName(), 0, 0, 0, 0);
        if(uuidSet.contains(playerData.getUuid())) return;
        final boolean shift = event.getPlayer().isSneaking();
        final TriggerType type = shift ? TriggerType.SHIFT_ATTACK : TriggerType.ATTACK;
        playerData.castSkill(type);
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
}
