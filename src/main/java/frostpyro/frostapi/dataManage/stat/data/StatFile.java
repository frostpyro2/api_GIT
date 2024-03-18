package frostpyro.frostapi.dataManage.stat.data;

import frostpyro.frostapi.FrostAPI;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class StatFile {
    public static Set<String> getStatType(){
        return FrostAPI.getPlugin().stats.getKeys(false);
    }
}
