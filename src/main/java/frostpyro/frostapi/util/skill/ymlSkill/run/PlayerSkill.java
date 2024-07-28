package frostpyro.frostapi.util.skill.ymlSkill.run;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.damageManager.damageData.DamageType;
import frostpyro.frostapi.util.lib.Utility;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

public class PlayerSkill implements Skill{

    private ConfigurationSection configuration;
    private final PlayerTriggerData data;

    public PlayerSkill(@NotNull String skillName, PlayerTriggerData data){
        try{
            configuration = FrostAPI.getPlugin().skillF.getConfigurationSection(skillName);
        }
        catch (Exception any){
            //do nothing
        }
        this.data = data;
    }

    public void activateSkill() {
        if(configuration == null) return;
        firstFloor();
    }

    private void firstFloor(){
        if(data.getCast().isCoolDown((Configuration) configuration)){
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
            return;
        }
        secondFloor();
    }

    private void secondFloor(){
        double coolDown = .01;
        if(configuration.getDouble("coolDown") != 0) coolDown = configuration.getDouble("coolDown");
        data.getCast().setCoolDown((Configuration) configuration, coolDown);
        thirdFloor();
    }

    private void thirdFloor(){
        Utility.runSKill(data, configuration);
    }
}
