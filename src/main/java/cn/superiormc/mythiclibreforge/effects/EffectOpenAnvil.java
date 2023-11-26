package cn.superiormc.mythiclibreforge.effects;

import com.willfp.eco.core.Prerequisite;
import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.effects.Effect;
import com.willfp.libreforge.triggers.TriggerData;
import com.willfp.libreforge.triggers.TriggerParameter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class EffectOpenAnvil extends Effect<NoCompileData> {

    public EffectOpenAnvil() {
        super("open_anvil");
    }

    @Override
    public boolean isPermanent() {
        return false;
    }

    @Override
    protected boolean onTrigger(@NotNull Config config, @NotNull TriggerData data, NoCompileData compileData) {
        if (Prerequisite.HAS_PAPER.isMet()) {
            Player player = data.getPlayer();
            if (player == null) {
                return false;
            }
            player.openAnvil(null, true);
            return true;
        }
        Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §cError: open_anvil effect require use Paper!");
        return false;
    }

    @NotNull
    @Override
    protected Set<TriggerParameter> getParameters() {
        Set<TriggerParameter> data = new HashSet<>();
        data.add(TriggerParameter.PLAYER);
        return data;
    }

}
