package cn.superiormc.mythiclibreforge.triggers;

import com.willfp.libreforge.Dispatcher;
import com.willfp.libreforge.DispatcherKt;
import com.willfp.libreforge.Holder;
import com.willfp.libreforge.ProvidedHolder;
import com.willfp.libreforge.triggers.Trigger;
import com.willfp.libreforge.triggers.TriggerData;
import com.willfp.libreforge.triggers.TriggerParameter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TriggerHighestAttack extends Trigger {

    public TriggerHighestAttack() {
        super("highest_attack");
    }

    @NotNull
    @Override
    public Set<TriggerParameter> getParameters() {
        Set<TriggerParameter> data = new HashSet<>();
        data.add(TriggerParameter.PLAYER);
        data.add(TriggerParameter.VICTIM);
        data.add(TriggerParameter.LOCATION);
        data.add(TriggerParameter.ITEM);
        data.add(TriggerParameter.EVENT);
        return data;
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void handle(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof LivingEntity
        || !(event.getDamager() instanceof LivingEntity))) {
            return;
        }
        LivingEntity entity = (LivingEntity) event.getDamager();
        Player player = null;
        if (event.getDamager() instanceof Player) {
            player = (Player) event.getDamager();
        }
        ItemStack item = null;
        if (entity.getEquipment() != null) {
            item = entity.getEquipment().getItemInMainHand();
        }
        LivingEntity victim = (LivingEntity) event.getEntity();
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
                DispatcherKt.toDispatcher(entity),
                player,
                victim,
                null,
                event,
                victim.getLocation(),
                null,
                null,
                item,
                null,
                event.getDamage(),
                player);
        this.dispatch(DispatcherKt.toDispatcher(player), data, null);

    }
}
