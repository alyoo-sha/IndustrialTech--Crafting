package org.mod.industrialtech_crafting.api.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.RegistryObject;
import org.mod.industrialtech_crafting.init.iface.IItemsUtils;
import org.mod.industrialtech_crafting.rarity.ModRarity;

/**
 * Реализация Builder для создания предметов.
 */
public class ItemBuilderImpl implements IItemBuilder {
    private final String name;
    private int maxStackSize = 64;
    private Rarity rarity = Rarity.COMMON;
    private FoodProperties foodProperties = null;

    private ItemBuilderImpl(String name) {
        this.name = name;
    }

    /**
     * Создаёт новый builder для предмета.
     */
    public static ItemBuilderImpl create(String name) {
        return new ItemBuilderImpl(name);
    }

    /**
     * Пресет: обычный ингредиент.
     */
    public static ItemBuilderImpl ingot(String name) {
        return create(name).maxStackSize(64);
    }

    /**
     * Пресет: редкий ингредиент.
     */
    public static ItemBuilderImpl ingotRare(String name) {
        return create(name).maxStackSize(64).rarity(Rarity.RARE);
    }

    /**
     * Пресет: эпический ингредиент.
     */
    public static ItemBuilderImpl itemRare(String name) {
        return create(name).maxStackSize(64).rarity(Rarity.RARE);
    }
    public static ItemBuilderImpl ingotEpic(String name) {
        return create(name).maxStackSize(64).rarity(Rarity.EPIC);
    }
    public static ItemBuilderImpl ingotLegendary(String name) {
        return create(name).maxStackSize(64).rarity(ModRarity.LEGENDARY);
    }
    public static ItemBuilderImpl ingotMythic(String name) {
        return create(name).maxStackSize(64).rarity(ModRarity.LEGENDARY);
    }

    @Override
    public ItemBuilderImpl maxStackSize(int size) {
        this.maxStackSize = size;
        return this;
    }

    @Override
    public ItemBuilderImpl rarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }


    @Override
    public ItemBuilderImpl food(FoodProperties food) {
        this.foodProperties = food;
        return this;
    }

    @Override
    public RegistryObject<Item> build() {
        // Создаём final переменную для использования в лямбде
        final int stackSize = this.maxStackSize;
        final Rarity itemRarity = this.rarity;
        final FoodProperties food = this.foodProperties;
        
        return IItemsUtils.ITEMS.register(name, () -> {
            Item.Properties props = new Item.Properties().stacksTo(stackSize);
            if (itemRarity != Rarity.COMMON) props.rarity(itemRarity);
            if (food != null) props.food(food);
            return new Item(props);
        });
    }
}
