package org.mod.industrialtech_crafting;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.mod.industrialtech_crafting.api.ModAPI;
import org.mod.industrialtech_crafting.init.ModBlocks;
import org.mod.industrialtech_crafting.init.iface.IBlockEntityUtils;
import org.mod.industrialtech_crafting.init.iface.IItemsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Industrialtech_crafting.MOD_ID)
public class Industrialtech_crafting {
    public static final String MOD_ID = "industrialtech_crafting";
    public static final String NAME = "IndustrialTech: Crafting";
    public static final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static final DeferredRegister<Block> BLOCK =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Industrialtech_crafting.MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Industrialtech_crafting.MOD_ID);

    public Industrialtech_crafting() {

        ModAPI.initBlocks(BLOCK);
        ModAPI.initItems(ITEMS);
        ModBlocks.init();
        Industrialtech_crafting.BLOCK.register(modEventBus);
        Industrialtech_crafting.ITEMS.register(modEventBus);
        IItemsUtils.ITEMS.register(modEventBus);
        IBlockEntityUtils.BLOCK_ENTITIES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
