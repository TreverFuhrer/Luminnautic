package twox.luminnautic.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import twox.luminnautic.registry.GlowingCoralBlocks;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
	public ModBlockLootTableProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate() {
		for (GlowingCoralBlocks.GlowingCoralBlock coral : GlowingCoralBlocks.all()) {
			add(coral.block(), createSingleItemTableWithSilkTouch(coral.block(), coral.deadCoralBlock()));
		}
	}
}
