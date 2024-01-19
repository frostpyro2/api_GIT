package frostpyro.frostapi.handler.effect_handler;

public abstract class TargetEffect<T> {
    private T t;
    public TargetEffect(T t){
        this.t = t;
    }

    public abstract void action();

    public T getObj(){
        return t;
    }
}
