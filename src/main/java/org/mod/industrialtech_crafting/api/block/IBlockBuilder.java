package org.mod.industrialtech_crafting.api.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.registries.RegistryObject;

/**
 * Builder для создания блоков с fluent API.
 */
public interface IBlockBuilder {
    
    /**
     * Устанавливает базовый блок для копирования свойств.
     */
    IBlockBuilder base(Block block);

    /**
     * Устанавливает звук блока.
     */
    IBlockBuilder sound(SoundType sound);

    /**
     * Устанавливает твёрдость блока.
     */
    IBlockBuilder hardness(float hardness);

    /**
     * Устанавливает сопротивление блока.
     */
    IBlockBuilder resistance(float resistance);

    /**
     * Требует правильный инструмент для добычи.
     */
    IBlockBuilder requiresTool();

    /**
     * Без таблицы лута.
     */
    IBlockBuilder noLootTable();
    /**
     * Устанавливает размер стопки.
     */
    IBlockBuilder maxStackSize(int size);

    /**
     * Собирает и регистрирует блок.
     */
    RegistryObject<Block> build();
}
