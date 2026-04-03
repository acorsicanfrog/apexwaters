package com.acorsicanfrog.apexwaters.neoforge;

import com.acorsicanfrog.apexwaters.ApexWatersCommon;
import com.acorsicanfrog.apexwaters.client.model.GreatWhiteSharkModel;
import com.acorsicanfrog.apexwaters.client.renderer.GreatWhiteSharkRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = ApexWatersCommon.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = ApexWatersCommon.MOD_ID, value = Dist.CLIENT)
public class ApexWatersNeoForgeClient {
    public ApexWatersNeoForgeClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ApexWatersNeoForge.GREAT_WHITE_SHARK.get(), GreatWhiteSharkRenderer::new);
    }

    @SubscribeEvent
    static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GreatWhiteSharkModel.LAYER_LOCATION, GreatWhiteSharkModel::createBodyLayer);
    }
}