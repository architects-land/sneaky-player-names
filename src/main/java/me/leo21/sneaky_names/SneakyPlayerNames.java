package me.leo21.sneaky_names;

import me.leo21.sneaky_names.config.ModConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SneakyPlayerNames implements ModInitializer {
	public static final String MOD_ID = "sneaky_player_names";
	public static final String MOD_NAME = "Sneaky Player Names";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ModConfig CONFIG = new ModConfig();

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing SneakyPlayerNames");
	}
}