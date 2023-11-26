package cn.superiormc.mythiclibreforge.effects;

import com.willfp.eco.core.Prerequisite;
import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.effects.Effect;
import com.willfp.libreforge.triggers.TriggerData;
import com.willfp.libreforge.triggers.TriggerParameter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class EffectOpenEnchanting extends Effect<NoCompileData> {

    public EffectOpenEnchanting() {
        super("open_enchanting");
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
        player.openEnchanting(null, true);
        return true;
    }

    @NotNull
    @Override
    protected Set<TriggerParameter> getParameters() {
        Set<TriggerParameter> data = new HashSet<>();
        data.add(TriggerParameter.PLAYER);
        return data;
    }

}
