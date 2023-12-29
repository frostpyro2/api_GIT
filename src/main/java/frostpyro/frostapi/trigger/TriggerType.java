package frostpyro.frostapi.trigger;


import javax.annotation.Nonnull;
import javax.xml.validation.Validator;
import java.util.HashMap;
import java.util.Map;

public class TriggerType {
    /**
     * attack: attacking entity
     * left_click: left click on air/block
     * right_click: right click on air/block
     */
    private String type;
    public TriggerType(){}

    @Nonnull
    public static TriggerType ATTACK = new TriggerType("ATTACK"),
    LEFT_CLICK = new TriggerType("LEFT_CLICK"),
    RIGHT_CLICK = new TriggerType("RIGHT_CLICK"),
    PROJECTILE_HIT = new TriggerType("PROJECTILE_HIT"),
    PROJECTILE_TICK = new TriggerType("PROJECTILE_TICK"),
    PROJECTILE_LAND = new TriggerType("PROJECTILE_LAND"),
    DAMAGED = new TriggerType("DAMAGED"),
    SHIFT = new TriggerType("SHIFT"),
    GENERAL = new TriggerType("GENERAL")
    ;
    private static final Map<String, TriggerType> ID = new HashMap<>();
    private TriggerType(String type){
        this.type = type;
    }
    static {
        register(ATTACK);
        register(LEFT_CLICK);
        register(RIGHT_CLICK);

        register(PROJECTILE_HIT);
        register(PROJECTILE_TICK);
        register(PROJECTILE_LAND);

        register(DAMAGED);
        register(SHIFT);
        register(GENERAL);
    }
    public String getType(){
        return this.type;
    }

    public static void register(TriggerType type){
        ID.put(type.getType(), type);
    }

}
