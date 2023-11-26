package cn.superiormc.mythiclibreforge.triggers;

import com.willfp.libreforge.Dispatcher;
import com.willfp.libreforge.DispatcherKt;
import com.willfp.libreforge.Holder;
import com.willfp.libreforge.ProvidedHolder;
import com.willfp.libreforge.triggers.Trigger;
import com.willfp.libreforge.triggers.TriggerData;
import com.willfp.libreforge.triggers.TriggerParameter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
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
        ProvidedHolder holder = new ProvidedHolder() {
            @Override
            public boolean isShowingAnyNotMet(@NotNull Player player) {
                return false;
            }

            @Override
            public boolean isShowingAnyNotMet(@NotNull Dispatcher<?> dispatcher) {
                return false;
            }

            @NotNull
            @Override
            public List<String> getNotMetLines(@NotNull Player player) {
                return null;
            }

            @NotNull
            @Override
            public List<String> getNotMetLines(@NotNull Dispatcher<?> dispatcher) {
                return null;
            }

            @NotNull
            @Override
            public Holder getHolder() {
                return null;
            }

            @Nullable
            @Override
            public Object getProvider() {
                return null;
            }

            @NotNull
            @Override
            public Holder component1() {
                return null;
            }

            @Nullable
            @Override
            public Object component2() {
                return null;
            }
        };
        TriggerData data = new TriggerData(holder,
                DispatcherKt.toDispatcher(player),
                player,
                null,
                null,
                event,
                null,
                null,
                null,
                item,
                null,
                item.getAmount(),
                player);
        this.dispatch(DispatcherKt.toDispatcher(player), data, null);

    }
}
