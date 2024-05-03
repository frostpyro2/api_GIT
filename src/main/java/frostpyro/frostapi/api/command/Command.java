package frostpyro.frostapi.api.command;

import frostpyro.frostapi.util.skill.casting.SkillItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.isOp()){
            player.sendMessage(ChatColor.RED + "you must be OP to use this command!");
            return false;
        }

        if(args.length > 0){
            player.sendMessage(ChatColor.GREEN + "item summoned!");
            try{
                Player getter = Bukkit.getPlayer(args[1]);
                if(getter == null) return false;
                getter.getInventory().addItem(SkillItem.skillItemList().get(0));
            }
            catch (Exception any){
                //
            }
            return true;
        }

        player.sendMessage(ChatColor.RED + "failed to summon item!");

        return false;
    }
}
