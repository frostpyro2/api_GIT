package frostpyro.frostapi.skill.handler;

import frostpyro.frostapi.players.PlayerData;
import frostpyro.frostapi.skill.handler.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.io.ObjectInputFilter;
import java.util.*;

public abstract class SkillManager{
    private ConfigurationSection configuration;
    private TriggerType type;
    private PlayerData playerData;

    private Set<Entity> entitySet;

    private boolean trigger = true;
    private boolean item = true;
    private int particle_num = 0;
    private ItemDisplay display = null;

    public SkillManager(){

    }

    public SkillManager(ConfigurationSection configuration, PlayerData playerData, Set<Entity> entitySet, TriggerType type){
        this.configuration = configuration;
        this.type = type;
        this.playerData = playerData;
        this.entitySet = entitySet;
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
        HEAL();
        DAMAGE_N_EFFECT();
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

    private void DAMAGE_N_EFFECT(){
        ConfigurationSection section = configuration.getConfigurationSection("DAMAGE");
        if(section == null) return;
        int amount = section.getInt("AMOUNT");
        double distance = section.getDouble("INIT_DIST");
        boolean all = section.getBoolean("TARGET_ALL");
        int targetTime = section.getInt("TARGET_TIME");
        String point = section.getString("POINT");

        Player player = playerData.getPlayer();
        int x = 1;
        for(Entity entity : player.getNearbyEntities(distance, distance, distance)){
            if(!(entity instanceof LivingEntity)) continue;
            double dist = 5;
            if(configuration.getConfigurationSection("SELF_DAMAGE") == null){
                if(entity == player){
                    continue;
                }
            }



            entitySet.add(entity);
            ((LivingEntity)entity).damage(amount, player);
            x++;
            if(!all && x > targetTime){
                break;
            }
        }
    }

    private void HEAL(){
        ConfigurationSection section = configuration.getConfigurationSection("HEAL");
        if(section == null) return;
        Collection<String> str = section.getKeys(false);
    }

    protected int COOL_DOWN(){
        if(configuration.get("COOL_DOWN") == null){
            Bukkit.getConsoleSender().sendMessage("this is null");
            return 0;
        }

        return configuration.getInt("COOL_DOWN");
    }
    /*
      파일 구조: 예시

      SKILL_NAME:
           TEST: hello world!
           SKILL_ID:
           ITEM:
                MATERIAL: NETHERITE_SWORD
                MODEL: 1
           TRIGGER_TYPE:
               -LEFT_CLICK
               -ATTACK
           ATTACK_TYPE: MELEE
           COOL_DOWN: 5
           PARTICLE:
               PARTICLE_TYPE: FLAME
               SPEED: 0.1
               DIST: 1
               QUANTITY: 3
           DISPLAY:
               ITEM: COAL
               CUSTOM_INIT: 1
               FRAME: 7
               SIZE: 5
           DAMAGE:
               AMOUNT: 5
               POINT:
                - SELF
                - DISPLAY
                - LOCATION
                - NEARBY
               INIT_DIST: 10
               ENTITY: NEARBY
           SELF_DAMAGE:
               AMOUNT:
           HEAL:
               ENTITY: SELF
               AMOUNT: 1
               POINT:
                - SELF
                - NEARBY
                - DISPLAY
                - LOCATION
     */
}
