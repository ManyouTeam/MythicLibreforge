package cn.superiormc.mythiclibreforge.triggers;

import com.willfp.libreforge.Holder;
import com.willfp.libreforge.ProvidedHolder;
import com.willfp.libreforge.triggers.Trigger;
import com.willfp.libreforge.triggers.TriggerData;
import com.willfp.libreforge.triggers.TriggerParameter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class TriggerAdvencementDone extends Trigger {

    public TriggerAdvencementDone() {
        super("advancement_done");
    }

    @NotNull
    @Override
    public Set<TriggerParameter> getParameters() {
        Set<TriggerParameter> data = new HashSet<>();
        data.add(TriggerParameter.PLAYER);
        data.add(TriggerParameter.LOCATION);
        data.add(TriggerParameter.EVENT);
        return data;
    }

    @EventHandler
    public void handle(PlayerAdvancementDoneEvent event) {
        if (event.getAdvancement().getKey().getKey().startsWith("recipes/")) {
            return;
        }
        Player player = event.getPlayer();
        Location location = event.getPlayer().getLocation();
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
                location,
                null,
                null,
                null,
                null,
                1,
                player);
        this.dispatch(player, data, null);
    }
}
