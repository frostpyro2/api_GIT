package frostpyro.frostapi.graphic_user_interface.EXP_GUI;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.graphic_user_interface.User_Interface;
import org.bukkit.Bukkit;
import org.bukkit.boss.*;
import org.bukkit.entity.Player;

public class ExpGUI implements User_Interface {
    //TODO: get max_val and player's val from SQL or saved data in yml
    //make GUI by boss health bar

    private Player player;
    public ExpGUI(Player player){
        this.player = player;
    }
    private KeyedBossBar expBar = Bukkit.createBossBar(FrostAPI.getPlugin().exp, "EXP", BarColor.YELLOW, BarStyle.SOLID);
    @Override
    public void guiSetting() {

    }

    @Override
    public void show() {
        expBar.setProgress(0.5);
        expBar.addPlayer(player);
    }

    @Override
    public void disappear() {
        expBar.removePlayer(player);
    }
}
