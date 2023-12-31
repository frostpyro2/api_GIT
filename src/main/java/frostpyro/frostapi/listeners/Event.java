package frostpyro.frostapi.listeners;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.players.PlayerData;
import frostpyro.frostapi.skill.Skill;
import frostpyro.frostapi.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Event implements Listener {
    public Event(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    private Map<PlayerData, Long> shiftPressTime = new HashMap<>();
    @EventHandler
    private void click(PlayerInteractEvent event){
        PlayerData playerData = new PlayerData(event.getPlayer().getUniqueId().toString(), event.getPlayer().getName(), 0, 0, 0, 0);
        final boolean shift = event.getPlayer().isSneaking();
        final boolean left = event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK;
        final TriggerType type = shift ? (left ? TriggerType.SHIFT_LEFT_CLICK : TriggerType.SHIFT_RIGHT_CLICK) : (left ? TriggerType.LEFT_CLICK : TriggerType.RIGHT_CLICK);
        playerData.castSkill(type);
    }

    @EventHandler
    private void hit(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        PlayerData playerData = new PlayerData(player.getUniqueId().toString(), player.getName(), 0, 0, 0, 0);
        final boolean shift = playerData.getPlayer().isSneaking();
        final TriggerType type = shift ? TriggerType.SHIFT_ATTACK : TriggerType.ATTACK;
        playerData.castSkill(type);
    }

    @EventHandler
    private void getHit(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
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
