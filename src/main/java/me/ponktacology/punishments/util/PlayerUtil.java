package me.ponktacology.punishments.util;

import org.bukkit.Bukkit;

import java.util.UUID;

public class PlayerUtil {

    private static final String UUID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}";

    @SuppressWarnings("deprecation")
    public static UUID getUUID(String input) {
        UUID toReturn = null;
        if (input.matches(UUID_REGEX)) {
            if (Bukkit.getPlayer(UUID.fromString(input)) != null) {
                toReturn = Bukkit.getPlayer(UUID.fromString(input)).getUniqueId();
            } else if (Bukkit.getOfflinePlayer(UUID.fromString(input)) != null) {
                toReturn = Bukkit.getOfflinePlayer(UUID.fromString(input)).getUniqueId();
            }
        } else {
            if (Bukkit.getPlayer(input) != null) {
                toReturn = Bukkit.getPlayer(input).getUniqueId();
            } else if (Bukkit.getOfflinePlayer(input) != null) {
                toReturn = Bukkit.getOfflinePlayer(input).getUniqueId();
            }
        }
        return toReturn;
    }
}
