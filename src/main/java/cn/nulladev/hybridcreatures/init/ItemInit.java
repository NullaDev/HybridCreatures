package cn.nulladev.hybridcreatures.init;

import cn.nulladev.hybridcreatures.HybridCreatures;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HybridCreatures.MODID);

    public static final RegistryObject<ForgeSpawnEggItem> LLAMA_BLAZE_SPAWN_EGG = CreativeTabInit.addToTab(ITEMS.register("llama_blaze_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.LLAMA_BLAZE, 0xFCEBC6, 0x375482, new Item.Properties())));

    public static final RegistryObject<ForgeSpawnEggItem> CHICKEN_GHAST_SPAWN_EGG = CreativeTabInit.addToTab(ITEMS.register("chicken_ghast_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.CHICKEN_GHAST, 0xFCEBC6, 0x375482, new Item.Properties())));
}
