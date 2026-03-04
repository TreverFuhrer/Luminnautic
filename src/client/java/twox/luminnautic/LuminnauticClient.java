package twox.luminnautic;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.BiomeColors;
import twox.luminnautic.registry.ModBlocks;

public class LuminnauticClient implements ClientModInitializer {
	private static final int DEFAULT_WATER_COLOR = 0x3F76E4;

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LUMIN_PORTAL, RenderType.translucent());
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if (tintIndex != 0) {
				return -1;
			}
			if (world != null && pos != null) {
				return BiomeColors.getAverageWaterColor(world, pos);
			}
			return DEFAULT_WATER_COLOR;
		}, ModBlocks.LUMIN_PORTAL);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 0 ? DEFAULT_WATER_COLOR : -1,
			ModBlocks.LUMIN_PORTAL);
	}
}
