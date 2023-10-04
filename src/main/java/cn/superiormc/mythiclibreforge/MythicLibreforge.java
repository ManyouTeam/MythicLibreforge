package cn.superiormc.mythiclibreforge;

import cn.superiormc.mythiclibreforge.conditions.ConditionInCombat;
import cn.superiormc.mythiclibreforge.effects.EffectAddMMOStat;
import cn.superiormc.mythiclibreforge.effects.EffectAutoPlant;
import cn.superiormc.mythiclibreforge.effects.EffectCastMythicSkill;
import cn.superiormc.mythiclibreforge.filters.FilterAdvancements;
import cn.superiormc.mythiclibreforge.filters.FilterAtLocation;
import cn.superiormc.mythiclibreforge.filters.FilterOnlyOnFire;
import cn.superiormc.mythiclibreforge.managers.CombatManager;
import cn.superiormc.mythiclibreforge.triggers.TriggerAdvencementDone;
import cn.superiormc.mythiclibreforge.triggers.TriggerHighestAttack;
import cn.superiormc.mythiclibreforge.triggers.TriggerSmeltResult;
import cn.superiormc.mythiclibreforge.triggers.TriggerSmite;
import com.willfp.libreforge.conditions.Conditions;
import com.willfp.libreforge.effects.Effects;
import com.willfp.libreforge.filters.Filters;
import com.willfp.libreforge.triggers.Triggers;
import io.lumine.mythic.lib.MythicLib;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MythicLibreforge extends JavaPlugin {

    public static JavaPlugin instance;

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        config =  instance.getConfig();
        if (config.getBoolean("effects.cast_mythic_skill")) {
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fRegistered cast_mythic_skill effect.");
            Effects.INSTANCE.register(new EffectCastMythicSkill());
        }
        if (config.getBoolean("effects.auto_plant")) {
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fRegistered auto_plant effect.");
            Effects.INSTANCE.register(new EffectAutoPlant());
        }
        if (config.getBoolean("effects.add_mmo_stat")) {
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fRegistered add_mmo_stat effect.");
            Effects.INSTANCE.register(new EffectAddMMOStat());
        }
        if (config.getBoolean("triggers.advancement_done")) {
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fRegistered advancement_done trigger.");
            Triggers.INSTANCE.register(new TriggerAdvencementDone());
        }
        if (config.getBoolean("triggers.smite")) {
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fRegistered smite trigger.");
            Triggers.INSTANCE.register(new TriggerSmite());
        }
        if (config.getBoolean("triggers.highest_attack", true)) {
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fRegistered highest_attack trigger.");
            Triggers.INSTANCE.register(new TriggerHighestAttack());
        }
        if (config.getBoolean("triggers.smelt_result", true)) {
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fRegistered smelt_result trigger.");
            Triggers.INSTANCE.register(new TriggerSmeltResult());
        }
        if (config.getBoolean("filters.advancements")) {
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fRegistered advancements filter.");
            Filters.INSTANCE.register(new FilterAdvancements());
        }
        if (config.getBoolean("filters.only_on_fire")) {
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fRegistered only_on_fire filter.");
            Filters.INSTANCE.register(new FilterOnlyOnFire());
        }
        if (config.getBoolean("filters.at_location", true)) {
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fRegistered at_location filter.");
            Filters.INSTANCE.register(new FilterAtLocation());
        }
        if (config.getBoolean("conditions.in_combat")) {
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fRegistered in_combat condition.");
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §6Warn: This condition is experimental, be careful to use！");
            Bukkit.getPluginManager().registerEvents(new CombatManager(), this);
            Conditions.INSTANCE.register(new ConditionInCombat());
        }
        CombatManager.checkCombat();
        Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fPlugin is loaded. Author: PQguanfang.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fPlugin is disabled. Author: PQguanfang.");
    }

}
