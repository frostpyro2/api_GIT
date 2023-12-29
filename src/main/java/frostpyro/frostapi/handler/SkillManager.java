package frostpyro.frostapi.handler;

import frostpyro.frostapi.skill.Skill;
import frostpyro.frostapi.skill.trigger.TriggerType;
import org.bukkit.configuration.file.FileConfiguration;

public class SkillManager {
    private FileConfiguration configuration;
    private TriggerType type;

    public SkillManager(FileConfiguration configuration, TriggerType type){
        this.configuration = configuration;
        this.type = type;
    }

    /*
      파일 구조: 예시

      SKILL_NAME:
           SKILL_ID:
           TRIGGER_TYPE:
               -LEFT_CLICK
               -ATTACK
           ATTACK_TYPE: MELEE
           PARTICLE:
               BOOL: true
               PARTICLE_TYPE: FLAME
               SPEED: 0.1
               DIST: 1
               QUANTITY: 3
           DISPLAY:
               BOOL: true
               ITEM: COAL
               CUSTOM_INIT: 1
               FRAME: 7
               SIZE: 5
           DAMAGE:
               BOOL: true
               AMOUNT: 5
           SELF_DAMAGE:
               BOOL: false
               AMOUNT:
           HEAL:
               BOOL: true
               HEAL_TYPE: SELF
               AMOUNT: 1
     */
}
