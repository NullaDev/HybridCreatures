package cn.nulladev.hybridcreatures.init;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HybridCreatures.MODID);

    public static final RegistryObject<EntityType<LlamaBlaze>> LLAMA_BLAZE = ENTITIES.register("llama_blaze",
            () -> EntityType.Builder.<LlamaBlaze>of(LlamaBlaze::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.8F).clientTrackingRange(8)
                    .build(new ResourceLocation(HybridCreatures.MODID, "llama_blaze").toString())
    );

    public static final RegistryObject<EntityType<ChickenGhast>> CHICKEN_GHAST = ENTITIES.register("chicken_ghast",
            () -> EntityType.Builder.<ChickenGhast>of(ChickenGhast::new, MobCategory.MONSTER)
                    .sized(4.0F, 4.0F).clientTrackingRange(10)
                    .build(new ResourceLocation(HybridCreatures.MODID, "chicken_ghast").toString())
    );

    public static final RegistryObject<EntityType<PigSlime>> PIG_SLIME = ENTITIES.register("pig_slime",
            () -> EntityType.Builder.<PigSlime>of(PigSlime::new, MobCategory.MONSTER)
                    .sized(2.04F, 2.04F).clientTrackingRange(10)
                    .build(new ResourceLocation(HybridCreatures.MODID, "pig_slime").toString())
    );

    public static final RegistryObject<EntityType<SnowShulker>> SNOW_SHULKER = ENTITIES.register("snow_shulker",
            () -> EntityType.Builder.<SnowShulker>of(SnowShulker::new, MobCategory.MONSTER)
                    .immuneTo(Blocks.POWDER_SNOW)
                    .sized(1.0F, 1.0F).clientTrackingRange(10)
                    .build(new ResourceLocation(HybridCreatures.MODID, "snow_shulker").toString())
    );
}
