package cn.superiormc.mythiclibreforge.triggers;

import com.willfp.libreforge.DispatcherKt;
import com.willfp.libreforge.triggers.Trigger;
import com.willfp.libreforge.triggers.TriggerData;
import com.willfp.libreforge.triggers.TriggerParameter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class TriggerSmeltResult extends Trigger {

    public TriggerSmeltResult() {
        super("smelt_result");
    }

    @NotNull
    @Override
    public Set<TriggerParameter> getParameters() {
        Set<TriggerParameter> data = new HashSet<>();
        data.add(TriggerParameter.PLAYER);
        data.add(TriggerParameter.LOCATION);
        data.add(TriggerParameter.ITEM);
        return data;
    }

    @EventHandler
    public void handle(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        if (event.getInventory().getType() != InventoryType.FURNACE
                && !event.getInventory().getType().name().equals("BLAST_FURNACE")
                && !event.getInventory().getType().name().equals("SMOKER")) {
            return;
        }
        if (event.getSlotType() != InventoryType.SlotType.RESULT) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (item == null) {
            return;
        }
        TriggerData data = new TriggerData(DispatcherKt.toDispatcher(player),
                player,
                null,
                null,
                event,
                null,
                null,
                null,
                item,
                null,
                item.getAmount());
        this.dispatch(DispatcherKt.toDispatcher(player), data, null);
    }
}
