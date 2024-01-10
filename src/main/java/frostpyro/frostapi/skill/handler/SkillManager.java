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
               DELAY: 1
               AMOUNT: 5
               POINT:
                - DISPLAY
                - LOCATION
                - SELF
               INIT_DIST: 10
               ENTITY: NEARBY
           SELF_DAMAGE:
               DELAY:
               AMOUNT:
           HEAL:
               ENTITY: SELF
               AMOUNT: 1
               POINT:
                - DISPLAY
                - LOCATION
                - SELF
          ACTION:

     */
}
