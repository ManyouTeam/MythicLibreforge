package cn.superiormc.mythiclibreforge.effects;

import com.willfp.eco.core.Prerequisite;
import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.ConfigArguments;
import com.willfp.libreforge.ConfigArgumentsBuilder;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.effects.Effect;
import com.willfp.libreforge.triggers.TriggerData;
import com.willfp.libreforge.triggers.TriggerParameter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class EffectOpenDisposal extends Effect<NoCompileData> {

    public EffectOpenDisposal() {
        super("open_disposal");
    }

    @Override
    public boolean isPermanent() {
        return false;
    }

    @Override
    protected boolean onTrigger(@NotNull Config config, @NotNull TriggerData data, NoCompileData compileData) {
        Player player = data.getPlayer();
        if (player == null) {
            return false;
        }
        player.openInventory(Bukkit.createInventory(null, config.getInt("size", 54), config.getFormattedString("title")));
        return true;
    }

    @NotNull
    @Override
    protected Set<TriggerParameter> getParameters() {
        Set<TriggerParameter> data = new HashSet<>();
        data.add(TriggerParameter.PLAYER);
        return data;
    }

    @NotNull
    @Override
    public ConfigArguments getArguments() {
        ConfigArgumentsBuilder builder = new ConfigArgumentsBuilder();
        builder.require("title", "You must specify the UI title!");
        return builder.build$core();
    }

}
