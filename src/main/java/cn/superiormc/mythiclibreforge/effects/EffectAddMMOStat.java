package cn.superiormc.mythiclibreforge.effects;

import cn.superiormc.mythiclibreforge.managers.MMOManager;
import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.ConfigArguments;
import com.willfp.libreforge.ConfigArgumentsBuilder;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.ProvidedHolder;
import com.willfp.libreforge.effects.Effect;
import com.willfp.libreforge.effects.Identifiers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EffectAddMMOStat extends Effect<NoCompileData> {

    public static Map<UUID, UUID> players = new HashMap<>();

    public static Map<UUID, MMOManager> mmoData = new HashMap<>();

    public EffectAddMMOStat() {
        super("add_mmo_stat");
    }

    @Override
    public boolean isPermanent() {
        return true;
    }

    @Override
    protected void onEnable(@NotNull Player player, @NotNull Config config, @NotNull Identifiers identifiers, @NotNull ProvidedHolder holder, NoCompileData compileData) {
        if (Bukkit.getPluginManager().isPluginEnabled("MythicLib")) {
            players.put(player.getUniqueId(), identifiers.getUuid());
            String id = config.getString("stat").toUpperCase();
            MMOManager manager = new MMOManager(identifiers.getUuid().toString(),
                    player,
                    id,
                    config.getDoubleFromExpression("amount"));
            manager.addPlayerState();
            mmoData.put(player.getUniqueId(), manager);
        }
    }

    @Override
    protected void onDisable(@NotNull Player player, @NotNull Identifiers identifiers, @NotNull ProvidedHolder holder) {
        if (Bukkit.getPluginManager().isPluginEnabled("MythicLib")) {
            players.remove(player.getUniqueId());
            MMOManager manager = mmoData.get(player.getUniqueId());
            if (manager == null) {
                return;
            }
            manager.removePlayerState();
            mmoData.remove(player.getUniqueId());
        }
    }

    @NotNull
    @Override
    public ConfigArguments getArguments() {
        ConfigArgumentsBuilder builder = new ConfigArgumentsBuilder();
        builder.require("stat", "You must specify the stat to add!");
        builder.require("amount", "You must specify the added amount!");
        return builder.build$core();
    }
}
