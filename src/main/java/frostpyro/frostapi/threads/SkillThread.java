package frostpyro.frostapi.threads;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.player.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class SkillThread implements Runnable{
    private PlayerData playerData;
    public SkillThread(PlayerData playerData){
        this.playerData = playerData;
    }
    @Override
    public void run() {

    }
}
