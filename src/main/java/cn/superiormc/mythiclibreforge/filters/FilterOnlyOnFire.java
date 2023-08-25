package cn.superiormc.mythiclibreforge.filters;

import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.filters.Filter;
import com.willfp.libreforge.triggers.TriggerData;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class FilterOnlyOnFire extends Filter<NoCompileData, Boolean> {

    public FilterOnlyOnFire() {
        super("only_on_fire");
    }

    @Override
    public Boolean getValue(@NotNull Config config, @Nullable TriggerData triggerData, @NotNull String s) {
        return config.getBool(s);
    }

    @Override
    protected boolean isMet(@NotNull TriggerData triggerData, Boolean b, NoCompileData noCompileData) {
        if (triggerData.getVictim() == null) {
            return true;
        }
        return triggerData.getVictim().getFireTicks() >= 0;
    }
}
