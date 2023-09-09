package cn.superiormc.mythiclibreforge.managers;

import cn.superiormc.mythiclibreforge.MythicLibreforge;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Boat;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CombatManager implements Listener {

    private static Map<Player, Long> combatPlayer = new ConcurrentHashMap<>();

    @EventHandler
    public void combatEvent(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof ArmorStand ||
        event.getEntity() instanceof Boat ||
        event.getEntity() instanceof ItemFrame) {
            return;
        }
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (combatPlayer.containsKey(player)) {
                combatPlayer.replace(player, MythicLibreforge.config.getLong("settings.remove-combat-time"));
            }
            else {
                combatPlayer.put(player, MythicLibreforge.config.getLong("settings.remove-combat-time"));
            }
        }
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            combatPlayer.put(player, MythicLibreforge.config.getLong("settings.remove-combat-time"));
        }
    }

    public static void checkCombat() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(MythicLibreforge.instance, () -> {
            for (Player player : combatPlayer.keySet()) {
                combatPlayer.replace(player, combatPlayer.get(player) - 20L);
                if (combatPlayer.get(player) <= 0L) {
                    combatPlayer.remove(player);
                }
            }
        }, 20L, 20L);
    }

    public static boolean checkPlayer(Player player) {
        return combatPlayer.containsKey(player);
    }
}
