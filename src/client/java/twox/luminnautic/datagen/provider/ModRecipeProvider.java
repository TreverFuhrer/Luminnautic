package twox.luminnautic.datagen.provider;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import twox.luminnautic.registry.GlowingCoralBlocks;

public class ModRecipeProvider extends FabricRecipeProvider {
	public ModRecipeProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void buildRecipes(Consumer<FinishedRecipe> exporter) {
		for (GlowingCoralBlocks.GlowingCoralBlock coral : GlowingCoralBlocks.all()) {
			ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, coral.block())
				.requires(coral.baseCoralBlock())
				.requires(Items.GLOW_INK_SAC)
				.unlockedBy(getHasName(coral.baseCoralBlock()), has(coral.baseCoralBlock()))
				.save(exporter, coral.id());
		}
	}
}
