package frostpyro.frostapi.graphic_user_interface.select_GUI;

import frostpyro.frostapi.graphic_user_interface.User_Interface;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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
        player.openInventory(inventory);
    }

    @Override
    public void guiType() {

    }
}
