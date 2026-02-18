package twox.luminnautic;

import net.fabricmc.api.ModInitializer;
import twox.luminnautic.command.NauticDepthsCommand;
import twox.luminnautic.registry.ModBlocks;
import twox.luminnautic.registry.ModItemGroups;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Luminnautic implements ModInitializer {
	public static final String MOD_ID = "luminnautic";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.register();
		ModItemGroups.register();
		NauticDepthsCommand.register();
		LOGGER.info("Luminnautic initialized");
	}
}
