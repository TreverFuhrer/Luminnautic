package twox.luminnautic.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import twox.luminnautic.Luminnautic;

public final class ModItemGroups {
	public static void register() {
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries ->
			ModBlocks.all().forEach(entries::accept)
		);
		Luminnautic.LOGGER.info("Registered item group entries for {} blocks", ModBlocks.all().size());
	}
}
