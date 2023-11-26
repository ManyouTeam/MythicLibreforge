package cn.superiormc.mythiclibreforge.conditions;

import cn.superiormc.mythiclibreforge.managers.CombatManager;
import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.Dispatcher;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.ProvidedHolder;
import com.willfp.libreforge.conditions.Condition;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ConditionInCombat extends Condition<NoCompileData> {

    public ConditionInCombat() {
        super("in_combat");
    }

    @Override
    public boolean isMet(@NotNull Dispatcher<?> dispatcher, @NotNull Config config, @NotNull ProvidedHolder holder, NoCompileData compileData) {
        if (!(dispatcher.getDispatcher() instanceof Player)) {
            return false;
        }
        return CombatManager.checkPlayer(((Player)dispatcher.getDispatcher()));
    }
}
