package cn.superiormc.mythiclibreforge.managers;

import io.lumine.mythic.lib.api.player.MMOPlayerData;
import io.lumine.mythic.lib.api.stat.StatMap;
import io.lumine.mythic.lib.api.stat.modifier.StatModifier;
import io.lumine.mythic.lib.player.modifier.ModifierType;
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
        this.modifier = new StatModifier("MythicPrefixes_" + id, stat, value, ModifierType.FLAT);
    }

    public void addPlayerState() {
        MMOPlayerData playerData = MMOPlayerData.get(player);
        StatMap statMap = playerData.getStatMap();
        modifier.register(playerData);
        statMap.getInstance(stat).addModifier(modifier);
    }

    public void removePlayerState() {
        MMOPlayerData playerData = MMOPlayerData.get(player);
        StatMap statMap = playerData.getStatMap();
        statMap.getInstance(stat).remove("MythicPrefixes_" + id);
    }
}
