package frostpyro.frostapi.skill.handler.entityAction;

public class Activate <T extends TargetEntity>{
    private T t;
    public void setType(T t){
        this.t = t;
    }

    public void activate(){
        t.actFunction();
    }
}
