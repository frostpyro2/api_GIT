package frostpyro.frostapi.skill.trigger;


import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    ENTITY_RIGHT = new TriggerType("ENTITY_RIGHT"),
    ENTITY_RIGHT_SHIFT = new TriggerType("ENTITY_RIGHT_SHIFT"),
    SHIFT_ATTACK = new TriggerType("SHIFT_ATTACK"),
    LEFT_CLICK = new TriggerType("LEFT_CLICK"),
    RIGHT_CLICK = new TriggerType("RIGHT_CLICK"),
    SHIFT_LEFT_CLICK = new TriggerType("SHIFT_LEFT_CLICK"),
    SHIFT_RIGHT_CLICK = new TriggerType("SHIFT_RIGHT_CLICK"),
    PROJECTILE_HIT = new TriggerType("PROJECTILE_HIT"),
    PROJECTILE_TICK = new TriggerType("PROJECTILE_TICK"),
    PROJECTILE_LAND = new TriggerType("PROJECTILE_LAND"),
    DAMAGED = new TriggerType("DAMAGED"),
    SHIFT = new TriggerType("SHIFT"),
    SHIFT_SHIFT = new TriggerType("SHIFT_SHIFT"),
    GENERAL = new TriggerType("GENERAL"),
    KILL_ENTITY = new TriggerType("KILL_ENTITY")
    ;
    private static final Map<String, TriggerType> ID = new HashMap<>();
    private TriggerType(String type){
        this.type = type;
    }
    static {
        register(ATTACK);
        register(LEFT_CLICK);
        register(RIGHT_CLICK);
        register(SHIFT_LEFT_CLICK);
        register(SHIFT_RIGHT_CLICK);
        register(SHIFT_ATTACK);
        register(ENTITY_RIGHT);
        register(ENTITY_RIGHT_SHIFT);

        register(PROJECTILE_HIT);
        register(PROJECTILE_TICK);
        register(PROJECTILE_LAND);

        register(DAMAGED);
        register(SHIFT);
        register(GENERAL);
        register(KILL_ENTITY);
        register(SHIFT_SHIFT);
    }
    public String getType(){
        return this.type;
    }

    public static void register(@Nonnull TriggerType type){
        ID.put(type.getType(), type);
    }

    public static Collection<TriggerType> triggerTypes(){
        return ID.values();
    }


    public boolean equals(String string){
        return Objects.equals(string, type);
    }
}
