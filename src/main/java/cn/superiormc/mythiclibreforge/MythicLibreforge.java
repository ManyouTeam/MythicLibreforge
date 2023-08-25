package cn.superiormc.mythiclibreforge;

import cn.superiormc.mythiclibreforge.effects.EffectAddMMOStat;
import cn.superiormc.mythiclibreforge.effects.EffectAutoPlant;
import cn.superiormc.mythiclibreforge.effects.EffectCastMythicSkill;
import cn.superiormc.mythiclibreforge.filters.FiltersAdvancements;
import cn.superiormc.mythiclibreforge.triggers.TriggerAdvencementDone;
import cn.superiormc.mythiclibreforge.triggers.TriggerSmite;
import com.willfp.libreforge.effects.Effects;
import com.willfp.libreforge.filters.Filters;
import com.willfp.libreforge.triggers.Triggers;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MythicLibreforge extends JavaPlugin {

    public static JavaPlugin instance;

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;
        config =  instance.getConfig();
        if (config.getBoolean("effects.cast-mythic-skill")) {
            Effects.INSTANCE.register(new EffectCastMythicSkill());
        }
        if (config.getBoolean("effects.auto-plant")) {
            Effects.INSTANCE.register(new EffectAutoPlant());
        }
        if (config.getBoolean("effects.add-mmo-stat")) {
            Effects.INSTANCE.register(new EffectAddMMOStat());
        }
        if (config.getBoolean("triggers.advancement_done")) {
            Triggers.INSTANCE.register(new TriggerAdvencementDone());
        }
        if (config.getBoolean("triggers.smite")) {
            Triggers.INSTANCE.register(new TriggerSmite());
        }
        if (config.getBoolean("filters.advancements")) {
            Filters.INSTANCE.register(new FiltersAdvancements());
        }
        Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fPlugin is loaded. Author: PQguanfang.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fPlugin is disabled. Author: PQguanfang.");
    }

}
