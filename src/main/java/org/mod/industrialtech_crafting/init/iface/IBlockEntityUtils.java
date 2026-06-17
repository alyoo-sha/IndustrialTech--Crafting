package org.mod.industrialtech_crafting.init.iface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mod.industrialtech_crafting.Industrialtech_crafting;

import java.util.function.Supplier;

public class IBlockEntityUtils {
    /**
     * Регистрация BlockEntityType для одного связанного блока
     * @param name имя (registry name)
     * @param factory фабрика, создающая экземпляр BlockEntity (например, GeneratorBlockEntity::new)
     * @param block связанный блок
     * @return RegistryObject<BlockEntityType<T>>
     */
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Industrialtech_crafting.MOD_ID);

    // ==========================================
    // Вспомогательный метод регистрации
    // ==========================================

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(
            DeferredRegister<BlockEntityType<?>> register,
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            Supplier<? extends Block>[] blocks) {
        return register.register(name, () -> {
            Block[] blockArray = new Block[blocks.length];
            for (int i = 0; i < blocks.length; i++) {
                blockArray[i] = blocks[i].get();
            }
            return BlockEntityType.Builder.of(factory, blockArray).build(null);
        });
    }

    // ==========================================
    // Методы с глобальным реестром (без параметра)
    // ==========================================

    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerEntities(
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            Supplier<? extends Block> block) {
        return registerEntities(BLOCK_ENTITIES, name, factory, block);
    }

    @SafeVarargs
    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerEntities(
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            Supplier<? extends Block>... blocks) {
        return registerEntities(BLOCK_ENTITIES, name, factory, blocks);
    }

    // ==========================================
    // Методы с внешним реестром (с параметром)
    // ==========================================

    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerEntities(
            DeferredRegister<BlockEntityType<?>> register,
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            Supplier<? extends Block> block) {
        @SuppressWarnings("unchecked")
        Supplier<? extends Block>[] blocks = new Supplier[]{block};
        return register(register, name, factory, blocks);
    }

    @SafeVarargs
    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerEntities(
            DeferredRegister<BlockEntityType<?>> register,
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            Supplier<? extends Block>... blocks) {
        return register(register, name, factory, blocks);
    }
}
