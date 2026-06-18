package org.mod.industrialtech_crafting.api;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mod.industrialtech_crafting.api.block.BlockBuilderImpl;
import org.mod.industrialtech_crafting.api.block.MachineBlockFactory;
import org.mod.industrialtech_crafting.api.item.ItemBuilderImpl;
import org.mod.industrialtech_crafting.init.iface.IBlockEntityUtils;
import org.mod.industrialtech_crafting.init.iface.IBlockUtils;
import org.mod.industrialtech_crafting.init.iface.IItemsUtils;

import java.util.function.Supplier;

/**
 * Главный фасад API модификации.
 * Позволяет использовать все API методы через один импорт.
 * ══════════════════════════════════════════════════════════════════════
 * Пример использования:
 * import static org.mod.industrialtech_crafting.api.ModAPI.*;
 * ══════════════════════════════════════════════════════════════════════
 * public static final RegistryObject<Block> myBlock = machine("my_machine");
 * ══════════════════════════════════════════════════════════════════════
 * public static final RegistryObject<Item> myIngot = ingot("my_ingot");
 */
public class ModAPI {

    private static DeferredRegister<Block> BLOCKS;
    private static DeferredRegister<Item> ITEMS;
    private static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES;

    // ==========================================
    // Инициализация
    // ==========================================

    public static void initBlocks(DeferredRegister<Block> blocks) {
        BLOCKS = blocks;
    }

    public static void initItems(DeferredRegister<Item> items) {
        ITEMS = items;
    }

    public static void initBlockEntities(DeferredRegister<BlockEntityType<?>> blockEntities) {
        BLOCK_ENTITIES = blockEntities;
    }

    // ==========================================
    // Block API (используют BLOCKS)
    // ==========================================

    public static RegistryObject<Block> simple(String name) {
        checkBlocks();
        checkItems();
        return IBlockUtils.simpleBlock(BLOCKS, ITEMS, name);
    }

    public static RegistryObject<Block> simple(String name, Block base) {
        checkBlocks();
        checkItems();
        return IBlockUtils.simpleBlock(BLOCKS, ITEMS, name, base);
    }

    public static RegistryObject<Block> simpleNoLoot(String name, Block base) {
        checkBlocks();
        checkItems();
        return IBlockUtils.simpleNoLoot(BLOCKS, ITEMS, name, base);
    }

    public static RegistryObject<Block> simpleNoLoot(String name) {
        checkBlocks();
        checkItems();
        return IBlockUtils.simpleNoLoot(BLOCKS, ITEMS, name);
    }

