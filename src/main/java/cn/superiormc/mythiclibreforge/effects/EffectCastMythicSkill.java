package cn.superiormc.mythiclibreforge.libreforge;

import com.willfp.libreforge.ConfigArgumentsBuilder;
import com.willfp.libreforge.ConfigArgumentsKt;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.effects.Effect;
import com.willfp.libreforge.ConfigArguments;
import com.willfp.libreforge.effects.impl.EffectGiveXp;
import com.willfp.libreforge.triggers.TriggerData;
import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.triggers.TriggerParameter;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

public class EffectCastMythicSkills extends Effect {

    private static final Set<TriggerParameter> parameters = Collections.singleton(TriggerParameter.PLAYER);

    private static final ConfigArguments arguments = ConfigArgumentsKt.arguments((Function1)null.INSTANCE);

    public EffectCastMythicSkills() {
        super("cast_mythic_skills");
    }

    @Override
    public Set<TriggerParameter> getParameters() {
        return parameters;
    }

    @Override
    public void requireArguments() {
        arguments.require("amount", "You must specify the amount of xp to give!");
    }

    @Override
    public boolean onTrigger(Config config, TriggerData data, NoCompileData compileData) {
        if (data.getPlayer() == null) {
            return false;
        }

        if (Prerequisite.HAS_PAPER.isMet()) {
            data.getPlayer().giveExp(config.getIntFromExpression("amount", data),
                    config.getBoolOrNull("apply_mending") != null ? config.getBoolOrNull("apply_mending") : true);
        } else {
            data.getPlayer().giveExp(config.getIntFromExpression("amount", data));
        }

        return true;
    }
}

}
