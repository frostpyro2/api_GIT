package frostpyro.frostapi.handler;

import frostpyro.frostapi.skill.trigger.TriggerType;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Collection;
import java.util.List;

public abstract class SkillManager{
    private ConfigurationSection configuration;
    private TriggerType type;

    private boolean trigger = true;

    public SkillManager(){

    }

    public SkillManager(ConfigurationSection configuration, TriggerType type){
        this.configuration = configuration;
        this.type = type;
    }

    protected int coolDown = 0;

    public abstract void skillActivate();
    public void activateSkill(){
        TRIGGER_TYPE();
        if(!trigger) return;
        COOL_DOWN();
        PARTICLE();
        DISPLAY();
        HEAL();
        DAMAGE();
    }


    private void TRIGGER_TYPE() {
        ConfigurationSection section = configuration.getConfigurationSection("TRIGGER_TYPE");
        if(section == null) return;
        List<String> str = section.getStringList("TRIGGER_TYPE");
        if(!str.contains(type.getType())) trigger = false;
    }

    private void DAMAGE(){
        ConfigurationSection section = configuration.getConfigurationSection("DAMAGE");
        if(section == null) return;
        Collection<String> str = section.getKeys(false);
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

    private void COOL_DOWN(){
        ConfigurationSection section = configuration.getConfigurationSection("COOL_DOWN");
        if(section == null) return;
        coolDown = section.getInt("COOL_DOWN");
    }
    /*
      파일 구조: 예시

      SKILL_NAME:
           SKILL_ID:
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
