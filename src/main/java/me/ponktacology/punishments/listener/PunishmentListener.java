package me.ponktacology.punishments.listener;

import lombok.RequiredArgsConstructor;
import me.ponktacology.punishments.Punishments;
import me.ponktacology.punishments.punishment.Punishment;
import me.ponktacology.punishments.punishment.PunishmentType;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.server.PluginDisableEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class PunishmentListener implements Listener {

    private final Punishments plugin;

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event) {
        Punishment punishment = this.plugin.getPunishmentManager().getPunishments().get(event.getUniqueId());
        if (punishment == null) {
            System.out.println("1");
            return;
        }
        String banMesage = ChatColor.RED + "You are ";
        if (punishment.getPunishmentType().equals(PunishmentType.PERMBAN)) {
            banMesage += "permanently banned!";
        } else {
            banMesage += "temporary banned!\nYour ban will be lifted on: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date(punishment.getEndTimeStamp()));
        }

        event.setKickMessage(banMesage);
        event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
    }

    @EventHandler
    public void onDisable(PluginDisableEvent event) {
        this.plugin.getPunishmentManager().saveAllPunishments();
        this.plugin.getDataManager().getMongoClient().close();
    }
}
