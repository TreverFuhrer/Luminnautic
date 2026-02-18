package twox.luminnautic.datagen.provider;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import twox.luminnautic.registry.GlowingCoralBlocks;
import twox.luminnautic.registry.ModTags;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		for (GlowingCoralBlocks.GlowingCoralBlock coral : GlowingCoralBlocks.all()) {
			getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(coral.block());
			getOrCreateTagBuilder(BlockTags.CORAL_BLOCKS).add(coral.block());
			getOrCreateTagBuilder(ModTags.Blocks.GLOWING_CORAL_BLOCKS).add(coral.block());
		}
	}
}
