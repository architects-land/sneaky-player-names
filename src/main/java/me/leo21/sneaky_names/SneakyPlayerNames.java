package me.leo21.sneaky_names;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SneakyPlayerNames implements ModInitializer {
	public static final String MOD_ID = "sneaky_player_names";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing SneakyPlayerNames");
	}
}