package frostpyro.frostapi.listeners;

import frostpyro.frostapi.FrostAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;


public class Casting implements Listener {
    public Casting(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }


}
