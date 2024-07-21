package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.components;

import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base.ArmorStandDisplay;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base.Targeting;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class ArmorStandYml implements ComponentYml{
    private ArmorStandDisplay display;
    private ArmorStandDisplay.ArmorStandInit setting;
    private Targeting targeting;
    private FileConfiguration skill;
    public ArmorStandYml(ArmorStandDisplay display, FileConfiguration skill){
        this.display = display;
        targeting = new Targeting(display.getStand());
        setting = new ArmorStandDisplay.ArmorStandInit(display.getStand());
        this.skill = skill;
    }
    @Override
    public void activation(String path) {
        List<?> scripts = skill.getList(path);
        if(scripts == null) return;
        for(Object script : scripts){
            mapScript(script);
            stringScript(script);
        }
    }


    private void mapScript(Object script){
        if(!(script instanceof Map<?,?> mapScript)) return;
        spawn(mapScript);
        setItem(mapScript);
    }

    private void stringScript(Object script){
        if(!(script instanceof String strScript)) return;
        switch (strScript){
            case "delete"-> delete();
        }
    }

    private void delete(){
        setting.remove();
    }

    private void spawn(Map<?, ?> script){
        if(!script.containsKey("spawn")) return;
        Map<?, ?> mapScript = (Map<?, ?>) script.get("spawn");
        try{
            display.spawnStand();
            setting.isSmall((Boolean) mapScript.get("isSmall"));
            setting.hasPlate((Boolean) mapScript.get("hasPlate"));
            setting.isGravity((Boolean) mapScript.get("isGravity"));
            setting.isInvisible((Boolean) mapScript.get("isInvisible"));
            setting.isMarker((Boolean) mapScript.get("isMarker"));
        }
        catch (Exception any){

        }
    }

    private void setItem(Map<?, ?> script){
        if(!script.containsKey("setItem")) return;
        Map<?, ?> mapScript = (Map<?, ?>) script.get("setItem");
        try{
            display.setItem(ArmorStandDisplay.ItemLocation.valueOf((String) mapScript.get("location")), displayItem((Integer) mapScript.get("customModelData"), Material.valueOf((String) mapScript.get("material"))));
        }
        catch (Exception any){
            //do nothing
        }
    }

    private ItemStack displayItem(Integer customModel, Material material){
        ItemStack stack = new ItemStack(material);
        ItemMeta  meta = stack.getItemMeta();
        if(meta == null) throw new IllegalArgumentException();
        meta.setCustomModelData(customModel);
        stack.setItemMeta(meta);
        return stack;
    }

    private void targeting(Targeting.Target target){
        switch (target){
            case self -> display.setStand(display.getStand());
            case closest -> display.setStand(targeting.closest(true));
        }
    }
}
