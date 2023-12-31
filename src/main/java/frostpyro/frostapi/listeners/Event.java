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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class Event implements Listener {
    public Event(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

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
        if(playerData.getPlayer().isSneaking()){
            playerData.castSkill(TriggerType.ATTACK, TriggerType.SHIFT);
            return;
        }
        playerData.castSkill(TriggerType.ATTACK);
    }

    @EventHandler
    private void getHit(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        PlayerData playerData = new PlayerData(player.getUniqueId().toString(), player.getName(), 0, 0, 0, 0);
        if(playerData.getPlayer().isSneaking()){
            playerData.castSkill(TriggerType.DAMAGED, TriggerType.SHIFT);
            return;
        }
        playerData.castSkill(TriggerType.DAMAGED);
    }
}
