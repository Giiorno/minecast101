package com.example.examplemod;

import com.example.examplemod.combat.effects.CombatTrapEvents;
import com.example.examplemod.items.NoviceWandItem;
import com.example.examplemod.spells.SpellSchool;
import com.example.examplemod.spells.utility.BladeSpell;
import com.example.examplemod.spells.utility.TrapSpell;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.example.examplemod.pips.PipEvents;
import com.example.examplemod.commands.CommandEvents;
import com.example.examplemod.items.SpellCardItem;
import com.example.examplemod.spells.fire.FirecatSpell;
import com.example.examplemod.spells.balance.ScarabSpell;
import com.example.examplemod.combat.DamageNumberEvents;
import com.example.examplemod.combat.effects.CombatEffectEvents;
//SpellBlades imports

import com.example.examplemod.spells.utility.ClearBladesSpell;
//Bis hier hin die SpellBlade imports

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Minecast101.MODID)
public class Minecast101 {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "minecast101";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "minecast101" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "minecast101" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "minecast101" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new Block with the id "minecast101:example_block", combining the namespace and path
    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", p -> p.mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "minecast101:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);

    // Creates a new food item with the id "minecast101:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> NOVICE_WAND =
            ITEMS.registerItem(
                    "novice_wand",
                    NoviceWandItem::new
            );
    public static final DeferredItem<Item> FIRECAT_CARD =
            ITEMS.registerItem(
                    "firecat_card",
                    properties -> new SpellCardItem(
                            properties,
                            FirecatSpell::new
                    )
            );

    public static final DeferredItem<Item> SCARAB_CARD =
            ITEMS.registerItem(
                    "scarab_card",
                    properties -> new SpellCardItem(
                            properties,
                            ScarabSpell::new
                    )
            );
    public static final DeferredItem<Item> FIRE_BLADE_CARD =
            ITEMS.registerItem(
                    "fire_blade_card",
                    properties -> new SpellCardItem(
                            properties,
                            () -> new BladeSpell("Fire Blade", SpellSchool.FIRE, 1.35f, false, "fire_blade")
                    )
            );

    public static final DeferredItem<Item> ENHANCED_FIRE_BLADE_CARD =
            ITEMS.registerItem(
                    "enhanced_fire_blade_card",
                    properties -> new SpellCardItem(
                            properties,
                            () -> new BladeSpell("Enhanced Fire Blade", SpellSchool.FIRE, 1.45f, false, "fire_blade_enhanced")
                    )
            );

    public static final DeferredItem<Item> BALANCE_BLADE_CARD =
            ITEMS.registerItem(
                    "balance_blade_card",
                    properties -> new SpellCardItem(
                            properties,
                            () -> new BladeSpell("Balance Blade", SpellSchool.BALANCE, 1.25f, true, "balance_blade")
                    )
            );

    public static final DeferredItem<Item> ENHANCED_BALANCE_BLADE_CARD =
            ITEMS.registerItem(
                    "enhanced_balance_blade_card",
                    properties -> new SpellCardItem(
                            properties,
                            () -> new BladeSpell("Enhanced Balance Blade", SpellSchool.BALANCE, 1.35f, true, "balance_blade_enhanced")
                    )
            );

    public static final DeferredItem<Item> CLEAR_BLADES_CARD =
            ITEMS.registerItem(
                    "clear_blades_card",
                    properties -> new SpellCardItem(
                            properties,
                            ClearBladesSpell::new
                    )
            );

    public static final DeferredItem<Item> FIRE_TRAP_CARD =
            ITEMS.registerItem(
                    "fire_trap_card",
                    properties -> new SpellCardItem(
                            properties,
                            () -> new TrapSpell("Fire Trap", SpellSchool.FIRE, 1.40f, false, "fire_trap")
                    )
            );

    public static final DeferredItem<Item> BALANCE_TRAP_CARD =
            ITEMS.registerItem(
                    "balance_trap_card",
                    properties -> new SpellCardItem(
                            properties,
                            () -> new TrapSpell("Balance Trap", SpellSchool.BALANCE, 1.30f, true, "balance_trap")
                    )
            );

    // Creates a creative tab with the id "minecast101:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.minecast101")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> NOVICE_WAND.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                //Rüstung und so
                output.accept(NOVICE_WAND.get());
                //1 Pip Spells
                output.accept(FIRECAT_CARD.get());
                output.accept(SCARAB_CARD.get());
                //Blades
                output.accept(FIRE_BLADE_CARD.get());
                output.accept(ENHANCED_FIRE_BLADE_CARD.get());

                output.accept(BALANCE_BLADE_CARD.get());
                output.accept(ENHANCED_BALANCE_BLADE_CARD.get());

                output.accept(CLEAR_BLADES_CARD.get());
                //Traps
                output.accept(FIRE_TRAP_CARD.get());
                output.accept(BALANCE_TRAP_CARD.get());

                // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Minecast101(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Minecast101) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(PipEvents.class);
        NeoForge.EVENT_BUS.register(CommandEvents.class);
        NeoForge.EVENT_BUS.register(DamageNumberEvents.class);
        NeoForge.EVENT_BUS.register(CombatEffectEvents.class);
        NeoForge.EVENT_BUS.register(CombatTrapEvents.class);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(EXAMPLE_BLOCK_ITEM);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
