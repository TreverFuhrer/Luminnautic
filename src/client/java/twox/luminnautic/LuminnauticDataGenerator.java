package twox.luminnautic;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import twox.luminnautic.datagen.provider.ModBlockLootTableProvider;
import twox.luminnautic.datagen.provider.ModBlockTagProvider;
import twox.luminnautic.datagen.provider.ModItemTagProvider;
import twox.luminnautic.datagen.provider.ModLanguageProvider;
import twox.luminnautic.datagen.provider.ModModelProvider;
import twox.luminnautic.datagen.provider.ModRecipeProvider;

public class LuminnauticDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		FabricTagProvider.BlockTagProvider blockTagProvider = pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider((output, registriesFuture) -> new ModItemTagProvider(output, registriesFuture, blockTagProvider));
		pack.addProvider(ModLanguageProvider::new);
	}
}
