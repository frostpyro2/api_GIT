package frostpyro.frostapi.api.skill.dir;

public enum SkillYmlDir {
    DAMAGE("damage"),
    PARTICLE("particle"),
    COOL_DOWN("coolDown"),
    PROJECTILE("projectile"),
    HOMING("homing"),
    DISPLAY("display"),
    ITEM("item"),
    MODEL("model"),
    FRAME("frame")
    ;
    private String string;
    SkillYmlDir(String string){
        this.string = string;
    }

    public String getDir(){
        return string;
    }
}
