package cn.superiormc.mythiclibreforge.managers;

import io.lumine.mythic.lib.MythicLib;
import io.lumine.mythic.lib.api.player.MMOPlayerData;
import io.lumine.mythic.lib.api.stat.StatInstance;
import io.lumine.mythic.lib.api.stat.StatMap;
import io.lumine.mythic.lib.api.stat.modifier.StatModifier;
import io.lumine.mythic.lib.player.modifier.ModifierType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class MMOManager {

    private StatModifier modifier = null;

    private String id;

    private OfflinePlayer player;

    private String stat;

    private double value;

    public MMOManager(String id, OfflinePlayer player, String stat, double value) {
        this.id = id;
        this.player = player;
        this.stat = stat;
        this.value = value;
        this.modifier = new StatModifier("MythicLibreforge_" + id, stat, value, ModifierType.FLAT);
    }

    public void addPlayerStat() {
        MMOPlayerData playerData = MMOPlayerData.get(player);
        StatMap statMap = playerData.getStatMap();
        modifier.register(playerData);
        statMap.getInstance(stat).addModifier(modifier);
    }

    public void removePlayerStat() {
        MMOPlayerData playerData = MMOPlayerData.get(player);
        StatMap statMap = playerData.getStatMap();
        statMap.getInstance(stat).remove("MythicLibreforge_" + id);
    }
}
