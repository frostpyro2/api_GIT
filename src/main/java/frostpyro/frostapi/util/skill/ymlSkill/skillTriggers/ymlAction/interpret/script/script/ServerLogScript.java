package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.script;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Map;

public class ServerLogScript implements SkillRunnable{
    private int delay = 0;
    @Override
    public void run(TriggerData data, ConfigurationSection section) {
        Bukkit.getServer().getConsoleSender().sendMessage("slog");
        if(section == null) return;
        List<?> scripts = section.getList("serverLogging");
        if(scripts == null) return;
        for(Object script : scripts){
            if(script instanceof Map<?, ?> mapScript){
                Bukkit.getServer().getScheduler().runTaskLater(FrostAPI.getPlugin(), ()->{
                    serverSendMessage(data, mapScript);
                }, delay);
                setDelay(data, mapScript);
            }
        }
    }

    private void serverSendMessage(TriggerData data, Map<?, ?> script){
        String message = (String) script.get("message");
        try{
            Bukkit.getConsoleSender().sendMessage(data.getCast().getEntity().getName() + " : " + message);
        }
        catch (Exception any){
            //
        }
    }

    private void setDelay(TriggerData data, Map<?, ?> script){
        if(!script.containsKey("delay")) return;
        Map<?, ?> inner = (Map<?, ?>) script.get("delay");
        try{
            if(inner.containsKey("addDelay")){
                delay += (int)inner.get("addDelay");
                return;
            }
            delay = (int)inner.get("setDelay");
        }
        catch (Exception any){
            //
        }
    }
}
