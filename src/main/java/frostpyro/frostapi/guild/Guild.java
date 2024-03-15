package frostpyro.frostapi.guild;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.stat.StatProvider;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Guild {
    private StatProvider guildMaster;
    private List<StatProvider> guildMember;
    private String masterName;
    private UUID masterUUID;

    public Guild(){

    }

    public Guild(StatProvider guildMaster, List<StatProvider> guildMember){
        this.guildMaster = guildMaster;
        this.guildMember = guildMember;
        masterName = guildMaster.getName();
        masterUUID = guildMaster.getUuid();
    }

    private static List<StatProvider> statProviders(FileConfiguration configuration){
        List<StatProvider> temp = new ArrayList<>();
        for(String string : configuration.getStringList("members")){
            String[] tempStr = string.split("_");
            temp.add(StatProvider.get(UUID.fromString(tempStr[1])));
        }
        return temp;
    }

    public StatProvider getGuildMaster() {
        return guildMaster;
    }

    public void setGuildMaster(StatProvider guildMaster) {
        this.guildMaster = guildMaster;
    }

    public List<StatProvider> getGuildMember() {
        return guildMember;
    }

    public void setGuildMember(List<StatProvider> guildMember) {
        this.guildMember = guildMember;
    }

    public void deleteGuildMember(StatProvider deleteMember){
        guildMember.remove(deleteMember);
    }

    public void addGuildMember(StatProvider newGuildMember) {
        guildMember.add(newGuildMember);
    }

    public boolean isGuildMember(StatProvider member){
        return guildMember.contains(member);
    }

    public static Guild get(StatProvider master){
        File file = new File(FrostAPI.getPlugin().getDataFolder(), "guild\\" + master.getName()+"_"+master.getUuid()+".yml");
        if(!file.exists()) return null;
        FileConfiguration configuration = new YamlConfiguration();
        try{
            configuration.load(file);
        }
        catch (IOException | InvalidConfigurationException e){
            return null;
        }

        return new Guild(master, statProviders(configuration));
    }

    public static void create(StatProvider master){

    }
}
