package twox.luminnautic.datagen.provider;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import twox.luminnautic.registry.ModTags;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public ModItemTagProvider(
		FabricDataOutput output,
		CompletableFuture<HolderLookup.Provider> registriesFuture,
		FabricTagProvider.BlockTagProvider blockTagProvider
	) {
		super(output, registriesFuture, blockTagProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		copy(ModTags.Blocks.GLOWING_CORAL_BLOCKS, ModTags.Items.GLOWING_CORAL_BLOCKS);
	}
}
