package cn.nulladev.hybridcreatures.init;

import cn.nulladev.hybridcreatures.HybridCreatures;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HybridCreatures.MODID);

    public static final RegistryObject<ForgeSpawnEggItem> EXAMPLE_SPAWN_EGG = CreativeTabInit.addToTab(ITEMS.register("example_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.LLAMA_BLAZE, 0xF0ABD1, 0xAE4C82, new Item.Properties())));
}
