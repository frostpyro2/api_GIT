package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base.TriggeredConfig;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.script.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SkillThread extends BukkitRunnable{
    private TriggeredConfig tc;
    private int delay = 0;
    private final List<? extends SkillRunnable> threads = new ArrayList<>(Arrays.asList(new DamageScript(), new EntityScript(), new ParticleScript(), new SoundScript(), new ExternalSkillScript()));
    public SkillThread(TriggeredConfig tc){
        this.tc = tc;
    }

    public SkillThread(TriggerData data, ConfigurationSection section){
        tc = new TriggeredConfig(data, section);
    }

    @Override
    public void run() {
        if(tc.getSection() == null) return;
        for(String key: tc.getSection().getKeys(false)){
            scriptSection(key);
        }
    }

    private void modifyDelay(ConfigurationSection section){
        if(section == null) return;
        if(section.contains("addDelay")){
            delay += section.getInt("addDelay");
            return;
        }
        if(section.contains("setDelay")){
            delay = section.getInt("setDelay");
            return;
        }
    }

    private void scriptSection(String key){

        int location = -2;

        switch(key){
            case "delay"-> location = -1;
            case "damage"->location = 0;
            case "entity"->location = 1;
            case "particle"->location = 2;
            case "sound"->location = 3;
            case "external"->location = 4;
        }

        if(location == -2) return;


        if(location == -1){
            modifyDelay(tc.getSection().getConfigurationSection(key));
            return;
        }

        int finalLocation = location;
        Bukkit.getServer().getScheduler().runTaskLater(FrostAPI.getPlugin(), ()->threads.get(finalLocation).run(tc.getData(), tc.getSection().getConfigurationSection(key)), delay);
    }
}
