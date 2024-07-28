package frostpyro.frostapi;

import frostpyro.frostapi.api.command.Command;
import frostpyro.frostapi.api.damageManager.damageData.DamageManage;
import frostpyro.frostapi.api.listeners.fake.FakeEventManager;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import frostpyro.frostapi.event.CritDamage;
import frostpyro.frostapi.event.DataListener;
import frostpyro.frostapi.event.SkillTriggerListener;
import frostpyro.frostapi.api.listeners.customEventListener.AttackEventListener;
import frostpyro.frostapi.util.skill.SkillManager;
import frostpyro.frostapi.util.skill.casting.SkillItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class FrostAPI extends JavaPlugin {
    private static FrostAPI plugin;
    private DamageManage damageManage;
    private final FakeEventManager fakeEventManager = new FakeEventManager();
    @Override
    public void onEnable() {
        plugin = this;
        loadConfigs();
        // Plugin startup logic

        generateFolders();

        new DataListener(this);
        new AttackEventListener(this);
        new SkillTriggerListener(this);
        new CritDamage(this);

        for(Player player : Bukkit.getOnlinePlayers()){
            PlayerDataTmp.upload(player.getUniqueId(), new PlayerDataTmp(player.getUniqueId()));
        }

        damageManage = new DamageManage();

        SkillItem.registerItem();
        try{
            this.getCommand("skill").setExecutor(new Command());
        }
        catch (Exception any){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "command threw NPE!");
        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "PLUGIN ACTIVATED SUCCESSFULLY: "+getDescription().getName());
    }

    @Override
    public void onDisable() {
        for(Player player : Bukkit.getOnlinePlayers()){
            PlayerDataTmp.flush(player.getUniqueId());
        }
        // Plugin shutdown logic
    }
    public static FrostAPI getPlugin(){
        return plugin;
    }

    public YamlConfiguration skillF, item, mobs;
    private void generateSkillFolder(){
        File FOLDER = new File(getDataFolder(), "\\skill");
        if(FOLDER.exists()) return;
        FOLDER.mkdirs();
    }

    private void generateSkillBuildFolder(){
        File FOLDER = new File(getDataFolder(), "\\skillBuild");
        if(FOLDER.exists()) return;
        FOLDER.mkdirs();
    }

    private void generatePlayerDataFolder(){
        File FOLDER = new File(getDataFolder(), "\\player");
        if(FOLDER.exists()) return;
        FOLDER.mkdirs();
    }

    private void generateItemFolder(){
        File FOLDER = new File(getDataFolder(), "\\skill\\item");
        if(FOLDER.exists()) return;
        FOLDER.mkdirs();
    }

    private void generateYmlSkillFolder(){
        File FOLDER = new File(getDataFolder(), "\\skill\\skills");
        if(FOLDER.exists()) return;
        FOLDER.mkdirs();
    }

    private void generateArmorEntityFolder(){
        File FOLDER = new File(getDataFolder(), "\\skill\\entity");
        if(FOLDER.exists()) return;
        FOLDER.mkdirs();
    }

    private void generateEffectItem(){
        File FOLDER = new File(getDataFolder(), "\\skill\\effectItem");
        if(FOLDER.exists()) return;
        FOLDER.mkdirs();
    }

    private void skillYml(){
        File YML = new File(getDataFolder(), "\\skill\\skill.yml");
        if(YML.exists()) {
            skillF = new YamlConfiguration();
            try{
                skillF.load(YML);
            }
            catch (Exception any){
                //nothing
            }
            return;
        }
        YML.mkdirs();
        skillF = new YamlConfiguration();
        try{
            skillF.load(YML);
        }
        catch (Exception any){
            //nothing
        }
    }

    private void generateFolders(){
        generateSkillFolder();
        generateSkillBuildFolder();
        generatePlayerDataFolder();
        generateItemFolder();
        generateYmlSkillFolder();
        generateArmorEntityFolder();
        generateEffectItem();
        skillYml();
    }

    public FileConfiguration skill, build, sql, stats;
    private void loadConfigs(){
        File skill = new File(getDataFolder(), "skillName.yml"), build = new File(getDataFolder(), "skillBuildName.yml"), sql = new File(getDataFolder(), "SQLInform.yml"), stats = new File(getDataFolder(), "stats.yml");
        if(!skill.exists()){
            skill.getParentFile().mkdirs();
            saveResource("skillName.yml", false);
        }
        if(!build.exists()){
            build.getParentFile().mkdirs();
            saveResource("skillBuildName.yml", false);
        }
        if(!sql.exists()){
            sql.getParentFile().mkdirs();
            saveResource("SQLInform.yml", false);
        }
        if(!stats.exists()){
            stats.getParentFile().mkdirs();
            saveResource("stats.yml", false);
        }
        this.skill = new YamlConfiguration();
        this.build = new YamlConfiguration();
        this.sql = new YamlConfiguration();
        this.stats = new YamlConfiguration();
        try{
            this.skill.load(skill);
            this.build.load(build);
            this.sql.load(sql);
            this.stats.load(stats);
        }
        catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
            return;
        }
        getLogger().info("config enabled!");
    }

    public NamespacedKey customEntity = new NamespacedKey(this, "customEntity");

    public DamageManage damage(){
        return damageManage;
    }

    public FakeEventManager fakeEventManager(){
        return fakeEventManager;
    }
}
