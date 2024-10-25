package cn.nulladev.hybridcreatures;

import cn.nulladev.hybridcreatures.init.CreativeTabInit;
import cn.nulladev.hybridcreatures.init.EntityInit;
import cn.nulladev.hybridcreatures.init.ItemInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HybridCreatures.MODID)
public class HybridCreatures {

    public static final String MODID = "hybridcreatures";

    public HybridCreatures() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);
//        BlockInit.BLOCKS.register(bus);
        CreativeTabInit.TABS.register(bus);
        EntityInit.ENTITIES.register(bus);
//        BlockEntityInit.BLOCK_ENTITIES.register(bus);
//        MenuInit.MENU_TYPES.register(bus);
    }

}
