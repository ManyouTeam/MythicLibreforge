package cn.superiormc.mythiclibreforge.arguments;

import com.willfp.libreforge.ConfigurableElement;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.UtilsKt;
import com.willfp.libreforge.effects.arguments.EffectArgument;
import com.willfp.libreforge.triggers.DispatchedTrigger;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.jetbrains.annotations.NotNull;

public class ArgDamageItem extends EffectArgument<NoCompileData> {

    public ArgDamageItem() {
        super("damage_item");
    }

    @Override
    public boolean isMet(@NotNull ConfigurableElement element, @NotNull DispatchedTrigger trigger, NoCompileData compileData) {
        ItemStack item = trigger.getData().getItem();
        if (item == null) {
            return false;
        }
        if (item.getType().isAir()) {
            return false;
        }
        if (!item.hasItemMeta()) {
            return false;
        }
        return item.getItemMeta() instanceof Damageable;
    }

    @Override
    public void ifMet(@NotNull ConfigurableElement element, @NotNull DispatchedTrigger trigger, NoCompileData compileData) {
        if (trigger.getData().getItem() == null || trigger.getData().getPlayer() == null) {
            return;
        }
        UtilsKt.applyDamage(trigger.getData().getItem(), 1, trigger.getData().getPlayer());
    }
}
