package twox.luminnautic.registry;

import java.util.List;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import twox.luminnautic.Luminnautic;

public final class GlowingCoralBlocks {
	// Add new glowing coral entries here.
	public static final GlowingCoralBlock GLOWING_BRAIN_CORAL = register(
		"glowing_brain_coral_block",
		Blocks.BRAIN_CORAL_BLOCK,
		Blocks.DEAD_BRAIN_CORAL_BLOCK
	);
	public static final GlowingCoralBlock GLOWING_BUBBLE_CORAL = register(
		"glowing_bubble_coral_block",
		Blocks.BUBBLE_CORAL_BLOCK,
		Blocks.DEAD_BUBBLE_CORAL_BLOCK
	);
	public static final GlowingCoralBlock GLOWING_FIRE_CORAL = register(
		"glowing_fire_coral_block",
		Blocks.FIRE_CORAL_BLOCK,
		Blocks.DEAD_FIRE_CORAL_BLOCK
	);
	public static final GlowingCoralBlock GLOWING_HORN_CORAL = register(
		"glowing_horn_coral_block",
		Blocks.HORN_CORAL_BLOCK,
		Blocks.DEAD_HORN_CORAL_BLOCK
	);
	public static final GlowingCoralBlock GLOWING_TUBE_CORAL = register(
		"glowing_tube_coral_block",
		Blocks.TUBE_CORAL_BLOCK,
		Blocks.DEAD_TUBE_CORAL_BLOCK
	);

	// Keep this list in sync with constants above.
	private static final List<GlowingCoralBlock> ALL = List.of(
		GLOWING_BRAIN_CORAL,
		GLOWING_BUBBLE_CORAL,
		GLOWING_FIRE_CORAL,
		GLOWING_HORN_CORAL,
		GLOWING_TUBE_CORAL
	);

	// Optional direct block aliases.
	public static final Block GLOWING_BRAIN_CORAL_BLOCK = GLOWING_BRAIN_CORAL.block();
	public static final Block GLOWING_BUBBLE_CORAL_BLOCK = GLOWING_BUBBLE_CORAL.block();
	public static final Block GLOWING_FIRE_CORAL_BLOCK = GLOWING_FIRE_CORAL.block();
	public static final Block GLOWING_HORN_CORAL_BLOCK = GLOWING_HORN_CORAL.block();
	public static final Block GLOWING_TUBE_CORAL_BLOCK = GLOWING_TUBE_CORAL.block();

	// Called by ModBlocks.register() to force class initialization.
	static void init() {
	}

	public static List<GlowingCoralBlock> all() {
		return ALL;
	}

	public static List<Block> allBlocks() {
		return ALL.stream().map(GlowingCoralBlock::block).toList();
	}

	// Custom register helpers.
	private static GlowingCoralBlock register(String name, Block liveVariant, Block deadVariant) {
		return new GlowingCoralBlock(name, ModBlocks.registerCoralBlock(name, liveVariant, deadVariant), liveVariant, deadVariant);
	}

	// Metadata used by datagen/recipes/tags.
	public record GlowingCoralBlock(String name, Block block, Block baseCoralBlock, Block deadCoralBlock) {
		public ResourceLocation id() {
			return new ResourceLocation(Luminnautic.MOD_ID, name);
		}

		public ResourceLocation textureId() {
			return new ResourceLocation(Luminnautic.MOD_ID, "block/glow_coral/" + name);
		}

		public String englishName() {
			return ModBlocks.toEnglishName(name);
		}
	}
}
