package org.mod.industrialtech_crafting.init.iface;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mod.industrialtech_crafting.Industrialtech_crafting;
import org.mod.industrialtech_crafting.rarity.ModRarity;

import java.util.function.Supplier;

public interface IItemsUtils {
    DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Industrialtech_crafting.MOD_ID);

    static RegistryObject<Item> item(String name) {
        return registerItem(name, () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    }

    static RegistryObject<Item> itemRare(String name) {
        return registerItem(name, () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    }

    static RegistryObject<Item> itemEpic(String name) {
        return registerItem(name, () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    }

    static RegistryObject<Item> itemLegendary(String name) {
        return registerItem(name, () -> new Item(new Item.Properties().rarity(ModRarity.LEGENDARY)));
    }

    static RegistryObject<Item> itemMythic(String name) {
        return registerItem(name, () -> new Item(new Item.Properties().rarity(ModRarity.MYTHIC)));
    }

    static RegistryObject<Item> material(String name) {
        return item(name);
    }

    static RegistryObject<Item> materialRare(String name) {
        return itemRare(name);
    }

    static RegistryObject<Item> materialEpic(String name) {
        return itemEpic(name);
    }

    static RegistryObject<Item> materialLegendary(String name) {
        return itemLegendary(name);
    }

    static RegistryObject<Item> materialMythic(String name) {
        return itemMythic(name);
    }

    static RegistryObject<Item> food(String name, FoodProperties food) {
        return registerItem(name, () -> new Item(new Item.Properties().food(food)));
    }

    static RegistryObject<Item> food(String name, int nutrition, float saturation) {
        FoodProperties properties = new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationMod(saturation)
                .build();
        return food(name, properties);
    }

    static RegistryObject<Item> food(String name, int nutrition, float saturation, boolean isMeat) {
        FoodProperties.Builder builder = new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationMod(saturation);
        if (isMeat) builder.meat();
        return food(name, builder.build());
    }

    static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> supplier) {
        return IItemsUtils.ITEMS.register(name, supplier);
    }
}
