package twox.luminnautic.registry;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import twox.luminnautic.Luminnautic;
import twox.luminnautic.block.LuminPortal;
import twox.luminnautic.block.LuminPortalBlock;


public final class ModBlocks {
	private static final int GLOWING_CORAL_LUMINANCE = 15;
	private static final Map<String, Block> REGISTERED_BLOCKS = new LinkedHashMap<>();

	// Portal block
	public static final Block LUMIN_PORTAL_BLOCK = registerBlock(
		"lumin_portal_block",
		() -> new LuminPortalBlock(BlockBehaviour.Properties.of()
        .strength(3.0f, 3.0f)
        .lightLevel(state -> 15)
        .noOcclusion())
	);

	public static final Block LUMIN_PORTAL = registerBlock(
  		  "lumin_portal",
		() -> new LuminPortal(BlockBehaviour.Properties.of()
		.strength(0f)
		.noCollission()
		.noOcclusion()
		.lightLevel(state -> 10)
		)
	);

	// Public registry API.
	public static void register() {
		GlowingCoralBlocks.init();
		Luminnautic.LOGGER.info("Registered {} blocks", REGISTERED_BLOCKS.size());
	}

	public static List<Block> all() {
		return List.copyOf(REGISTERED_BLOCKS.values());
	}

	public static List<GlowingCoralBlocks.GlowingCoralBlock> glowingCorals() {
		return GlowingCoralBlocks.all();
	}

	// Shared register helpers.
	static Block registerCoralBlock(String name, Block liveVariant, Block deadVariant) {
		return registerBlock(name, () -> new CoralBlock(
			deadVariant,
			BlockBehaviour.Properties.copy(liveVariant).lightLevel(state -> GLOWING_CORAL_LUMINANCE)
		));
	}

	static Block registerBlock(String name, Supplier<Block> blockFactory) {
		ResourceLocation id = new ResourceLocation(Luminnautic.MOD_ID, name);
		Block block = Registry.register(BuiltInRegistries.BLOCK, id, blockFactory.get());
		Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties()));
		REGISTERED_BLOCKS.put(name, block);
		return block;
	}

	static String toEnglishName(String idPath) {
		String[] words = idPath.split("_");
		StringBuilder title = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			title.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
			if (i < words.length - 1) {
				title.append(' ');
			}
		}
		return title.toString();
	}
}
