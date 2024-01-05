package cn.superiormc.mythiclibreforge.effects;

import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.ConfigArguments;
import com.willfp.libreforge.ConfigArgumentsBuilder;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.effects.Effect;
import com.willfp.libreforge.triggers.TriggerData;
import com.willfp.libreforge.triggers.TriggerParameter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.utils.MythicUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EffectQuickEquip extends Effect<NoCompileData> {

    public EffectQuickEquip() {
        super("quick_equip");
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

        ItemStack item = player.getInventory().getItemInMainHand();
        EquipmentSlot equip = EquipmentSlot.HAND;

        if (item.getType().isAir()) {
            return false;
        }

        Material itemType = item.getType();

        if (itemType == Material.LEATHER_HELMET ||
                itemType == Material.CHAINMAIL_HELMET ||
                itemType == Material.GOLDEN_HELMET ||
                itemType == Material.DIAMOND_HELMET ||
                itemType == Material.IRON_HELMET ||
                itemType == Material.NETHERITE_HELMET ||
                itemType == Material.TURTLE_HELMET) {
            equip = EquipmentSlot.HEAD;
        }

        if (itemType == Material.LEATHER_CHESTPLATE ||
                itemType == Material.CHAINMAIL_CHESTPLATE ||
                itemType == Material.GOLDEN_CHESTPLATE ||
                itemType == Material.DIAMOND_CHESTPLATE ||
                itemType == Material.IRON_CHESTPLATE ||
                itemType == Material.NETHERITE_CHESTPLATE ||
                itemType == Material.ELYTRA) {
            equip = EquipmentSlot.CHEST;
        }

        if (itemType == Material.LEATHER_LEGGINGS ||
                itemType == Material.CHAINMAIL_LEGGINGS ||
                itemType == Material.GOLDEN_LEGGINGS ||
                itemType == Material.DIAMOND_LEGGINGS ||
                itemType == Material.IRON_LEGGINGS ||
                itemType == Material.NETHERITE_LEGGINGS) {
            equip = EquipmentSlot.LEGS;
        }

        if (itemType == Material.LEATHER_BOOTS ||
                itemType == Material.CHAINMAIL_BOOTS ||
                itemType == Material.GOLDEN_BOOTS ||
                itemType == Material.DIAMOND_BOOTS ||
                itemType == Material.IRON_BOOTS ||
                itemType == Material.NETHERITE_BOOTS) {
            equip = EquipmentSlot.FEET;
        }

        if (itemType == Material.TOTEM_OF_UNDYING ||
                itemType == Material.SHIELD) {
            equip = EquipmentSlot.OFF_HAND;
        }

        ItemStack oldItem = player.getInventory().getItem(equip);

        if (oldItem == item) {
            return false;
        }

        player.getInventory().setItemInMainHand(null);
        player.getInventory().setItem(equip, item);
        if (!oldItem.getType().isAir()) {
            player.getInventory().addItem(oldItem);
        }

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
