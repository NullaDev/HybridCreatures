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
            () -> new ForgeSpawnEggItem(EntityInit.CHICKEN_GHAST, 0xFFFFFF, 0xFF0000, new Item.Properties())));

    public static final RegistryObject<ForgeSpawnEggItem> PIG_SLIME_SPAWN_EGG = CreativeTabInit.addToTab(ITEMS.register("pig_slime_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.PIG_SLIME, 0xE58E89, 0xC6615A, new Item.Properties())));

    public static final RegistryObject<ForgeSpawnEggItem> SNOW_SHULKER_SPAWN_EGG = CreativeTabInit.addToTab(ITEMS.register("snow_shulker_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.SNOW_SHULKER, 0xE38A1D, 0xFFFFFF, new Item.Properties())));

    public static final RegistryObject<ForgeSpawnEggItem> SLIME_ENDER_MAN_SPAWN_EGG = CreativeTabInit.addToTab(ITEMS.register("slime_ender_man_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.SLIME_ENDER_MAN, 0x51A03E, 0x000000, new Item.Properties())));

    public static final RegistryObject<ForgeSpawnEggItem> CREEPER_CHICKEN_SPAWN_EGG = CreativeTabInit.addToTab(ITEMS.register("creeper_chicken_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.CREEPER_CHICKEN, 0x67CF55, 0xFFFFFF, new Item.Properties())));
}
