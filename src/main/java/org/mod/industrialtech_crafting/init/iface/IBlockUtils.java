package org.mod.industrialtech_crafting.init.iface;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class IBlockUtils {
    /**
     * ══════════════════════════════════════════════════════════════════════
     * ════════════════════════ Базовые методы регистрации ═══════════════════════
     * ══════════════════════════════════════════════════════════════════════
     */
    public static <T extends Block> RegistryObject<T> register(
            DeferredRegister<Block> blocks,
            String name,
            Supplier<T> blockSupplier) {
        RegistryObject<T> registered = blocks.register(name, blockSupplier);
        IItemsUtils.ITEMS.register(
                name,
                () -> new BlockItem(registered.get(), new Item.Properties()));
        return registered;
    }

    public static <T extends Block> RegistryObject<T> register(
            DeferredRegister<Block> blocks,
            String name,
            Supplier<T> blockSupplier,
            Rarity rarity) {
        RegistryObject<T> registered = blocks.register(name, blockSupplier);
        IItemsUtils.ITEMS.register(
                name,
                () -> new BlockItem(registered.get(), new Item.Properties().rarity(rarity)));
        return registered;
    }

    public static <T extends Block> RegistryObject<T> registerBlockOnly(
            DeferredRegister<Block> blocks,
            String name,
            Supplier<T> blockSupplier) {
        return blocks.register(name, blockSupplier);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(
            DeferredRegister<Block> register,
            String name,
            Supplier<T> block) {
        RegistryObject<T> registered = register.register(name, block);
        IItemsUtils.ITEMS.register(
                name,
                () -> new BlockItem(registered.get(), new Item.Properties()));
        return registered;
    }
    /**
     * ══════════════════════════════════════════════════════════════════════
     * ═════════════════════════════ Простые блоки ════════════════════════════
     * ══════════════════════════════════════════════════════════════════════
     */

    public static RegistryObject<Block> simpleBlock(DeferredRegister<Block> blocks, String name) {
        return simpleBlock(blocks, name, Blocks.STONE);
    }

    public static RegistryObject<Block> simpleBlock(DeferredRegister<Block> blocks, String name, Block baseBlock) {
        return registerBlock(blocks, name, () -> new Block(BlockBehaviour.Properties.copy(baseBlock)));
    }

    public static RegistryObject<Block> simpleNoLoot(DeferredRegister<Block> blocks, String name) {
        return simpleNoLoot(blocks, name, Blocks.STONE);
    }

    public static RegistryObject<Block> simpleNoLoot(DeferredRegister<Block> blocks, String name, Block baseBlock) {
        return registerBlock(blocks, name,
                () -> new Block(BlockBehaviour.Properties.copy(baseBlock).noLootTable()));
    }
    /**
     * ══════════════════════════════════════════════════════════════════════
     * ════════════════════════════════ Руды ════════════════════════════════
     * ══════════════════════════════════════════════════════════════════════
     */

    public static RegistryObject<Block> ore(DeferredRegister<Block> blocks, String name) {
        return createOre(blocks, name, Blocks.STONE, SoundType.STONE);
    }

    public static RegistryObject<Block> deepslateOre(DeferredRegister<Block> blocks, String name) {
        return createOre(blocks, name, Blocks.DEEPSLATE, SoundType.DEEPSLATE);
    }

    public static RegistryObject<Block> netherOre(DeferredRegister<Block> blocks, String name) {
        return createOre(blocks, name, Blocks.NETHERRACK, SoundType.NETHERRACK);
    }

    public static RegistryObject<Block> endOre(DeferredRegister<Block> blocks, String name) {
        return createOre(blocks, name, Blocks.END_STONE, SoundType.STONE);
    }
    /**
     * ══════════════════════════════════════════════════════════════════════
     * ═══════════════════════════ Технические блоки ═══════════════════════════
     * ══════════════════════════════════════════════════════════════════════
     */

    public static RegistryObject<Block> machine(DeferredRegister<Block> blocks, String name) {
        return register(blocks, name, () -> new Block(
                BlockBehaviour.Properties.of()
                        .sound(SoundType.METAL)
                        .strength(5.0F, 10.0F)
                        .requiresCorrectToolForDrops()
        ));
    }

    public static RegistryObject<Block> heavyMachine(DeferredRegister<Block> blocks, String name) {
        return register(blocks, name, () -> new Block(
                BlockBehaviour.Properties.of()
                        .sound(SoundType.METAL)
                        .strength(6.0F, 12.0F)
                        .requiresCorrectToolForDrops()
        ));
    }

    public static RegistryObject<Block> withTool(
            DeferredRegister<Block> blocks,
            String name,
            SoundType sound,
            float hardness,
            float resistance) {
        return register(blocks, name, () -> new Block(
                BlockBehaviour.Properties.of()
                        .sound(sound)
                        .strength(hardness, resistance)
                        .requiresCorrectToolForDrops()
        ));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  Вспомогательный метод для руд
    // ═══════════════════════════════════════════════════════════════════════════

    private static RegistryObject<Block> createOre(
            DeferredRegister<Block> blocks,
            String name,
            Block baseBlock,
            SoundType soundType) {
        return registerBlock(blocks, name,
                () -> new Block(BlockBehaviour.Properties.copy(baseBlock)
                        .sound(soundType)
                        .requiresCorrectToolForDrops()));
    }
}