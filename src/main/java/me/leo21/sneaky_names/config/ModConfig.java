package me.leo21.sneaky_names.config;

import me.leo21.sneaky_names.SneakyPlayerNames;

public class ModConfig {
    public boolean HIDE_PLAYER_NAMES = true;
    public int MAX_DISTANCE = 30;

    public ModConfig() {
        SimpleConfig config = SimpleConfig.of(SneakyPlayerNames.MOD_ID).provider(this::provider).request();

        this.HIDE_PLAYER_NAMES = config.getOrDefault( "hide_player_names", this.HIDE_PLAYER_NAMES);
        this.MAX_DISTANCE = config.getOrDefault( "max_distance", this.MAX_DISTANCE);
    }

    private String provider( String filename ) {
        return "# " + SneakyPlayerNames.MOD_NAME + " configuration file\n\n" +
                "# Whether to hide out of sight players' names or not\n" +
                "hide_player_names = " + this.HIDE_PLAYER_NAMES +"\n\n" +
                "# The furthest you can see a player name (in blocks).\n" +
                "# A negative distance represents an infinite distance, and 0 will never show names.\n" +
                "max_distance = " + this.MAX_DISTANCE;
    }
}
