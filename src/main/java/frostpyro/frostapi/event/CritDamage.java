package frostpyro.frostapi.event;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.dataManage.stat.StatProvider;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Random;

public class CritDamage implements Listener {

    public CritDamage(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void crit(AttackEvent event){
        Random random = new Random();
        double chance = random.nextDouble();
        if(event.getAttack().getAttacker() == null) return;
        final StatProvider provider = event.getAttack().getAttacker();
        double critChance = provider.getStat("CRIT_CHANCE") / 100;
        if(critChance < chance) return;
        event.bukkit().setDamage(event.bukkit().getDamage() * 2);
    }
}
