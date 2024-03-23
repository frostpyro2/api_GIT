package frostpyro.frostapi.util.skill.armorStand;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArmorStandItem {
    private World world;
    private Location location;
    public ArmorStandItem(World world, Location location){
        this.world = world;
        this.location = location;
    }

    public ArmorStand stand(ItemStack itemStack, int model, boolean gravity, boolean invisible){
        ArmorStand stand = world.spawn(location, ArmorStand.class);
        stand.getEquipment().setHelmet(itemStack);
        stand.setGravity(gravity);
        stand.setMarker(true);
        stand.setInvisible(invisible);
        return stand;
    }

    public void setLocation(Location location){
        this.location = location;
    }
}
