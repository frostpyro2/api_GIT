package frostpyro.frostapi.skill.entityType;

import java.util.HashMap;
import java.util.Map;

public class EntityType {
    private String type;
    public static EntityType SELF = new EntityType("SELF"),
    NEARBY = new EntityType("NEARBY");

    public EntityType(){

    }
    private final static Map<String, EntityType> ID = new HashMap<>();
    private EntityType(String type){
        this.type = type;
    }

    private static void register(EntityType type){
        ID.put(type.getType(), type);
    }

    static{
        register(SELF);
        register(NEARBY);
    }

    public String getType(){
        return type;
    }
}
