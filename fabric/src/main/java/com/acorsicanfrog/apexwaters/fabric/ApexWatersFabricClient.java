package com.acorsicanfrog.apexwaters.fabric;

import com.acorsicanfrog.apexwaters.client.model.GreatWhiteSharkModel;
import com.acorsicanfrog.apexwaters.client.renderer.GreatWhiteSharkRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class ApexWatersFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRenderers.register(ApexWatersFabric.GREAT_WHITE_SHARK, GreatWhiteSharkRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(GreatWhiteSharkModel.LAYER_LOCATION, GreatWhiteSharkModel::createBodyLayer);
    }
}