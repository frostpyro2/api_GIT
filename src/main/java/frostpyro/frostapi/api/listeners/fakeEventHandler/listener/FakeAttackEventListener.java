package frostpyro.frostapi.api.listeners.fakeEventHandler.listener;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.damageManager.DamageType;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.api.listeners.fakeEventHandler.eventHandler.FakeEventHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FakeAttackEventListener implements Listener {
    public FakeAttackEventListener(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void fakeEvent(AttackEvent event){
        if(!event.getPacket().getType().equals(DamageType.DEFAULT))
            event.setCancelled(true);
    }
}
