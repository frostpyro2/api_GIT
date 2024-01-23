package frostpyro.frostapi.graphic_user_interface.EXP_GUI;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.graphic_user_interface.User_Interface;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;

public class ExpGUI implements User_Interface {
    //TODO: get max_val and player's val from SQL or saved data in yml
    //make GUI by boss health bar
    private BossBar expBar = Bukkit.getBossBar(FrostAPI.getPlugin().exp);
    @Override
    public void guiType() {

    }

    @Override
    public void show() {

    }
}
