package cn.nulladev.hybridcreatures.client.event;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.client.model.*;
import cn.nulladev.hybridcreatures.client.renderer.*;
import cn.nulladev.hybridcreatures.init.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HybridCreatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModHandler {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Entities
        event.registerEntityRenderer(EntityInit.LLAMA_BLAZE.get(), LlamaBlazeRenderer::new);
        event.registerEntityRenderer(EntityInit.CHICKEN_GHAST.get(), ChickenGhastRenderer::new);
        event.registerEntityRenderer(EntityInit.PIG_SLIME.get(), PigSlimeRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LlamaBlazeModel.LAYER_LOCATION, LlamaBlazeModel::createBodyLayer);
        event.registerLayerDefinition(ChickenGhastModel.LAYER_LOCATION, ChickenGhastModel::createBodyLayer);
        event.registerLayerDefinition(PigSlimeModel.LAYER_LOCATION, PigSlimeModel::createBodyLayer);
        event.registerLayerDefinition(PigSlimeModel.LAYER_LOCATION_OUTER, PigSlimeModel::createOuterLayer);
    }
}
