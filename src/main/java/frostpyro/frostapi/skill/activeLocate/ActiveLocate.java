package frostpyro.frostapi.skill.activeLocate;


import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ActiveLocate {
    public ActiveLocate(){

    }

    private String type;
    private static Map<String, ActiveLocate> ID = new HashMap<>();
    private ActiveLocate(String type){
        this.type = type;
    }

    @Nonnull
    public static ActiveLocate PARTICLE = new ActiveLocate("PARTICLE"),
    SELF = new ActiveLocate("SELF"),
    DISPLAY = new ActiveLocate("DISPLAY"),
    CLOSEST = new ActiveLocate("CLOSEST");

    static {
        register(PARTICLE);
        register(SELF);
        register(DISPLAY);
        register(CLOSEST);
    }

    private static void register(ActiveLocate object){
        ID.put(object.getType(), object);
    }

    public String getType(){
        return this.type;
    }

    public ActiveLocate getObject(String type){
        return ID.get(type);
    }

    public Collection<ActiveLocate> locateTypes(){
        return ID.values();
    }
}
