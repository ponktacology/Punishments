package me.ponktacology.punishments.config;

import lombok.Getter;
import me.ponktacology.punishments.Punishments;

@Getter
public class PunishmentsConfig {

    private final String mongoLink;

    public PunishmentsConfig(Punishments plugin) {
        this.mongoLink = plugin.getConfig().getString("mongo_link");
    }
}
