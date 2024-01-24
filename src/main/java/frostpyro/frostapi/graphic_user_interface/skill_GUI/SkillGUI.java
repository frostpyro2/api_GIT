package frostpyro.frostapi.graphic_user_interface.skill_GUI;

import frostpyro.frostapi.graphic_user_interface.User_Interface;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class SkillGUI implements User_Interface {
    private Player player;
    public SkillGUI(Player player){
        this.player = player;
    }
    @Override
    public void show() {

    }

    @Override
    public void guiType() {

    }

    @Override
    public void disappear() {

    }
}
