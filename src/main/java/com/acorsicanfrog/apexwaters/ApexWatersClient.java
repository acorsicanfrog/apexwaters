package com.acorsicanfrog.apexwaters;

import com.acorsicanfrog.apexwaters.client.model.GreatWhiteSharkModel;
import com.acorsicanfrog.apexwaters.client.renderer.GreatWhiteSharkRenderer;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = ApexWaters.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = ApexWaters.MODID, value = Dist.CLIENT)
public class ApexWatersClient {
    public ApexWatersClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ApexWaters.GREAT_WHITE_SHARK.get(), GreatWhiteSharkRenderer::new);
    }

    @SubscribeEvent
    static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GreatWhiteSharkModel.LAYER_LOCATION, GreatWhiteSharkModel::createBodyLayer);
    }

    @SubscribeEvent
    static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> -1, ApexWaters.GREAT_WHITE_SHARK_SPAWN_EGG.get());
    }
}