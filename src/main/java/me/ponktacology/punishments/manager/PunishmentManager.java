package me.ponktacology.punishments.manager;

import lombok.Getter;
import me.ponktacology.punishments.Punishments;
import me.ponktacology.punishments.punishment.Punishment;
import me.ponktacology.punishments.punishment.PunishmentType;
import org.bson.Document;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

@Getter
public class PunishmentManager {


    private final Punishments plugin;
    private final HashMap<UUID, Punishment> punishments;

    public PunishmentManager(Punishments plugin) {
        this.plugin = plugin;
        this.punishments = new HashMap<>();
    }

    public void loadAllPunishments() {
        this.plugin.getDataManager().getPunishments().find().forEach((Consumer<? super Document>) punishment -> {
            new Punishment(UUID.fromString(punishment.getString("target")),
                    punishment.getString("reason"),
                    PunishmentType.valueOf(punishment.getString("type")),
                    punishment.getLong("timeStamp"),
                    punishment.getLong("endTimeStamp"));
        });
    }

    //It's a bit stupid but it's working well enough.
    public void saveAllPunishments() {
        this.plugin.getDataManager().getPunishments().find().forEach((Consumer<? super Document>) punishment -> {
            this.plugin.getDataManager().getPunishments().deleteOne(punishment);
        });
        this.punishments.values().forEach(punishment -> {
            Document document = new Document("target", punishment.getTarget().toString());
            document.append("reason", punishment.getReason())
                    .append("type", punishment.getPunishmentType().toString())
                    .append("timeStamp", punishment.getTimeStamp())
                    .append("endTimeStamp", punishment.getEndTimeStamp());
            this.plugin.getDataManager().getPunishments().insertOne(document);
        });
    }
}
