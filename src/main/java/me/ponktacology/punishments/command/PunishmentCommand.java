package me.ponktacology.punishments.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import lombok.RequiredArgsConstructor;
import me.ponktacology.punishments.Punishments;
import me.ponktacology.punishments.punishment.Punishment;
import me.ponktacology.punishments.punishment.PunishmentType;
import me.ponktacology.punishments.util.PlayerUtil;
import me.ponktacology.punishments.util.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@RequiredArgsConstructor
public class PunishmentCommand extends BaseCommand {

    private final Punishments plugin;


    @CommandAlias("permban")
    public void execute(CommandSender sender, String target, String reason) {
        UUID targetUUID;
        if ((targetUUID = PlayerUtil.getUUID(target)) == null) {
            sender.sendMessage(ChatColor.RED + "Invalid player.");
            return;
        }

        if (this.plugin.getPunishmentManager().getPunishments().containsKey(targetUUID)) {
            sender.sendMessage(ChatColor.RED + "This player is already banned.");
            return;
        }

        new Punishment(targetUUID, reason, PunishmentType.PERMBAN, -1);
        Player targetPlayer;

        if ((targetPlayer = Bukkit.getPlayer(targetUUID)) != null) {
            targetPlayer.kickPlayer(ChatColor.RED + "You have been banned!");
        }
        sender.sendMessage(ChatColor.GREEN + "Successfully permbanned player.");
    }

    @CommandAlias("tempban")
    public void execute(CommandSender sender, String target, String reason, String time) {
        UUID targetUUID;
        if ((targetUUID = PlayerUtil.getUUID(target)) == null) {
            sender.sendMessage(ChatColor.RED + "Invalid player.");
            return;
        }

        long endTimeStamp;

        if ((endTimeStamp = TimeUtil.parseTime(time)) < 0) {
            sender.sendMessage(ChatColor.RED + "Invalid time format. Example: 5d22h13m20s");
            return;
        }

        new Punishment(targetUUID, reason, PunishmentType.TEMPBAN, System.currentTimeMillis() + endTimeStamp);
        Player targetPlayer;

        if ((targetPlayer = Bukkit.getPlayer(targetUUID)) != null) {
            targetPlayer.kickPlayer(ChatColor.RED + "You have been banned!");
        }
    }

    @CommandAlias("unban")
    public void execute(CommandSender sender, String target) {
        UUID targetUUID;
        if ((targetUUID = PlayerUtil.getUUID(target)) == null) {
            sender.sendMessage(ChatColor.RED + "Invalid player.");
            return;
        }

        if (!this.plugin.getPunishmentManager().getPunishments().containsKey(targetUUID)) {
            sender.sendMessage(ChatColor.RED + "This player isn't banned.");
            return;
        }

        this.plugin.getPunishmentManager().getPunishments().remove(targetUUID);
        sender.sendMessage(ChatColor.GREEN + "Successfully unbanned player.");
    }
}
