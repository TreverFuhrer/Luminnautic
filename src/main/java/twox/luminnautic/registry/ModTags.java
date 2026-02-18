package twox.luminnautic.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import twox.luminnautic.Luminnautic;

public final class ModTags {
	public static final class Blocks {
		public static final TagKey<Block> GLOWING_CORAL_BLOCKS = createBlockTag("glowing_coral_blocks");
	}

	public static final class Items {
		public static final TagKey<Item> GLOWING_CORAL_BLOCKS = createItemTag("glowing_coral_blocks");
	}

	private static TagKey<Block> createBlockTag(String path) {
		return TagKey.create(Registries.BLOCK, new ResourceLocation(Luminnautic.MOD_ID, path));
	}

	private static TagKey<Item> createItemTag(String path) {
		return TagKey.create(Registries.ITEM, new ResourceLocation(Luminnautic.MOD_ID, path));
	}
}
