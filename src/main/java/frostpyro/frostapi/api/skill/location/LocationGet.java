package frostpyro.frostapi.api.skill.location;

import org.bukkit.Location;

public abstract class LocationGet {
    protected Location location;
    public abstract void setLocation(Location location);

    public Location getLocation() {
        return location;
    }
}
