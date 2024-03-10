package frostpyro.frostapi.threads;

import frostpyro.frostapi.dataManage.stat.player.PlayerData;

public class SkillThread implements Runnable{
    private PlayerData playerData;
    public SkillThread(PlayerData playerData){
        this.playerData = playerData;
    }
    @Override
    public void run() {

    }
}
