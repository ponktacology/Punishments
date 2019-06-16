package me.ponktacology.punishments.punishment;

import lombok.Data;
import me.ponktacology.punishments.Punishments;

import java.util.UUID;

@Data
public class Punishment {

    private final Punishments plugin = Punishments.getPlugin(Punishments.class);

    private final UUID target;
    private final String reason;
    private final PunishmentType punishmentType;
    private final long timeStamp;
    private final long endTimeStamp;

    public Punishment(UUID target, String reason, PunishmentType punishmentType, long endTimeStamp) {
        this.target = target;
        this.reason = reason;
        this.punishmentType = punishmentType;
        this.timeStamp = System.currentTimeMillis();
        this.endTimeStamp = endTimeStamp;
        this.plugin.getPunishmentManager().getPunishments().put(target, this);
    }

    public Punishment(UUID target, String reason, PunishmentType punishmentType, long timeStamp, long endTimeStamp) {
        this.target = target;
        this.reason = reason;
        this.punishmentType = punishmentType;
        this.timeStamp = timeStamp;
        this.endTimeStamp = endTimeStamp;
        this.plugin.getPunishmentManager().getPunishments().put(target, this);
    }
}
