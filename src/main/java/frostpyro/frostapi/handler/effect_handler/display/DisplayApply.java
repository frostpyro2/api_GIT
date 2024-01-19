package frostpyro.frostapi.handler.effect_handler.display;

import frostpyro.frostapi.handler.effect_handler.TargetEffect;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DisplayApply extends TargetEffect<ItemDisplay> {
    private ItemStack itemStack;
    private int startPoint;
    private int frame;

    private ItemMeta meta;
    public DisplayApply(ItemDisplay itemDisplay, ItemStack itemStack, int startPoint) {
        super(itemDisplay);
        this.itemStack = itemStack;
        this.startPoint = startPoint;
    }
    public void setFrame(int frame){
        this.frame = frame;
    }

    public void setItemMeta(ItemMeta itemMeta){
        this.meta = itemMeta;
    }
    @Override
    public void action() {
        itemStack.setItemMeta(meta);
        super.getObj().setItemStack(itemStack);
    }
}
