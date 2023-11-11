package cn.superiormc.mythiclibreforge.filters;

import cn.superiormc.mythiclibreforge.MythicLibreforge;
import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.filters.Filter;
import com.willfp.libreforge.triggers.TriggerData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class FilterIsCrit extends Filter<NoCompileData, Boolean> {

    public FilterIsCrit() {
        super("is_crit");
    }

    @Override
    public Boolean getValue(@NotNull Config config, @Nullable TriggerData triggerData, @NotNull String s) {
        return config.getBool(s);
    }

    @Override
    protected boolean isMet(@NotNull TriggerData triggerData, Boolean b, NoCompileData noCompileData) {
        Event event = triggerData.getEvent();
        Player player = triggerData.getPlayer();
        if (event == null || player == null) {
            return true;
        }
        if (!(event instanceof EntityDamageEvent)) {
            return true;
        }
        return player.getVelocity().getY() < -0.1;
    }
}
