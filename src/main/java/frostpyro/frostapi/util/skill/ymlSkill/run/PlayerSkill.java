package frostpyro.frostapi.util.skill.ymlSkill.run;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.damageManager.damageData.DamageType;
import frostpyro.frostapi.util.lib.Utility;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

public class PlayerSkill implements Skill{

    private Configuration configuration;
    private final PlayerTriggerData data;

    private final String skillName;

    public PlayerSkill(@NotNull String skillName, PlayerTriggerData data){
        this.skillName = skillName;
        try{
            configuration = Skill.getSkill(skillName);
        }
        catch (Exception any){
            //do nothing
        }
        this.data = data;
    }

    public void activateSkill() {
        if(configuration == null){
            return;
        }
        onCoolDown();
    }

    private void onCoolDown(){
        if(data.getCast().isCoolDown((Configuration) configuration)){
            cancelDamage();
            return;
        }
        coolDownSet();
    }

    private void coolDownSet(){
        double coolDown = .01;
        if(configuration.getDouble("coolDown") != 0) coolDown = configuration.getDouble("coolDown");
        data.getCast().setCoolDown((Configuration) configuration, coolDown);
        run();
    }

    private void run(){
        Utility.runSKill(data, skillName);
    }

    private void cancelDamage(){
        try{
            if(configuration.getBoolean("noDamageOnCoolDown")){
                if(data.getData().getDamage().getDamageType().contains(DamageType.PHYSICAL)){
                    if(data.getData().getDamage().getDamageType().contains(DamageType.PROJECTILE)) data.getData().getDamage().setDamageType(DamageType.PROJECTILESKILL);
                    else data.getData().getDamage().setDamageType(DamageType.MELEESKILL);

                    data.getEvent().setCancelled(true);
                }
            }
        }
        catch (Exception any){
            // do nothing
        }
    }
}