    public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier) {
        checkBlocks();
        checkItems();
        return IBlockUtils.register(BLOCKS, ITEMS, name, blockSupplier);
    }

    public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, Rarity rarity) {
        checkBlocks();
        checkItems();
        return IBlockUtils.register(BLOCKS, ITEMS, name, blockSupplier, rarity);
    }

    public static RegistryObject<Block> ore(String name) {
        checkBlocks();
        checkItems();
        return IBlockUtils.ore(BLOCKS, ITEMS, name);
    }

    public static RegistryObject<Block> deepslateOre(String name) {
        checkBlocks();
        checkItems();
        return IBlockUtils.deepslateOre(BLOCKS, ITEMS, name);
    }

    public static RegistryObject<Block> netherOre(String name) {
        checkBlocks();
        checkItems();
        return IBlockUtils.netherOre(BLOCKS, ITEMS, name);
    }

    public static RegistryObject<Block> endOre(String name) {
        checkBlocks();
        checkItems();
        return IBlockUtils.endOre(BLOCKS, ITEMS, name);
    }

    public static RegistryObject<Block> machine(String name) {
        checkBlocks();
        checkItems();
        return IBlockUtils.machine(BLOCKS, ITEMS, name);
    }

    public static RegistryObject<Block> heavyMachine(String name) {
        checkBlocks();
        checkItems();
        return IBlockUtils.heavyMachine(BLOCKS, ITEMS, name);
    }
    // ==========================================
    // BlockEntity API (используют BLOCK_ENTITIES)
    // ==========================================

    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> blockEntity(
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            Supplier<? extends Block> block) {
        checkBlockEntities();
        return IBlockEntityUtils.registerEntities(BLOCK_ENTITIES, name, factory, block);
    }

    @SafeVarargs
    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> blockEntity(
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            Supplier<? extends Block>... blocks) {
        checkBlockEntities();
        return IBlockEntityUtils.registerEntities(BLOCK_ENTITIES, name, factory, blocks);
    }

    // ==========================================
    // Item API (используют ITEMS)
    // ==========================================

    public static RegistryObject<Item> item(String name) {
        checkItems();
        return IItemsUtils.item(ITEMS, name);
    }

    public static RegistryObject<Item> itemRare(String name) {
        checkItems();
        return IItemsUtils.itemRare(ITEMS, name);
    }

    public static RegistryObject<Item> itemEpic(String name) {
        checkItems();
        return IItemsUtils.itemEpic(ITEMS, name);
    }

    public static RegistryObject<Item> itemLegendary(String name) {
        checkItems();
        return IItemsUtils.itemLegendary(ITEMS, name);
    }

    public static RegistryObject<Item> itemMythic(String name) {
        checkItems();
        return IItemsUtils.itemMythic(ITEMS, name);
    }

    public static RegistryObject<Item> material(String name) {
        checkItems();
        return IItemsUtils.material(ITEMS, name);
    }

    public static RegistryObject<Item> materialRare(String name) {
        checkItems();
        return IItemsUtils.materialRare(ITEMS, name);
    }

    public static RegistryObject<Item> materialEpic(String name) {
        checkItems();
        return IItemsUtils.materialEpic(ITEMS, name);
    }

    public static RegistryObject<Item> materialLegendary(String name) {
        checkItems();
        return IItemsUtils.materialLegendary(ITEMS, name);
    }

    public static RegistryObject<Item> materialMythic(String name) {
        checkItems();
        return IItemsUtils.materialMythic(ITEMS, name);
    }

    public static RegistryObject<Item> food(String name, FoodProperties food) {
        checkItems();
        return IItemsUtils.food(ITEMS, name, food);
    }

    public static RegistryObject<Item> food(String name, int nutrition, float saturation) {
        checkItems();
        return IItemsUtils.food(ITEMS, name, nutrition, saturation);
    }

    public static RegistryObject<Item> food(String name, int nutrition, float saturation, boolean isMeat) {
        checkItems();
        return IItemsUtils.food(ITEMS, name, nutrition, saturation, isMeat);
    }

    // ==========================================
    // Item API с передачей реестра (для внешних модов)
    // ==========================================

    public static RegistryObject<Item> item(DeferredRegister<Item> items, String name) {
        return IItemsUtils.item(items, name);
    }

    public static RegistryObject<Item> material(DeferredRegister<Item> items, String name) {
        return IItemsUtils.material(items, name);
    }

    // ==========================================
    // Builder API (требуют отдельной инициализации)
    // ==========================================

    public static BlockBuilderImpl block(String name) {
        return BlockBuilderImpl.create(name);
    }

    public static BlockBuilderImpl oreBlock(String name) {
        return BlockBuilderImpl.ore(name);
    }

    public static BlockBuilderImpl deepslateOreBlock(String name) {
        return BlockBuilderImpl.deepslateOre(name);
    }

    public static BlockBuilderImpl netherOreBlock(String name) {
        return BlockBuilderImpl.netherOre(name);
    }

    public static BlockBuilderImpl endOreBlock(String name) {
        return BlockBuilderImpl.endOre(name);
    }

    public static BlockBuilderImpl machineBlock(String name) {
        return BlockBuilderImpl.machine(name);
    }

    public static BlockBuilderImpl heavyMachineBlock(String name) {
        return BlockBuilderImpl.heavyMachine(name);
    }

    public static ItemBuilderImpl items(String name) {
        return ItemBuilderImpl.create(name);
    }

    public static ItemBuilderImpl ingotItem(String name) {
        return ItemBuilderImpl.ingot(name);
    }

    public static ItemBuilderImpl rareIngot(String name) {
        return ItemBuilderImpl.itemRare(name);
    }

    public static ItemBuilderImpl epicIngot(String name) {
        return ItemBuilderImpl.ingotEpic(name);
    }

    public static ItemBuilderImpl ingotLegendary(String name) {
        return ItemBuilderImpl.ingotLegendary(name);
    }

    public static ItemBuilderImpl ingotMythic(String name) {
        return ItemBuilderImpl.ingotMythic(name);
    }

    // ==========================================
    // Machine API
    // ==========================================

    public static RegistryObject<Block> createMachine(String name, Supplier<? extends Block> blockSupplier) {
        return MachineBlockFactory.createWithGui(name, blockSupplier);
    }

    // ==========================================
    // Проверки инициализации
    // ==========================================

    private static void checkBlocks() {
        if (BLOCKS == null) {
            throw new IllegalStateException("ModAPI не инициализирован для блоков! Вызовите initBlocks()");
        }
    }

    private static void checkItems() {
        if (ITEMS == null) {
            throw new IllegalStateException("ModAPI не инициализирован для предметов! Вызовите initItems()");
        }
    }

    private static void checkBlockEntities() {
        if (BLOCK_ENTITIES == null) {
            throw new IllegalStateException("ModAPI не инициализирован для BlockEntity! Вызовите initBlockEntities()");
        }
    }
}
