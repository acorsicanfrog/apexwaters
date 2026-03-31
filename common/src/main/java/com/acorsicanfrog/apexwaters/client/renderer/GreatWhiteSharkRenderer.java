package com.acorsicanfrog.apexwaters.client.renderer;

import com.acorsicanfrog.apexwaters.ApexWatersCommon;
import com.acorsicanfrog.apexwaters.client.model.GreatWhiteSharkModel;
import com.acorsicanfrog.apexwaters.entity.GreatWhiteSharkEntity;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;

public class GreatWhiteSharkRenderer extends MobRenderer<GreatWhiteSharkEntity, GreatWhiteSharkModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "textures/entity/great_white_shark.png");
    private static final ResourceLocation EPIC_TEXTURE = ResourceLocation.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "textures/entity/great_white_shark_epic.png");
    private static final double EXTRA_CULLING_MARGIN_HORIZONTAL = 4.0D;
    private static final double EXTRA_CULLING_MARGIN_VERTICAL = 1.5D;

    public GreatWhiteSharkRenderer(EntityRendererProvider.Context context) {
        super(context, new GreatWhiteSharkModel(context.bakeLayer(GreatWhiteSharkModel.LAYER_LOCATION)), 0.7F);
    }

    @Override
    public boolean shouldRender(GreatWhiteSharkEntity entity, Frustum frustum, double x, double y, double z) {
        if (super.shouldRender(entity, frustum, x, y, z)) {
            return true;
        }

        AABB expandedBounds = entity.getBoundingBoxForCulling().inflate(EXTRA_CULLING_MARGIN_HORIZONTAL, EXTRA_CULLING_MARGIN_VERTICAL, EXTRA_CULLING_MARGIN_HORIZONTAL);
        return frustum.isVisible(expandedBounds);
    }

    @Override
    public ResourceLocation getTextureLocation(GreatWhiteSharkEntity entity) {
        return entity.isLegendaryShark() ? EPIC_TEXTURE : TEXTURE;
    }
}