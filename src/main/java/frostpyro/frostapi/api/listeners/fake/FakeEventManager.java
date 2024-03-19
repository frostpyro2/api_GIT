package frostpyro.frostapi.api.listeners.fake;

import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeEventManager {
    private final Map<Class<?>, List<FakeEventCaller>> callers = new HashMap<>();

    public FakeEventManager(){
        registerFakeEvent(EntityDamageEvent.class, damageEvent -> damageEvent.getDamage() == 0);
        registerFakeEvent(EntityDamageEvent.class, damageEvent -> damageEvent instanceof FakeEntityDamagedByEntityEvent);
        registerFakeEvent(BlockBreakEvent.class, event -> event instanceof FakeBlockBreakEvent);
    }


    public <E extends Event> void registerFakeEvent(Class<E> event, @NotNull FakeEventCaller<E> caller){
        this.callers.computeIfAbsent(event, ununsed -> new ArrayList<>()).add(caller);
    }

    public boolean isFake(@NotNull Event event){
        for(Map.Entry<Class<?>, List<FakeEventCaller>> entry : this.callers.entrySet()){
            if(entry.getKey().isInstance(event))
                for(FakeEventCaller caller : entry.getValue()) if(caller.isFake(event)) return true;
        }
        return false;
    }
}
