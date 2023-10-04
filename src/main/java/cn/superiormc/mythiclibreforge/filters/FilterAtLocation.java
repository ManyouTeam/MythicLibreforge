package cn.superiormc.mythiclibreforge.filters;

import cn.superiormc.mythiclibreforge.MythicLibreforge;
import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.filters.Filter;
import com.willfp.libreforge.triggers.TriggerData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class FilterAtLocation extends Filter<NoCompileData, Collection<String>> {

    public FilterAtLocation() {
        super("at_location");
    }

    @Override
    public Collection<String> getValue(@NotNull Config config, @Nullable TriggerData triggerData, @NotNull String s) {
        return config.getStrings(s);
    }

    @Override
    protected boolean isMet(@NotNull TriggerData triggerData, Collection<String> strings, NoCompileData noCompileData) {
        Location location = triggerData.getLocation();
        if (triggerData.getLocation() != null) {
            if (MythicLibreforge.config.getBoolean("settings.debug", false)) {
                Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §cNow Location: " + location.toString());
            }
            if (location.getWorld() == null) {
                return false;
            }
            String str = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
            return strings.contains(str.replace(", ", ","));
        }
        return true;
    }
}
