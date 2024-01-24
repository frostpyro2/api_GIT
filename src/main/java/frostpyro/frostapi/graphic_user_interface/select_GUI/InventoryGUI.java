package frostpyro.frostapi.graphic_user_interface.select_GUI;

import frostpyro.frostapi.graphic_user_interface.User_Interface;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryGUI implements User_Interface{
    //selecting skill from inventory GUI
    private Player player;
    private Inventory inventory;
    public InventoryGUI(Player player, int size){
        this.player = player;
        inventory = Bukkit.createInventory(player, size, ChatColor.GREEN+""+ChatColor.BOLD+"Skill_select");
    }
    @Override
    public void show() {
        itemSet(new ItemStack(Material.DIAMOND),1);
        player.openInventory(inventory);
    }

    @Override
    public void guiType() {

    }

    @Override
    public void disappear() {

    }

    private void itemSet(ItemStack itemStack, int index){
        inventory.setItem(index, itemStack);
    }
}
