package cn.superiormc.mythiclibreforge.effects;

import cn.superiormc.mythiclibreforge.MythicLibreforge;
import com.willfp.eco.core.config.interfaces.Config;
import com.willfp.libreforge.NoCompileData;
import com.willfp.libreforge.ProvidedHolder;
import com.willfp.libreforge.effects.Effect;
import com.willfp.libreforge.effects.Identifiers;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EffectAutoPlant extends Effect<NoCompileData> {

    public static Map<UUID, UUID> players = new HashMap<>();
    public EffectAutoPlant() {
        super("auto_plant");
    }

    @Override
    public boolean isPermanent() {
        return true;
    }

    @Override
    protected void onEnable(@NotNull Player player, @NotNull Config config, @NotNull Identifiers identifiers, @NotNull ProvidedHolder holder, NoCompileData compileData) {
        players.put(player.getUniqueId(), identifiers.getUuid());
    }

    @Override
    protected void onDisable(@NotNull Player player, @NotNull Identifiers identifiers, @NotNull ProvidedHolder holder) {
        players.remove(player.getUniqueId());
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void handle(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!players.containsKey(player.getUniqueId())) {
            return;
        }
        Block block = event.getBlock();
        Material type = block.getType();
        if (type == Material.GLOW_BERRIES ||
                type == Material.SWEET_BERRY_BUSH ||
                type == Material.CACTUS ||
                type == Material.BAMBOO ||
                type == Material.CHORUS_FLOWER ||
                type == Material.SUGAR_CANE) {
            return;
        }
        if (!(block.getBlockData() instanceof Ageable)) {
            return;
        }
        ItemStack item = new ItemStack(
                type == Material.WHEAT ? Material.WHEAT_SEEDS :
                        type == Material.POTATOES ? Material.POTATO :
                                type == Material.CARROTS ? Material.CARROT :
                                        type == Material.BEETROOTS ? Material.BEETROOT_SEEDS :
                                                type
        );
        boolean hasSeeds = player.getInventory().removeItem(item).isEmpty();
        if (!hasSeeds) {
            return;
        }
        Ageable ageBlock = (Ageable) block.getBlockData();
        if (ageBlock.getAge() != ageBlock.getMaximumAge()) {
            return;
        }
        // From EcoEnchants.
        ageBlock.setAge(0);
        Bukkit.getScheduler().runTaskLater(MythicLibreforge.instance, () -> {
                block.setType(type);
                block.setBlockData(event.getBlock().getBlockData());

                // Improves compatibility with other plugins.
                Bukkit.getPluginManager().callEvent(
                        new BlockPlaceEvent(
                                block,
                                block.getState(),
                                block.getRelative(BlockFace.DOWN),
                                player.getInventory().getItemInMainHand(),
                                player,
                                true,
                                EquipmentSlot.HAND
                        )
                );
        }, 5L);
    }

}
