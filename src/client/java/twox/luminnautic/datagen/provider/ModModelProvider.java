package twox.luminnautic.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import twox.luminnautic.registry.GlowingCoralBlocks;

public class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
		for (GlowingCoralBlocks.GlowingCoralBlock coral : GlowingCoralBlocks.all()) {
			TexturedModel.Provider texturedModel = TexturedModel.CUBE.updateTexture(textures -> {
				textures.put(TextureSlot.ALL, coral.textureId());
				textures.put(TextureSlot.PARTICLE, coral.textureId());
			});
			blockStateModelGenerator.createTrivialBlock(coral.block(), texturedModel);
		}
	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
	}
}
