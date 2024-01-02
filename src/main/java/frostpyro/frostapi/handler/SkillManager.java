package frostpyro.frostapi.handler;

import frostpyro.frostapi.players.PlayerData;
import frostpyro.frostapi.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class SkillManager{
    private ConfigurationSection configuration;
    private TriggerType[] type;
    private PlayerData playerData;

    private Set<Entity> entitySet;

    private boolean trigger = true;
    private boolean item = true;

    public SkillManager(){

    }

    public SkillManager(ConfigurationSection configuration, PlayerData playerData, Set<Entity> entitySet, TriggerType...type){
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
        PARTICLE();
        DISPLAY();
        HEAL();
        DAMAGE();
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
        List<String> temp = new ArrayList<>();
        for(TriggerType template : type){
            temp.add(template.getType());
        }
        List<String> intersect = new ArrayList<>(str);
        intersect.retainAll(temp);
        if(intersect.isEmpty()){
            trigger = false;
        }
    }

    private void DAMAGE(){
        ConfigurationSection section = configuration.getConfigurationSection("DAMAGE");
        if(section == null) return;


        Player player = playerData.getPlayer();
        for(Entity entity : player.getNearbyEntities(3,3,3)){
            if(!(entity instanceof LivingEntity)) continue;

            entitySet.add(entity);
            ((LivingEntity)entity).damage(5, player);
        }
    }

    private void HEAL(){
        ConfigurationSection section = configuration.getConfigurationSection("HEAL");
        if(section == null) return;
        Collection<String> str = section.getKeys(false);
    }

    private void PARTICLE(){
        ConfigurationSection section = configuration.getConfigurationSection("PARTICLE");
        if(section == null) return;
        Collection<String> str = section.getKeys(false);
    }

    private void DISPLAY(){
        ConfigurationSection section = configuration.getConfigurationSection("DISPLAY");
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
           SELF_DAMAGE:
               AMOUNT:
           HEAL:
               HEAL_TYPE: SELF
               AMOUNT: 1
     */
}
