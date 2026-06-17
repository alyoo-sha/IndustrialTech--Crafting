package org.mod.industrialtech_crafting.init.iface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mod.industrialtech_crafting.Industrialtech_crafting;

import java.util.function.Supplier;

public interface IBlockEntityUtils {
    DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Industrialtech_crafting.MOD_ID);

    /**
     * Регистрация BlockEntityType для одного связанного блока
     * @param name имя (registry name)
     * @param factory фабрика, создающая экземпляр BlockEntity (например, GeneratorBlockEntity::new)
     * @param block связанный блок
     * @return RegistryObject<BlockEntityType<T>>
     */
    static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerEntities(
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            Supplier<? extends Block> block
    ) {
        return BLOCK_ENTITIES.register(name, () -> BlockEntityType.Builder.of(factory, block.get()).build(null));
    };
    /**
     * Регистрация BlockEntityType для нескольких связанных блоков (если один тип тайла используется на нескольких блоках).
     */
    @SafeVarargs
    static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerEntities(
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            Supplier<? extends Block>... blocks
    ) {
        return BLOCK_ENTITIES.register(name, () -> {
            Block[] blockArray = new Block[blocks.length];
            for (int i = 0; i < blocks.length; i++) {
                blockArray[i] = blocks[i].get();
            }
            return BlockEntityType.Builder.of(factory, blockArray).build(null);
        });
    }
}
