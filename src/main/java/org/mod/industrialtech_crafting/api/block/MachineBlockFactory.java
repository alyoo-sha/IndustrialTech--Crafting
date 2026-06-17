package org.mod.industrialtech_crafting.api.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * Фабрика для создания технических блоков с GUI.
 * Используется для блоков, требующих интерфейса (столы, компрессоры, крафтеры).
 * 
 * Требует Cucumber и Extended Crafting библиотеки.
 */
public class MachineBlockFactory {

    /**
     * Создаёт технический блок с интерфейсом.
     * 
     * @param name имя блока
     * @param blockSupplier поставщик блока
     * @return зарегистрированный блок
     */
    public static RegistryObject<Block> createWithGui(String name, Supplier<? extends Block> blockSupplier) {
        return BlockBuilderImpl.create(name)
                .sound(SoundType.METAL)
                .hardness(5.0F)
                .resistance(10.0F)
                .requiresTool()
                .customBlock(blockSupplier)
                .build();
    }

    /**
     * Создаёт крафтовый стол базового уровня.
     */
    public static RegistryObject<Block> craftingTable(String name) {
        return BlockBuilderImpl.machine(name).build();
    }

    /**
     * Создаёт автоматический крафтовый стол.
     */
    public static RegistryObject<Block> autoCraftingTable(String name) {
        return BlockBuilderImpl.heavyMachine(name).build();
    }
}
