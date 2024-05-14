package frostpyro.frostapi.api.command;

import frostpyro.frostapi.util.skill.casting.SkillItem;
import frostpyro.frostapi.util.skill.container.SkillItemContainer;
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
            try{
                Player getter = Bukkit.getPlayer(args[0]);
                if(getter == null){
                    player.sendMessage(ChatColor.RED + "player does not exist!");
                    return false;
                }
                int id;
                try{
                    id = Integer.parseInt(args[1]);
                }
                catch (Exception any){
                    player.sendMessage(ChatColor.RED + "id is not available!");
                    return false;
                }
                try{
                    getter.getInventory().addItem(SkillItemContainer.getItemContainer(id).getStack());
                }
                catch (Exception any){
                    player.sendMessage(ChatColor.RED + "no item has this id!");
                }
                player.sendMessage(ChatColor.GREEN + "item summoned!");
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
