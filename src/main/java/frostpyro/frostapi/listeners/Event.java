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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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
        if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR)
            playerData.castSkill(TriggerType.LEFT_CLICK);
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)
            playerData.castSkill(TriggerType.RIGHT_CLICK);
    }
}
