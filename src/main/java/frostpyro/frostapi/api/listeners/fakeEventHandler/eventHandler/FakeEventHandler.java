package frostpyro.frostapi.api.listeners.fakeEventHandler.eventHandler;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeEventHandler {
    private final Map<Class<?>, List<FakeEventCaller>> eventMap = new HashMap<>();
    public FakeEventHandler(){
        register(EntityDamageEvent.class, damage -> damage.getDamage() == 0);
        register(EntityDamageEvent.class, damage -> damage instanceof FakeEntityDamageByEntityEvent);
    }

    public <E extends Event> void register(Class<E> eventClass, FakeEventCaller<E> fakeEvent){
        this.eventMap.computeIfAbsent(eventClass, unused -> new ArrayList<>()).add(fakeEvent);
    }

    public boolean isFake(@NotNull Event event){
        for(Map.Entry<Class<?>, List<FakeEventCaller>> entry : eventMap.entrySet())
            if(entry.getKey().isInstance(event))
                for (FakeEventCaller caller : entry.getValue()) if(caller.isFake(event)) return true;
        return false;
    }
}
