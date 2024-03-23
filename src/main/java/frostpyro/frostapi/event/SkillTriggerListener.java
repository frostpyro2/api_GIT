package frostpyro.frostapi.event;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.player.PlayerAttackEvent;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.dataManage.stat.player.EquipSlot;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.*;


public class SkillTriggerListener implements Listener {
    public SkillTriggerListener(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    private void click(PlayerInteractEvent event){
        if(event.getHand() == null) return;
        if(event.getAction() == Action.PHYSICAL) return;
        final PlayerDataTmp caster = PlayerDataTmp.get(event.getPlayer().getUniqueId());
        final boolean isLeft = event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK;
        final boolean isSneaking = event.getPlayer().isSneaking();
        final TriggerType type = isLeft ? (isSneaking ? TriggerType.SHIFT_LEFT_CLICK : TriggerType.LEFT_CLICK) : (isSneaking ? TriggerType.SHIFT_RIGHT_CLICK : TriggerType.RIGHT_CLICK);
        final TriggerData data = new TriggerData(caster, type, EquipSlot.fromBukkit(event.getHand()), null, null, null, null, new PlayerData(caster, EquipSlot.fromBukkit(event.getHand())));
        caster.castSkill(data);
    }

    @EventHandler
    private void hit(AttackEvent event){
        if(event.getAttack().getAttacker() == null) return;
        event.getEntity().sendMessage("damaged!");
    }

    @EventHandler
    private void playerHit(PlayerAttackEvent event){
        TriggerData data = new TriggerData(event, TriggerType.ATTACK);
        data.getCast().castSkill(data);
    }

    @EventHandler
    private void entityRight(PlayerInteractAtEntityEvent event){

    }

    @EventHandler
    private void test(EntityDamageEvent event){
        if(event.getCause() == EntityDamageEvent.DamageCause.CUSTOM)
            Bukkit.getConsoleSender().sendMessage("customDamage");
    }

    @EventHandler
    private void getHit(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player player)) return;
    }

    @EventHandler
    private void shift_twice(PlayerToggleSneakEvent event){

    }

    @EventHandler
    private void shift(PlayerToggleSneakEvent event) {

    }

    @EventHandler
    private void test(PlayerInteractEvent event){
        if(event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) return;
        Player player = event.getPlayer();
        ArmorStand stand = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
        stand.setVisible(false);
        stand.setGravity(false);
        stand.setInvulnerable(true);
        stand.getEquipment().setHelmet(new ItemStack(Material.PAPER));
        stand.setMarker(true);
    }
}
