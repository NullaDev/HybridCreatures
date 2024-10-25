package cn.nulladev.hybridcreatures.init;

import cn.nulladev.hybridcreatures.HybridCreatures;
import cn.nulladev.hybridcreatures.entity.LlamaBlaze;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HybridCreatures.MODID);

    public static final RegistryObject<EntityType<LlamaBlaze>> LLAMA_BLAZE = ENTITIES.register("example_entity",
            () -> EntityType.Builder.<LlamaBlaze>of(LlamaBlaze::new, MobCategory.MONSTER)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(HybridCreatures.MODID, "llama_blaze").toString())
    );
}
