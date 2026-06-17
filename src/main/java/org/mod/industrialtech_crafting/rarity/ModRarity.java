package org.mod.industrialtech_crafting.rarity;

import net.minecraft.world.item.Rarity;

public class ModRarity {
    public static Rarity LEGENDARY;
    public static Rarity MYTHIC;

    public static void init() {
        LEGENDARY = Rarity.create("Legendary",
                style -> style.withColor(0xFF6600));
        MYTHIC = Rarity.create("Mythic",
                style -> style.withColor(0xFF0000));
    };
}
