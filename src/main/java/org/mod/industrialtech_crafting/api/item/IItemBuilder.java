package org.mod.industrialtech_crafting.api.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.RegistryObject;

/**
 * Builder для создания предметов с fluent API.
 */
public interface IItemBuilder {
    
    /**
     * Устанавливает размер стопки.
     */
    IItemBuilder maxStackSize(int size);

    /**
     * Устанавливает редкость предмета.
     */
    IItemBuilder rarity(Rarity rarity);

    /**
     * Устанавливает food свойства.
     */
    IItemBuilder food(FoodProperties food);

    /**
     * Собирает и регистрирует предмет.
     */
    RegistryObject<Item> build();
}
