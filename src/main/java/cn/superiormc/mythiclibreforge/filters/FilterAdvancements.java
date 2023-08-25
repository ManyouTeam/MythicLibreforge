package cn.superiormc.mythiclibreforge.filters;

import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.filters.Filter;
import com.willfp.libreforge.triggers.TriggerData;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class FilterAdvancements extends Filter<NoCompileData, Collection<String>> {

    public FilterAdvancements() {
        super("advancements");
    }

    @Override
    public Collection<String> getValue(@NotNull Config config, @Nullable TriggerData triggerData, @NotNull String s) {
        return config.getStrings(s);
    }

    @Override
    protected boolean isMet(@NotNull TriggerData triggerData, Collection<String> strings, NoCompileData noCompileData) {
        if (triggerData.getEvent() instanceof PlayerAdvancementDoneEvent) {
            return strings.contains(((PlayerAdvancementDoneEvent) triggerData.getEvent()).getAdvancement().getKey().toString().toLowerCase());
        }
        return true;
    }
}
