package frostpyro.frostapi.dataManage.stat.data;

import java.util.Objects;

public class StatInstance {
    private StatMap map;
    private String stat;
    public StatInstance(StatMap map, String stat){
        this.map = map;
        this.stat = stat;
    }

    public double getFinal(){
        return Objects.requireNonNull(PlayerFile.getFile(map.getEntity())).getDouble(stat);
    }
}
