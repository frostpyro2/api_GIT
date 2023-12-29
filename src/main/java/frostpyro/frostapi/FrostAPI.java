package frostpyro.frostapi;

import frostpyro.frostapi.listeners.Casting;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class FrostAPI extends JavaPlugin {
    private static FrostAPI plugin;
    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        generateSkillFolder();
        generateSkillBuildFolder();

        loadConfigs();

        new Casting(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static FrostAPI getPlugin(){
        return plugin;
    }
    public void generateSkillFolder(){
        File FOLDER = new File(getDataFolder(), "\\skill");
        if(FOLDER.exists()) return;
        FOLDER.mkdirs();
    }

    public void generateSkillBuildFolder(){
        File FOLDER = new File(getDataFolder(), "\\skillBuild");
        if(FOLDER.exists()) return;
        FOLDER.mkdirs();
    }

    public void loadConfigs(){

    }

}
