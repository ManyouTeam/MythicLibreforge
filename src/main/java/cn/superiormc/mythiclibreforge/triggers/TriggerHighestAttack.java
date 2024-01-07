package cn.superiormc.mythiclibreforge.triggers;

import com.willfp.libreforge.DispatcherKt;
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

import java.util.HashSet;
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
        if (!(event.getEntity() instanceof LivingEntity)
        || !(event.getDamager() instanceof LivingEntity)) {
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
        TriggerData data = new TriggerData(DispatcherKt.toDispatcher(entity),
                player,
                victim,
                null,
                event,
                victim.getLocation(),
                null,
                null,
                item,
                null,
                event.getDamage());
        this.dispatch(DispatcherKt.toDispatcher(entity), data, null);

    }
}
