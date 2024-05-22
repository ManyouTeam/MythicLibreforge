package cn.superiormc.mythiclibreforge.filters;

import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.filters.Filter;
import com.willfp.libreforge.triggers.TriggerData;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class FilterDontHasEnchantment extends Filter<NoCompileData, Collection<String>> {

    public FilterDontHasEnchantment() {
        super("dont_has_enchantment");
    }

    @Override
    public Collection<String> getValue(@NotNull Config config, @Nullable TriggerData triggerData, @NotNull String s) {
        return config.getStrings(s);
    }

    @Override
    protected boolean isMet(@NotNull TriggerData triggerData, Collection<String> strings, NoCompileData noCompileData) {
        ItemStack item = triggerData.getItem();
        if (item == null) {
            return false;
        }
        for (String str : strings) {
            Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(str));
            if (enchantment == null) {
                continue;
            }
            if (item.getEnchantments().containsKey(enchantment)) {
                return false;
            }
        }
        return true;
    }
}
