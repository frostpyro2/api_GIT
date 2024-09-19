package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script;

import frostpyro.frostapi.FrostAPI;
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
    private boolean priority = true;
    public SkillThread(TriggeredConfig tc){
        this.tc = tc;
    }

    public SkillThread(TriggeredConfig tc, boolean priority){
        this.tc = tc;
        this.priority = priority;
    }

    @Override
    public void run() {
        for(String ignored : tc.getSection().getKeys(false)){

            if(priority) modifyDelay(tc.getSection().getConfigurationSection("delay"));

            scriptSection(0, "damage");
            scriptSection(1, "entity");
            scriptSection(2, "particle");
            scriptSection(3, "sound");
            scriptSection(4, "external");
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

    private void scriptSection(int location, String path){
        Bukkit.getServer().getScheduler().runTaskLater(FrostAPI.getPlugin(), ()->threads.get(location).run(tc.getData(), tc.getSection().getConfigurationSection(path)), delay);
    }
}
