package com.split.extraons;

import com.split.extraons.blocks.GamingChairBlock;
import com.split.extraons.extractor.ExtractorScreen;
import com.split.extraons.gamingchair.GamingChairSitEntity;
import com.split.extraons.gamingchair.S2CEntitySpawnPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class MainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.INSTANCE.register(Main.SIT_ENTITY_TYPE, (entityRenderDispatcher, context) -> new EmptyRenderer(entityRenderDispatcher));
        ScreenRegistry.register(Main.EXTRACTOR_SCREEN_HANDLER, ExtractorScreen::new);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> state.get(GamingChairBlock.COLORID).getFireworkColor(), Main.PLAIN_GAMING_CHAIR_BLOCK);
        registerClientboundPackets();
    }

    private static class EmptyRenderer extends EntityRenderer<GamingChairSitEntity>

    {
        protected EmptyRenderer(EntityRenderDispatcher entityRenderDispatcher)
        {
            super(entityRenderDispatcher);
        }

        @Override
        public boolean shouldRender(GamingChairSitEntity entity, Frustum frustum, double d, double e, double f)
        {
            return false;
        }

        @Override
        public Identifier getTexture(GamingChairSitEntity entity)
        {
            return null;
        }
    }

    public static void registerClientboundPackets() {
        ClientSidePacketRegistry.INSTANCE.register(S2CEntitySpawnPacket.ID, S2CEntitySpawnPacket::onPacket);

    }
}
