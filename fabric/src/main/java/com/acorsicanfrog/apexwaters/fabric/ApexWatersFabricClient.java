package com.acorsicanfrog.apexwaters.fabric;

import com.acorsicanfrog.apexwaters.client.model.GreatWhiteSharkModel;
import com.acorsicanfrog.apexwaters.client.renderer.GreatWhiteSharkRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ApexWatersFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ApexWatersFabric.GREAT_WHITE_SHARK, GreatWhiteSharkRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(GreatWhiteSharkModel.LAYER_LOCATION, GreatWhiteSharkModel::createBodyLayer);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> -1, ApexWatersFabric.SPAWN_EGG);
    }
}