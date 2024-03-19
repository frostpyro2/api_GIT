package frostpyro.frostapi.api.listeners.fake.fakeProvoker;

import frostpyro.frostapi.FrostAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class FakeProvokerListener implements Listener {
    public FakeProvokerListener(FrostAPI plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void fakeDamage(EntityDamageByEntityEvent event){

    }
}
