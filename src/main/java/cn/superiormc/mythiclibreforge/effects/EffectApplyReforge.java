package cn.superiormc.mythiclibreforge.effects;

import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.*;
import com.willfp.libreforge.effects.Effect;
import com.willfp.libreforge.triggers.TriggerData;
import com.willfp.libreforge.triggers.TriggerParameter;
import com.willfp.reforges.reforges.Reforge;
import com.willfp.reforges.reforges.ReforgeTargets;
import com.willfp.reforges.reforges.Reforges;
import com.willfp.reforges.util.ReforgeUtilsKt;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EffectApplyReforge extends Effect<NoCompileData> {

    public EffectApplyReforge() {
        super("apply_reforge");
    }

    @Override
    public boolean isPermanent() {
        return false;
    }

    @Override
    protected boolean onTrigger(@NotNull Config config, @NotNull TriggerData data, NoCompileData compileData) {
        if (!Bukkit.getPluginManager().isPluginEnabled("Reforges")) {
            return false;
        }
        if (data.getItem() == null) {
            return false;
        }
        if (ReforgeUtilsKt.getReforge(data.getItem()) != null) {
            return false;
        }
        Reforge reforge = Reforges.getByKey(config.getStringOrNull("reforge"));
        if (reforge == null) {
            List<Reforge> disallowedTargets = new ArrayList<>();
            for (String disallowedTarget : config.getStrings("disallowed-reforge")) {
                Reforge disallowedReforge = Reforges.getByKey(disallowedTarget);
                if (disallowedReforge != null) {
                    disallowedTargets.add(disallowedReforge);
                }
            }
            reforge = ReforgeUtilsKt.getRandomReforge(ReforgeTargets.getForItem(data.getItem()), disallowedTargets);
            if (reforge == null) {
                return false;
            }
        }

        ReforgeUtilsKt.setReforge(data.getItem(), reforge);

        return true;
    }

    @NotNull
    @Override
    protected Set<TriggerParameter> getParameters() {
        Set<TriggerParameter> data = new HashSet<>();
        data.add(TriggerParameter.ITEM);
        return data;
    }
}
