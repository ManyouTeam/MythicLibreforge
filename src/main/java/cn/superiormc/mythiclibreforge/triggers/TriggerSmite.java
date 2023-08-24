package cn.superiormc.mythiclibreforge.triggers;

import com.willfp.libreforge.Holder;
import com.willfp.libreforge.ProvidedHolder;
import com.willfp.libreforge.triggers.Trigger;
import com.willfp.libreforge.triggers.TriggerData;
import com.willfp.libreforge.triggers.TriggerParameter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class TriggerSmite extends Trigger {

    public TriggerSmite() {
        super("smite");
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
    public void handle(SmithItemEvent event) {
        if (event.getInventory().getResult() == null) {
            return;
        }
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getInventory().getResult();
        ProvidedHolder holder = new ProvidedHolder() {
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
                player,
                null,
                null,
                event,
                null,
                null,
                null,
                item,
                null,
                1,
                player);
        this.dispatch(player, data, null);
    }
}
