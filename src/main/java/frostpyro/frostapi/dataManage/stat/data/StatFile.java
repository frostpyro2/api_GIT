package frostpyro.frostapi.dataManage.stat.data;

import frostpyro.frostapi.FrostAPI;

import java.util.List;

public class StatFile {
    public static List<String> getStatType(){
        return FrostAPI.getPlugin().stats.getStringList("stats");
    }
}
