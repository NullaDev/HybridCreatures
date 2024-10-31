package event;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.entity.*;
import cn.nulladev.hybridcreatures.init.EntityInit;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HybridCreatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.LLAMA_BLAZE.get(), LlamaBlaze.createAttributes().build());
        event.put(EntityInit.CHICKEN_GHAST.get(), ChickenGhast.createAttributes().build());
        event.put(EntityInit.PIG_SLIME.get(), Monster.createMonsterAttributes().build());
        event.put(EntityInit.SNOW_SHULKER.get(), SnowShulker.createAttributes().build());
        event.put(EntityInit.SLIME_ENDER_MAN.get(), SlimeEnderMan.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
//        event.register(
//                EntityInit.LLAMA_BLAZE.get(),
//                SpawnPlacements.Type.ON_GROUND,
//                Heightmap.Types.WORLD_SURFACE,
//                LlamaBlaze::canSpawn,
//                SpawnPlacementRegisterEvent.Operation.OR
//        );
    }

}
