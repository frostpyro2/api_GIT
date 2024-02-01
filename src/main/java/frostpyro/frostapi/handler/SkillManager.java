package frostpyro.frostapi.handler;

import frostpyro.frostapi.players.PlayerData;
import frostpyro.frostapi.handler.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class SkillManager{
    private ConfigurationSection configuration;
    private TriggerType type;
    private PlayerData playerData;

    private boolean trigger = true;
    private boolean item = true;
    private int particle_num = 0;
    private ItemDisplay display = null;

    public SkillManager(){

    }

    public SkillManager(ConfigurationSection configuration, PlayerData playerData, TriggerType type){
        this.configuration = configuration;
        this.type = type;
        this.playerData = playerData;
    }

    protected int coolDown = 0;

    public abstract void skillActivate();
    public void activateSkill(){
        ITEM();
        if(!item){
            return;
        }
        TRIGGER_TYPE();
        if(!trigger){
            return;
        }
        TEST();
        DAMAGE_APPLY();
    }
    private void TEST(){
        String str = configuration.getString("TEST");
        if(str == null){
            return;
        }
        playerData.getPlayer().sendMessage(str);
    }

    private void ITEM(){

    }

    private void TRIGGER_TYPE() {
        List<String> str = configuration.getStringList("TRIGGER_TYPE");
        if(!str.contains(type.getType())){
            trigger = false;
        }
    }

    private void DAMAGE_APPLY(){
        Player player = playerData.getPlayer();
        for(Entity entity : player.getNearbyEntities(3,3,3)){
            if(!(entity instanceof LivingEntity)) continue;
            ((LivingEntity)entity).damage(5, player);
        }
    }

    private void HEAL_APPLY(){

    }


    protected int COOL_DOWN(){
        if(configuration.get("COOL_DOWN") == null){
            Bukkit.getConsoleSender().sendMessage("this is null");
            return 0;
        }

        return configuration.getInt("COOL_DOWN");
    }
    /*

      SKILL NAME:
        ID:
        ITEM:
        TRIGGER_TYPE:
        COOL_DOWN:
        CASTER:
            HEAL:

        TARGET:

            VISUAL_EFFECT:

            DAMAGE:

            JUMP:



        VISUAL_EFFECT:
            PARTICLE:

            //OR

            ITEM_DISPLAY:
     */
}
