package cn.superiormc.mythiclibreforge.effects;

import cn.superiormc.mythiclibreforge.MythicLibreforge;
import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.Dispatcher;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.ProvidedHolder;
import com.willfp.libreforge.effects.Effect;
import com.willfp.libreforge.effects.Identifiers;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EffectCircleFlight extends Effect<NoCompileData> {

    public static Map<UUID, UUID> players = new HashMap<>();

    public static Map<UUID, BukkitTask> schedules = new HashMap<>();

    public EffectCircleFlight() {
        super("circle_flight");
    }

    @Override
    public boolean isPermanent() {
        return true;
    }

    @Override
    protected void onEnable(@NotNull Dispatcher<?> dispatcher, @NotNull Config config, @NotNull Identifiers identifiers, @NotNull ProvidedHolder holder, NoCompileData compileData) {
        if (dispatcher.getDispatcher() instanceof Player) {
            Player player = (Player) dispatcher.getDispatcher();
            if (player.getGameMode() != GameMode.SURVIVAL && player.getGameMode() != GameMode.ADVENTURE) {
                return;
            }
            players.put(player.getUniqueId(), identifiers.getUuid());
            if (!schedules.containsKey(player.getUniqueId())) {
                schedules.put(player.getUniqueId(), Bukkit.getScheduler().runTaskTimer(MythicLibreforge.instance, () -> {
                    player.setAllowFlight(players.containsKey(player.getUniqueId()));
                }, 20L, 20L));
            }
        }
    }

    @Override
    protected void onDisable(@NotNull Dispatcher<?> dispatcher, @NotNull Identifiers identifiers, @NotNull ProvidedHolder holder) {
        if (dispatcher.getDispatcher() instanceof Player) {
            Player player = (Player) dispatcher.getDispatcher();
            players.remove(player.getUniqueId());
            BukkitTask task = schedules.get(player.getUniqueId());
            if (task != null) {
                task.cancel();
            }
            if (player.getGameMode() != GameMode.SURVIVAL && player.getGameMode() != GameMode.ADVENTURE) {
                player.setAllowFlight(players.containsKey(player.getUniqueId()));
            }
        }
    }

}
