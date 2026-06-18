package org.mod.industrialtech_crafting.api.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mod.industrialtech_crafting.init.iface.IItemsUtils;

import java.util.function.Supplier;

/**
 * Реализация Builder для создания блоков.
 * Перед использованием вызовите BlockBuilderImpl.init(ваш_реестр_блоков).
 */
public class BlockBuilderImpl implements IBlockBuilder {

    private static DeferredRegister<Block> BLOCKS_REGISTRY;
    private static DeferredRegister<Item> ITEMS_REGISTRY;

    /**
     * Инициализация билдера – передайте свой реестр блоков.
     * Вызывать один раз в конструкторе мода.
     */
    public static void init(DeferredRegister<Block> blocks) {
        BLOCKS_REGISTRY = blocks;
    }

    /**
     * Инициализация билдера с реестром блоков и предметов.
     * Вызывать один раз в конструкторе мода для внешних модов.
     */
    public static void init(DeferredRegister<Block> blocks, DeferredRegister<Item> items) {
        BLOCKS_REGISTRY = blocks;
        ITEMS_REGISTRY = items;
    }

    /**
     * Устанавливает реестр предметов для текущего builder'а.
     * Используется для кросс-модовой регистрации.
     */
    public BlockBuilderImpl items(DeferredRegister<Item> items) {
        this.customItemsRegistry = items;
        return this;
    }

    private final String name;
    private Block baseBlock = Blocks.STONE;
    private SoundType sound = SoundType.STONE;
    private float hardness = 1.5F;
    private float resistance = 6.0F;
    private boolean requiresTool = false;
    private boolean noLootTable = false;
    private int maxStackSize = 64;
    private Supplier<? extends Block> customBlockSupplier;
    private DeferredRegister<Item> customItemsRegistry; // Для внешних модов

    private BlockBuilderImpl(String name) {
        this.name = name;
    }

    /**
     * Создаёт новый builder для блока.
     */
    public static BlockBuilderImpl create(String name) {
        return new BlockBuilderImpl(name);
    }

    /**
     * Пресет: обычная руда.
     */
    public static BlockBuilderImpl ore(String name) {
        return create(name)
                .base(Blocks.STONE)
                .sound(SoundType.STONE)
                .requiresTool();
    }

    /**
     * Пресет: руда в дисельт.
     */
    public static BlockBuilderImpl deepslateOre(String name) {
        return create(name)
                .base(Blocks.DEEPSLATE)
                .sound(SoundType.DEEPSLATE)
                .requiresTool();
    }

    /**
     * Пресет: руда в Нижний мир.
     */
    public static BlockBuilderImpl netherOre(String name) {
        return create(name)
                .base(Blocks.NETHERRACK)
                .sound(SoundType.NETHERRACK)
                .requiresTool();
    }

    /**
     * Пресет: руда в End.
     */
    public static BlockBuilderImpl endOre(String name) {
        return create(name)
                .base(Blocks.END_STONE)
                .sound(SoundType.STONE)
                .requiresTool();
    }

    /**
     * Пресет: технический блок.
     */
    public static BlockBuilderImpl machine(String name) {
        return create(name)
                .base(Blocks.IRON_BLOCK)
                .sound(SoundType.METAL)
                .hardness(5.0F)
                .resistance(10.0F)
                .requiresTool();
    }

    /**
     * Пресет: тяжёлый технический блок.
     */
    public static BlockBuilderImpl heavyMachine(String name) {
        return machine(name)
                .hardness(6.0F)
                .resistance(12.0F);
    }

    // ==========================================
    // Методы настройки (fluent)
    // ==========================================

    @Override
    public BlockBuilderImpl base(Block block) {
        this.baseBlock = block;
        return this;
    }

    @Override
    public BlockBuilderImpl sound(SoundType sound) {
        this.sound = sound;
        return this;
    }

    @Override
    public BlockBuilderImpl hardness(float hardness) {
        this.hardness = hardness;
        return this;
    }

    @Override
    public BlockBuilderImpl resistance(float resistance) {
        this.resistance = resistance;
        return this;
    }

    @Override
    public BlockBuilderImpl requiresTool() {
        this.requiresTool = true;
        return this;
    }

    @Override
    public BlockBuilderImpl noLootTable() {
        this.noLootTable = true;
        return this;
    }

    @Override
    public BlockBuilderImpl maxStackSize(int size) {
        this.maxStackSize = size;
        return this;
    }

    /**
     * Устанавливает кастомный поставщик блока.
     */
    public BlockBuilderImpl customBlock(Supplier<? extends Block> supplier) {
        this.customBlockSupplier = supplier;
        return this;
    }

    // ==========================================
    // Сборка
    // ==========================================

    @Override
    public RegistryObject<Block> build() {
        if (BLOCKS_REGISTRY == null) {
            throw new IllegalStateException("BlockBuilderImpl не инициализирован! Вызовите BlockBuilderImpl.init(реестр) перед использованием.");
        }

        // Определяем реестр предметов (приоритет: custom > static > fallback)
        DeferredRegister<Item> itemsRegistry = customItemsRegistry != null ? customItemsRegistry : ITEMS_REGISTRY;
        if (itemsRegistry == null) {
            throw new IllegalStateException("BlockBuilderImpl не инициализирован для предметов! Вызовите init(blocks, items) или используйте .items(реестр)");
        }

        // Если задан кастомный поставщик – используем его
        if (customBlockSupplier != null) {
            RegistryObject<Block> block = BLOCKS_REGISTRY.register(name, customBlockSupplier);
            itemsRegistry.register(name,
                    () -> new BlockItem(block.get(), new Item.Properties().stacksTo(maxStackSize)));
            return block;
        }

        // Стандартный блок
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.copy(baseBlock)
                .sound(sound)
                .strength(hardness, resistance);

        if (requiresTool) {
            properties.requiresCorrectToolForDrops();
        }
        if (noLootTable) {
            properties.noLootTable();
        }

        RegistryObject<Block> block = BLOCKS_REGISTRY.register(name, () -> new Block(properties));
        itemsRegistry.register(name,
                () -> new BlockItem(block.get(), new Item.Properties().stacksTo(maxStackSize)));
        return block;
    }
}