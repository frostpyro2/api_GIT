package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base.TriggeredConfig;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.script.*;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SkillThread extends BukkitRunnable{
    private TriggeredConfig tc;
    @SuppressWarnings("all")
    private List<? extends SkillRunnable> threads = new ArrayList<>(Arrays.asList(new DamageScript(), new EntityScript(), new ParticleScript(), new SoundScript(), new ExternalSkillScript(), new LoggingScript()));
    public SkillThread(TriggeredConfig tc){
        this.tc = tc;
    }

    @Override
    public void run() {
        if(tc.getSection() == null){
            Bukkit.getConsoleSender().sendMessage("section is null");
            return;
        }
        List<Thread> threadList = Collections.synchronizedList(new ArrayList<>());
        for(SkillRunnable runnable : threads){
            threadList.add(new Thread(()->{
                try{
                    Bukkit.getServer().getScheduler().runTask(FrostAPI.getPlugin(), ()->runnable.run(tc.getData(), tc.getSection()));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }));
        }

        for(Thread t : threadList){
            t.start();
        }

        for(Thread t : threadList){
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
