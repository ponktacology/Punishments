package me.ponktacology.punishments;

import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import me.ponktacology.punishments.command.PunishmentCommand;
import me.ponktacology.punishments.config.PunishmentsConfig;
import me.ponktacology.punishments.listener.PunishmentListener;
import me.ponktacology.punishments.manager.DataManager;
import me.ponktacology.punishments.manager.PunishmentManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Punishments extends JavaPlugin {

    private PunishmentsConfig punishmentsConfig;
    private DataManager dataManager;
    private PunishmentManager punishmentManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.punishmentsConfig = new PunishmentsConfig(this);
        this.dataManager = new DataManager(this);
        this.punishmentManager = new PunishmentManager(this);
        this.punishmentManager.loadAllPunishments();

        Bukkit.getPluginManager().registerEvents(new PunishmentListener(this), this);
        BukkitCommandManager bukkitCommandManager = new BukkitCommandManager(this);
        bukkitCommandManager.registerCommand(new PunishmentCommand(this));
    }


}
