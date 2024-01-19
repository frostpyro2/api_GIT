package frostpyro.frostapi.threads;

import org.bukkit.entity.Player;

public class SkillThread implements Runnable{
    private Player player;

    public SkillThread(Player player){
        this.player = player;
    }

    @Override
    public void run() {

    }
}
