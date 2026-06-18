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

public class IItemsUtils {

    // Глобальный реестр для BlockItem и общих предметов (привязан к industrialtech_crafting)
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Industrialtech_crafting.MOD_ID);

    // ==========================================
    // Вспомогательный метод регистрации
    // ==========================================

    private static <T extends Item> RegistryObject<T> register(DeferredRegister<Item> items, String name, Supplier<T> supplier) {
        return items.register(name, supplier);
    }

    // ==========================================
    // Методы для глобального реестра (без параметра)
    // ==========================================

    public static RegistryObject<Item> item(String name) {
        return item(ITEMS, name);
    }

    public static RegistryObject<Item> itemRare(String name) {
        return itemRare(ITEMS, name);
    }

    public static RegistryObject<Item> itemEpic(String name) {
        return itemEpic(ITEMS, name);
    }

    public static RegistryObject<Item> itemLegendary(String name) {
        return itemLegendary(ITEMS, name);
    }

    public static RegistryObject<Item> itemMythic(String name) {
        return itemMythic(ITEMS, name);
    }

    public static RegistryObject<Item> material(String name) {
        return material(ITEMS, name);
    }

    public static RegistryObject<Item> materialRare(String name) {
        return materialRare(ITEMS, name);
    }

    public static RegistryObject<Item> materialEpic(String name) {
        return materialEpic(ITEMS, name);
    }

    public static RegistryObject<Item> materialLegendary(String name) {
        return materialLegendary(ITEMS, name);
    }

    public static RegistryObject<Item> materialMythic(String name) {
        return materialMythic(ITEMS, name);
    }

    public static RegistryObject<Item> food(String name, FoodProperties food) {
        return food(ITEMS, name, food);
    }

    public static RegistryObject<Item> food(String name, int nutrition, float saturation) {
        return food(ITEMS, name, nutrition, saturation);
    }

    public static RegistryObject<Item> food(String name, int nutrition, float saturation, boolean isMeat) {
        return food(ITEMS, name, nutrition, saturation, isMeat);
    }

    // ==========================================
    // Методы для внешнего реестра (с параметром DeferredRegister<Item>)
    // ==========================================

    public static RegistryObject<Item> item(DeferredRegister<Item> items, String name) {
        return register(items, name, () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    }

    public static RegistryObject<Item> itemBlock(DeferredRegister<Item> items, String name) {
        return register(items, name, () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    }

    public static RegistryObject<Item> customBlockRarity(DeferredRegister<Item> items, String name, Rarity rarity) {
        return register(items, name, () -> new Item(new Item.Properties().rarity(rarity)));
    }

    public static RegistryObject<Item> itemRare(DeferredRegister<Item> items, String name) {
        return register(items, name, () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    }

    public static RegistryObject<Item> itemEpic(DeferredRegister<Item> items, String name) {
        return register(items, name, () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    }

    public static RegistryObject<Item> itemLegendary(DeferredRegister<Item> items, String name) {
        return register(items, name, () -> new Item(new Item.Properties().rarity(ModRarity.LEGENDARY)));
    }

    public static RegistryObject<Item> itemMythic(DeferredRegister<Item> items, String name) {
        return register(items, name, () -> new Item(new Item.Properties().rarity(ModRarity.MYTHIC)));
    }

    public static RegistryObject<Item> material(DeferredRegister<Item> items, String name) {
        return item(items, name);
    }

    public static RegistryObject<Item> materialRare(DeferredRegister<Item> items, String name) {
        return itemRare(items, name);
    }

    public static RegistryObject<Item> materialEpic(DeferredRegister<Item> items, String name) {
        return itemEpic(items, name);
    }

    public static RegistryObject<Item> materialLegendary(DeferredRegister<Item> items, String name) {
        return itemLegendary(items, name);
    }

    public static RegistryObject<Item> materialMythic(DeferredRegister<Item> items, String name) {
        return itemMythic(items, name);
    }

    public static RegistryObject<Item> food(DeferredRegister<Item> items, String name, FoodProperties food) {
        return register(items, name, () -> new Item(new Item.Properties().food(food)));
    }

    public static RegistryObject<Item> food(DeferredRegister<Item> items, String name, int nutrition, float saturation) {
        FoodProperties properties = new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationMod(saturation)
                .build();
        return food(items, name, properties);
    }

    public static RegistryObject<Item> food(DeferredRegister<Item> items, String name, int nutrition, float saturation, boolean isMeat) {
        FoodProperties.Builder builder = new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationMod(saturation);
        if (isMeat) builder.meat();
        return food(items, name, builder.build());
    }
}