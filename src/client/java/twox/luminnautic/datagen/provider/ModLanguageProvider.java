package twox.luminnautic.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import twox.luminnautic.registry.GlowingCoralBlocks;
import twox.luminnautic.registry.ModBlocks;

public class ModLanguageProvider extends FabricLanguageProvider {
	public ModLanguageProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generateTranslations(TranslationBuilder translationBuilder) {
		for (GlowingCoralBlocks.GlowingCoralBlock coral : GlowingCoralBlocks.all()) {
			translationBuilder.add(coral.block(), coral.englishName());
		}
		// Portal block translation
		translationBuilder.add(ModBlocks.LUMIN_PORTAL_BLOCK, "Lumin Portal Block");
	}
}
