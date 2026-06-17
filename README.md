# IndustrialTech: Crafting

[![Minecraft](https://img.shields.io/badge/minecraft-1.20.1-blue.svg)](https://www.minecraft.net/)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)
[![Forge](https://img.shields.io/badge/forge-47.4.10-orange.svg)](https://forge.gemwire.uk/)

Дополнение для мода **IndustrialTech**, добавляющее возможность создавать большие крафты (shaped crafting recipes) и расширяющее систему крафта в моде.

## 📋 Описание

IndustrialTech: Crafting — это модификация для Minecraft, которая расширяет возможности крафта в сеттинге IndustrialTech. Мод добавляет новые блоки-инготы и предметы, а также предоставляет удобный API для создания технических блоков и предметов.

## 🔧 Технические детали

- **Версия Minecraft**: 1.20.1
- **Minecraft Forge**: 47.4.10
- **Mappings**: ParchmentMC 2023.09.03
- **Язык программирования**: Java 17
- **Лицензия**: MIT

## 📦 Зависимости

### Обязательные
- Minecraft Forge 47.4.10

### Необязательные (runtimeOnly)
- Applied Energistics 2
- Cucumber (Blakebr0)
- EMI (EMI)
- CraftTweaker
- Botarium
- Sodium/Embeddium и совместимые моды оптимизации

### Разработка
Для разработки используется:
- ParchmentMC mappings
- Mixin (через Mixingradle)

## 🚀 Сборка

```bash
./gradlew build
```

Собранный мод будет находиться в директории `build/libs/`.

## 💻 API

Мод предоставляет удобный API для создания блоков и предметов:

### Инициализация

```java
// В главном классе мода
public class Industrialtech_crafting {
    public Industrialtech_crafting() {
        ModAPI.initBlocks(BLOCKS_REGISTRY);
        ModAPI.initItems(ITEMS_REGISTRY);
        ModAPI.initBlockEntities(BLOCK_ENTITIES_REGISTRY);
    }
}
```

### Создание блоков

```java
import static org.mod.industrialtech_crafting.api.ModAPI.*;

// Базовые блоки
public static final RegistryObject<Block> MY_BLOCK = simple("my_block");
public static final RegistryObject<Block> MY_BLOCK_CUSTOM = simple("my_block_custom", Blocks.IRON_BLOCK);

// Блоки-руды
public static final RegistryObject<Block> MY_ORE = ore("my_ore");
public static final RegistryObject<Block> MY_NETHER_ORE = netherOre("my_nether_ore");
public static final RegistryObject<Block> MY_END_ORE = endOre("my_end_ore");

// Технические блоки
public static final RegistryObject<Block> MY_MACHINE = machine("my_machine");
public static final RegistryObject<Block> MY_HEAVY_MACHINE = heavyMachine("my_heavy_machine");
```

### Использование Builder API

```java
// Создание через builder
public static final RegistryObject<Block> CUSTOM_BLOCK = block("custom_block")
    .sound(SoundType.METAL)
    .hardness(5.0F)
    .resistance(10.0F)
    .requiresTool()
    .build();

// Создание инготов
public static final RegistryObject<Item> CUSTOM_INGOT = ingotItem("custom_ingot")
    .maxStackSize(64)
    .rarity(Rarity.UNCOMMON)
    .build();
```

### Редкость предметов

Мод добавляет кастомные уровни редкости:
- **LEGENDARY** (оранжевый цвет) — `#FF6600`
- **MYTHIC** (красный цвет) — `#FF0000`

```java
import static org.mod.industrialtech_crafting.rarity.ModRarity.*;

// Использование
public static final RegistryObject<Item> LEGENDARY_ITEM = register("legendary_item", 
    () -> new Item(new Item.Properties().rarity(LEGENDARY)));
```

## 📁 Структура проекта

```
src/
├── main/
│   ├── java/
│   │   └── org/mod/industrialtech_crafting/
│   │       ├── Industrialtech_crafting.java    # Главный класс мода
│   │       ├── Config.java                     # Конфигурация
│   │       ├── api/                           # API мода
│   │       │   ├── ModAPI.java                # Фасад API
│   │       │   ├── IItemBuilder.java          # Интерфейс builder предметов
│   │       │   ├── IBlockBuilder.java         # Интерфейс builder блоков
│   │       │   ├── item/
│   │       │   │   └── ItemBuilderImpl.java   # Реализация builder предметов
│   │       │   └── block/
│   │       │       ├── BlockBuilderImpl.java  # Реализация builder блоков
│   │       │       └── MachineBlockFactory.java # Фабрика технических блоков
│   │       ├── init/
│   │       │   ├── ModBlocks.java             # Регистрация блоков
│   │       │   ├── ModBlockEntities.java      # Регистрация BlockEntity
│   │       │   └── iface/
│   │       │       ├── IBlockUtils.java       # Утилиты для блоков
│   │       │       ├── IItemsUtils.java      # Утилиты для предметов
│   │       │       └── IBlockEntityUtils.java # Утилиты для BlockEntity
│   │       └── rarity/
│   │           └── ModRarity.java             # Кастомные уровни редкости
│   └── resources/
│       ├── META-INF/
│       │   └── mods.toml                      # Метаданные мода
│       └── pack.mcmeta
```

## 🛠️ Текущие блоки

В мод входят следующие блоки:

| Блок | Описание | Редкость |
|------|----------|----------|
| luminessence_block | Базовый энергетический блок | Обычная |
| black_iron_block | Чёрная железа | Обычная |
| redstone_ingot_block | Блок редстоун ингота | Обычная |
| enhanced_redstone_ingot_block | Улучшенный блок редстоун ингота | Редкая |
| ender_ingot_block | Блок эндер ингота | Обычная |
| enhanced_ender_ingot_block | Улучшенный блок эндер ингота | Редкая |
| crystaltine_block | Кристаллин блок | Редкая |
| nether_star_block | Блок незер звезды | Редкая |
| flux_star_block | Блок flux звезды | Редкая |
| ender_star_block | Блок эндер звезды | Редкая |

*Примечание: некоторые блоки (таблицы крафта, автокрафтеры, компрессоры) закомментированы и находятся в разработке.*

## 🤝 Совместимость

Мод разработан для работы с:
- **Applied Energistics 2** — интеграция с системой ME сетей
- **Cucumber** — библиотека для упрощения разработки
- **CraftTweaker** — возможность кастомизации крафтов
- **EMI** — интеграция с современным UI рецептов

## 📝 Лицензия

Проект распространяется под лицензией MIT. Подробнее см. в файле `LICENSE`.

## 👤 Авторы

- **alyoo_sha** — основной разработчик

## 🔄 Вклад в проект

Если вы хотите внести свой вклад в разработку:

1. Сделайте форк репозитория
2. Создайте ветку для ваших изменений (`git checkout -b feature/amazing-feature`)
3. Зафиксируйте изменения (`git commit -m 'Add amazing feature'`)
4. Отправьте ветку (`git push origin feature/amazing-feature`)
5. Откройте Pull Request

---

> ⚠️ **Примечание**: Мод находится в активной разработке. Некоторые функции могут быть недоступны или изменяться в будущих версиях.