package cn.superiormc.mythiclibreforge;

import cn.superiormc.mythiclibreforge.effects.EffectAutoPlant;
import cn.superiormc.mythiclibreforge.effects.EffectCastMythicSkill;
import cn.superiormc.mythiclibreforge.filters.FiltersAdvancements;
import cn.superiormc.mythiclibreforge.triggers.TriggerAdvencementDone;
import cn.superiormc.mythiclibreforge.triggers.TriggerSmite;
import com.willfp.libreforge.effects.Effects;
import com.willfp.libreforge.filters.Filters;
import com.willfp.libreforge.triggers.Triggers;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MythicLibreforge extends JavaPlugin {

    public static JavaPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        Effects.INSTANCE.register(new EffectCastMythicSkill());
        Effects.INSTANCE.register(new EffectAutoPlant());
        Triggers.INSTANCE.register(new TriggerAdvencementDone());
        Triggers.INSTANCE.register(new TriggerSmite());
        Filters.INSTANCE.register(new FiltersAdvancements());
        Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fPlugin is loaded. Author: PQguanfang.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicLibreforge] §fPlugin is disabled. Author: PQguanfang.");
    }

}
