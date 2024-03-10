package frostpyro.frostapi.api.listeners.fakeEventHandler.eventHandler;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public abstract class FakeBlockBreakEvent extends BlockBreakEvent {
    public FakeBlockBreakEvent(@NotNull Block theBlock, @NotNull Player player) {
        super(theBlock, player);
    }
}
