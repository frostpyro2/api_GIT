package frostpyro.frostapi.handler;

import frostpyro.frostapi.players.PlayerData;
import frostpyro.frostapi.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Collection;
import java.util.List;

public abstract class SkillManager{
    private ConfigurationSection configuration;
    private TriggerType type;
    private PlayerData playerData;

    private boolean trigger = true;

    public SkillManager(){

    }

    public SkillManager(ConfigurationSection configuration, TriggerType type, PlayerData playerData){
        this.configuration = configuration;
        this.type = type;
        this.playerData = playerData;

    }

    protected int coolDown;

    public abstract void skillActivate();
    public void activateSkill(){
        TRIGGER_TYPE();
        if(!trigger){
            return;
        }
        TEST();
        COOL_DOWN();
        PARTICLE();
        DISPLAY();
        HEAL();
        DAMAGE();
    }
    private void TEST(){
        String str = configuration.getString("TEST");
        if(str == null){
            Bukkit.getLogger().info(ChatColor.RED+ "FUCK YOU");
            return;
        }
        playerData.getPlayer().sendMessage(str);
    }

    private void TRIGGER_TYPE() {
        List<String> str = configuration.getStringList("TRIGGER_TYPE");
        if(!str.contains(type.getType())){
            Bukkit.getConsoleSender().sendMessage(str.get(0));
            Bukkit.getLogger().info(ChatColor.RED+ "WHY?????????????");
            trigger = false;
        }
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
        coolDown = configuration.getInt("COOL_DOWN");
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
