package com.split.extraons;

import com.split.extraons.blocks.*;
import com.split.extraons.extractor.*;
import com.split.extraons.items.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.recipe.*;
import net.minecraft.util.*;
import com.split.extraons.gamingchair.GamingChairSitEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
    public static final String MOD_ID = "extraons";


    public static final EntityType<GamingChairSitEntity> SIT_ENTITY_TYPE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("extraons", "gaming_sit"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, GamingChairSitEntity::new).size(EntityDimensions.fixed(0F, 0.3F)).build()
    );

    //items
    public static final Item HDMI_CABLE = new HDMICableItem(new Item.Settings().group(Main.MAIN_GROUP).maxCount(16));
    public static final Item MIC_CABLE = new Item(new Item.Settings().group(Main.MAIN_GROUP).maxCount(16));
    public static final Item COVERED_SCREEN = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item PLASTIC = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item COMPRESSED_PLASTIC = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item SCREEN = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item TV_BASE = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item WHEEL = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item SEAT = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item CHAIR_BACK = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item CONNECTOR_ROD = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item RUBBER = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item EXPOSED_WIRES = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item COVERED_WIRES = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item HDMI_SOCKET = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final Item MIC_SOCKET = new Item(new Item.Settings().group(Main.MAIN_GROUP));
    public static final DyingKitItem YELLOW_DYING_KIT = new DyingKitItem(new Item.Settings().group(Main.MAIN_GROUP).maxCount(1), DyeColor.YELLOW);
    public static final DyingKitItem ORANGE_DYING_KIT = new DyingKitItem(new Item.Settings().group(Main.MAIN_GROUP).maxCount(1), DyeColor.ORANGE);
    public static final DyingKitItem RED_DYING_KIT = new DyingKitItem(new Item.Settings().group(Main.MAIN_GROUP).maxCount(1), DyeColor.RED);

    //Extractor Stuff
    public static final BlockEntityType EXTRACTOR_BLOCK_ENTITY;
    public static final RecipeType<ExtractorRecipe> EXTRACTOR_RECIPE_TYPE;
    public static final RecipeSerializer<ExtractorRecipe> EXTRACTOR_RECIPE_SERIALIZER;
    public static final ScreenHandlerType<ExtractorScreenHandler> EXTRACTOR_SCREEN_HANDLER;

    //blocks
    public static final TVBlock TV = new TVBlock(Block.Settings.of(Material.STONE).nonOpaque());
    public static final ExtractorBlock EXTRACTOR_BLOCK = new ExtractorBlock(Block.Settings.of(Material.STONE));
    public static final GamingChairBlock PLAIN_GAMING_CHAIR_BLOCK = new GamingChairBlock(Block.Settings.of(Material.STONE).nonOpaque());
    public static final MonitorBlock MONITOR = new MonitorBlock(Block.Settings.of(Material.STONE).nonOpaque());


    //Consoles
    public static final ConsoleBlock CONSOLE_CREEPER = new ConsoleBlock(Block.Settings.of(Material.STONE).nonOpaque());
    public static final ConsoleBlock CONSOLE_PIG = new ConsoleBlock(Block.Settings.of(Material.STONE).nonOpaque());
    public static final ConsoleBlock CONSOLE_COW = new ConsoleBlock(Block.Settings.of(Material.STONE).nonOpaque());

    //Sounds
    public static final Identifier CONSOLE_STARTUP_ID = new Identifier("extraons:console_startup");
    public static SoundEvent CONSOLE_STARTUP_EVENT = new SoundEvent(CONSOLE_STARTUP_ID);

    public static final Identifier TV_CLICK_ID = new Identifier("extraons:tv_click");
    public static SoundEvent TV_CLICK_EVENT = new SoundEvent(TV_CLICK_ID);

    public static final Identifier PAINT_SPLASH_ID = new Identifier("extraons:paint_splash");
    public static SoundEvent PAINT_SPLASH_EVENT = new SoundEvent(PAINT_SPLASH_ID);

    @Override
    public void onInitialize() {
        //Items
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "hdmi_cable"), HDMI_CABLE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mic_cable"), MIC_CABLE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "covered_screen"), COVERED_SCREEN);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "plastic"), PLASTIC);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "compressed_plastic"), COMPRESSED_PLASTIC);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "screen"), SCREEN);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tv_base"), TV_BASE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "wheel"), WHEEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "seat"), SEAT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "back"), CHAIR_BACK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "connector_rod"), CONNECTOR_ROD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "rubber"), RUBBER);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "exposed_wires"), EXPOSED_WIRES);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "covered_wires"), COVERED_WIRES);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "hdmi_socket"), HDMI_SOCKET);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mic_socket"), MIC_SOCKET);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yellow_dying_kit"), YELLOW_DYING_KIT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "orange_dying_kit"), ORANGE_DYING_KIT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_dying_kit"), RED_DYING_KIT);

        //Blocks
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "tv"), TV);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plain_gaming_chair"), PLAIN_GAMING_CHAIR_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "extractor"), EXTRACTOR_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "console_creeper"), CONSOLE_CREEPER);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "console_pig"), CONSOLE_PIG);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "console_cow"), CONSOLE_COW);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "monitor"), MONITOR);

        //Block Items
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tv"), new BlockItem(TV, new FabricItemSettings().group(Main.MAIN_GROUP).maxCount(1)));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "extractor"), new BlockItem(EXTRACTOR_BLOCK, new FabricItemSettings().group(Main.MAIN_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "plain_gaming_chair"), new BlockItem(PLAIN_GAMING_CHAIR_BLOCK, new FabricItemSettings().group(Main.MAIN_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "console_creeper"), new BlockItem(CONSOLE_CREEPER, new FabricItemSettings().group(Main.MAIN_GROUP).maxCount(1)));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "console_cow"), new BlockItem(CONSOLE_COW, new FabricItemSettings().group(Main.MAIN_GROUP).maxCount(1)));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "console_pig"), new BlockItem(CONSOLE_PIG, new FabricItemSettings().group(Main.MAIN_GROUP).maxCount(1)));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "monitor"), new BlockItem(MONITOR, new FabricItemSettings().group(Main.MAIN_GROUP).maxCount(1)));

        //Sounds
        Registry.register(Registry.SOUND_EVENT, Main.CONSOLE_STARTUP_ID, CONSOLE_STARTUP_EVENT);
        Registry.register(Registry.SOUND_EVENT, Main.TV_CLICK_ID, TV_CLICK_EVENT);
        Registry.register(Registry.SOUND_EVENT, Main.PAINT_SPLASH_ID, PAINT_SPLASH_EVENT);
    }


    static {
        //BlockEntity
        EXTRACTOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "extractor"), BlockEntityType.Builder.create(ExtractorBlockEntity::new, EXTRACTOR_BLOCK).build(null));

        EXTRACTOR_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, "extractor"), new RecipeType<ExtractorRecipe>() {
            @Override
            public String toString() {return "extractor";}
        });

        EXTRACTOR_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MOD_ID, "extractor"), new CookingRecipeSerializer<>(ExtractorRecipe::new, 200));

        EXTRACTOR_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MOD_ID, "extractor"), ExtractorScreenHandler::new);
    }

    public static final ItemGroup MAIN_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "main"))
            .icon(() -> new ItemStack(Main.HDMI_CABLE))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(Main.EXTRACTOR_BLOCK));
                stacks.add(new ItemStack(Main.EXPOSED_WIRES));
                stacks.add(new ItemStack(Main.COVERED_WIRES));
                stacks.add(new ItemStack(Main.HDMI_CABLE));
                stacks.add(new ItemStack(Main.HDMI_SOCKET));
                stacks.add(new ItemStack(Main.MIC_CABLE));
                stacks.add(new ItemStack(Main.MIC_SOCKET));
                stacks.add(new ItemStack(Main.RUBBER));
                stacks.add(new ItemStack(Main.PLASTIC));
                stacks.add(new ItemStack(Main.COMPRESSED_PLASTIC));
                stacks.add(new ItemStack(Main.SCREEN));
                stacks.add(new ItemStack(Main.COVERED_SCREEN));
                stacks.add(new ItemStack(Main.TV_BASE));
                stacks.add(new ItemStack(Main.TV));
                stacks.add(new ItemStack(Main.WHEEL));
                stacks.add(new ItemStack(Main.CONNECTOR_ROD));
                stacks.add(new ItemStack(Main.SEAT));
                stacks.add(new ItemStack(Main.CHAIR_BACK));
                stacks.add(new ItemStack(Main.PLAIN_GAMING_CHAIR_BLOCK));
                stacks.add(new ItemStack(Main.CONSOLE_CREEPER));
                stacks.add(new ItemStack(Main.CONSOLE_PIG));
                stacks.add(new ItemStack(Main.CONSOLE_COW));
                stacks.add(new ItemStack(Main.MONITOR));
                stacks.add(new ItemStack(Main.YELLOW_DYING_KIT));
                stacks.add(new ItemStack(Main.ORANGE_DYING_KIT));
                stacks.add(new ItemStack(Main.RED_DYING_KIT));
            })
            .build();
}