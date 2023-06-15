package com.github.alexmodguy.alexscaves.server.misc;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import com.github.alexmodguy.alexscaves.server.item.CaveInfoItem;
import com.github.alexmodguy.alexscaves.server.item.CaveMapItem;
import com.github.alexmodguy.alexscaves.server.item.CustomTabBehavior;
import com.github.alexmodguy.alexscaves.server.level.biome.ACBiomeRegistry;
import com.github.alexmodguy.alexscaves.server.potion.ACEffectRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ACCreativeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> DEF_REG = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AlexsCaves.MODID);

    public static final RegistryObject<CreativeModeTab> MAGNETIC_CAVES = DEF_REG.register("magnetic_caves", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.alexscaves.magnetic_caves"))
            .icon(() -> new ItemStack(ACBlockRegistry.SCARLET_MAGNET.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .displayItems((enabledFeatures, output) -> {
                output.accept(CaveInfoItem.create(ACItemRegistry.CAVE_TABLET.get(), ACBiomeRegistry.MAGNETIC_CAVES));
                output.accept(CaveInfoItem.create(ACItemRegistry.CAVE_CODEX.get(), ACBiomeRegistry.MAGNETIC_CAVES));
                add(output, ACBlockRegistry.SPELUNKERY_TABLE.get());
                output.accept(CaveMapItem.createMap(ACBiomeRegistry.MAGNETIC_CAVES));
                ACItemRegistry.getSpawnEggsForTab(ACBiomeRegistry.MAGNETIC_CAVES).forEach((spawnEgg -> add(output, spawnEgg.get())));
                add(output, ACItemRegistry.RAW_SCARLET_NEODYMIUM.get());
                add(output, ACItemRegistry.SCARLET_NEODYMIUM_INGOT.get());
                add(output, ACItemRegistry.RAW_AZURE_NEODYMIUM.get());
                add(output, ACItemRegistry.AZURE_NEODYMIUM_INGOT.get());
                add(output, ACBlockRegistry.GALENA.get());
                add(output, ACBlockRegistry.GALENA_STAIRS.get());
                add(output, ACBlockRegistry.GALENA_SLAB.get());
                add(output, ACBlockRegistry.GALENA_BRICKS.get());
                add(output, ACBlockRegistry.GALENA_WALL.get());
                add(output, ACBlockRegistry.GALENA_BRICKS.get());
                add(output, ACBlockRegistry.GALENA_BRICK_STAIRS.get());
                add(output, ACBlockRegistry.GALENA_BRICK_SLAB.get());
                add(output, ACBlockRegistry.GALENA_BRICK_WALL.get());
                add(output, ACBlockRegistry.GALENA_IRON_ORE.get());
                add(output, ACBlockRegistry.ENERGIZED_GALENA_NEUTRAL.get());
                add(output, ACBlockRegistry.ENERGIZED_GALENA_SCARLET.get());
                add(output, ACBlockRegistry.ENERGIZED_GALENA_AZURE.get());
                add(output, ACBlockRegistry.GALENA_SPIRE.get());
                add(output, ACBlockRegistry.PACKED_GALENA.get());
                add(output, ACBlockRegistry.GALENA_PILLAR.get());
                add(output, ACBlockRegistry.TESLA_BULB.get());
                add(output, ACBlockRegistry.METAL_SWARF.get());
                add(output, ACBlockRegistry.SCRAP_METAL.get());
                add(output, ACBlockRegistry.SCRAP_METAL_PLATE.get());
                add(output, ACBlockRegistry.METAL_REBAR.get());
                add(output, ACBlockRegistry.METAL_SCAFFOLDING.get());
                add(output, ACBlockRegistry.METAL_BARREL.get());
                add(output, ACBlockRegistry.SCARLET_NEODYMIUM_NODE.get());
                add(output, ACBlockRegistry.SCARLET_NEODYMIUM_PILLAR.get());
                add(output, ACBlockRegistry.BLOCK_OF_SCARLET_NEODYMIUM.get());
                add(output, ACBlockRegistry.SCARLET_MAGNET.get());
                add(output, ACBlockRegistry.AZURE_NEODYMIUM_NODE.get());
                add(output, ACBlockRegistry.AZURE_NEODYMIUM_PILLAR.get());
                add(output, ACBlockRegistry.BLOCK_OF_AZURE_NEODYMIUM.get());
                add(output, ACBlockRegistry.AZURE_MAGNET.get());
                add(output, ACBlockRegistry.MAGNETIC_ACTIVATOR.get());
            })
            .build());

    public static final RegistryObject<CreativeModeTab> PRIMORDIAL_CAVES = DEF_REG.register("primordial_caves", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.alexscaves.primordial_caves"))
            .icon(() -> new ItemStack(ACBlockRegistry.FLYTRAP.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .withTabsBefore(MAGNETIC_CAVES.getKey())
            .displayItems((enabledFeatures, output) -> {
                output.accept(CaveInfoItem.create(ACItemRegistry.CAVE_TABLET.get(), ACBiomeRegistry.PRIMORDIAL_CAVES));
                output.accept(CaveInfoItem.create(ACItemRegistry.CAVE_CODEX.get(), ACBiomeRegistry.PRIMORDIAL_CAVES));
                add(output, ACBlockRegistry.SPELUNKERY_TABLE.get());
                output.accept(CaveMapItem.createMap(ACBiomeRegistry.PRIMORDIAL_CAVES));
                ACItemRegistry.getSpawnEggsForTab(ACBiomeRegistry.PRIMORDIAL_CAVES).forEach((spawnEgg -> add(output, spawnEgg.get())));
                add(output, ACBlockRegistry.AMBER.get());
                add(output, ACBlockRegistry.AMBERSOL.get());
                add(output, ACBlockRegistry.LIMESTONE.get());
                add(output, ACBlockRegistry.LIMESTONE_STAIRS.get());
                add(output, ACBlockRegistry.LIMESTONE_SLAB.get());
                add(output, ACBlockRegistry.LIMESTONE_WALL.get());
                add(output, ACBlockRegistry.SMOOTH_LIMESTONE.get());
                add(output, ACBlockRegistry.SMOOTH_LIMESTONE_STAIRS.get());
                add(output, ACBlockRegistry.SMOOTH_LIMESTONE_SLAB.get());
                add(output, ACBlockRegistry.SMOOTH_LIMESTONE_WALL.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_AMBERSOL.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_DARK.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_FOOTPRINT.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_FOOTPRINTS.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_TREE_STARS.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_PEWEN.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_TRILOCARIS.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_GROTTOCERATOPS.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_GROTTOCERATOPS_FRIEND.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_DINO_NUGGETS.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_VALLUMRAPTOR_CHEST.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_VALLUMRAPTOR_FRIEND.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_RELICHEIRUS.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_RELICHEIRUS_SLASH.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_ENDERMAN.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_PORTAL.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_SUBTERRANODON.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_SUBTERRANODON_RIDE.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_TREMORSAURUS.get());
                add(output, ACBlockRegistry.CAVE_PAINTING_TREMORSAURUS_FRIEND.get());
                add(output, ACBlockRegistry.PEWEN_LOG.get());
                add(output, ACBlockRegistry.PEWEN_BRANCH.get());
                add(output, ACBlockRegistry.PEWEN_SAPLING.get());
                add(output, ACBlockRegistry.PEWEN_PINES.get());
                add(output, ACBlockRegistry.PEWEN_WOOD.get());
                add(output, ACBlockRegistry.STRIPPED_PEWEN_LOG.get());
                add(output, ACBlockRegistry.STRIPPED_PEWEN_WOOD.get());
                add(output, ACBlockRegistry.PEWEN_PLANKS.get());
                add(output, ACBlockRegistry.PEWEN_PLANKS_STAIRS.get());
                add(output, ACBlockRegistry.PEWEN_PLANKS_SLAB.get());
                add(output, ACBlockRegistry.PEWEN_PLANKS_FENCE.get());
                add(output, ACItemRegistry.PEWEN_SIGN.get());
                add(output, ACItemRegistry.PEWEN_HANGING_SIGN.get());
                add(output, ACBlockRegistry.PEWEN_PRESSURE_PLATE.get());
                add(output, ACItemRegistry.PEWEN_DOOR.get());
                add(output, ACBlockRegistry.PEWEN_TRAPDOOR.get());
                add(output, ACBlockRegistry.PEWEN_BUTTON.get());
                add(output, ACBlockRegistry.PEWEN_FENCE_GATE.get());
                add(output, ACBlockRegistry.CURLY_FERN.get());
                add(output, ACBlockRegistry.FLYTRAP.get());
                add(output, ACBlockRegistry.CYCAD.get());
                add(output, ACBlockRegistry.ARCHAIC_VINE.get());
                add(output, ACBlockRegistry.ANCIENT_LEAVES.get());
                add(output, ACBlockRegistry.TREE_STAR.get());
            })
            .build());

    public static final RegistryObject<CreativeModeTab> TOXIC_CAVES = DEF_REG.register("toxic_caves", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.alexscaves.toxic_caves"))
            .icon(() -> new ItemStack(ACBlockRegistry.WASTE_DRUM.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .withTabsBefore(PRIMORDIAL_CAVES.getKey())
            .displayItems((enabledFeatures, output) -> {
                output.accept(CaveInfoItem.create(ACItemRegistry.CAVE_TABLET.get(), ACBiomeRegistry.TOXIC_CAVES));
                output.accept(CaveInfoItem.create(ACItemRegistry.CAVE_CODEX.get(), ACBiomeRegistry.TOXIC_CAVES));
                add(output, ACBlockRegistry.SPELUNKERY_TABLE.get());
                output.accept(CaveMapItem.createMap(ACBiomeRegistry.TOXIC_CAVES));
                ACItemRegistry.getSpawnEggsForTab(ACBiomeRegistry.TOXIC_CAVES).forEach((spawnEgg -> add(output, spawnEgg.get())));
                add(output, ACItemRegistry.ACID_BUCKET.get());
                add(output, ACItemRegistry.RADGILL_BUCKET.get());
                add(output, ACItemRegistry.RADON_BOTTLE.get());
                add(output, ACItemRegistry.CINDER_BRICK.get());
                add(output, ACBlockRegistry.RADROCK.get());
                add(output, ACBlockRegistry.RADROCK_STAIRS.get());
                add(output, ACBlockRegistry.RADROCK_SLAB.get());
                add(output, ACBlockRegistry.RADROCK_WALL.get());
                add(output, ACBlockRegistry.RADROCK_BRICKS.get());
                add(output, ACBlockRegistry.RADROCK_BRICK_STAIRS.get());
                add(output, ACBlockRegistry.RADROCK_BRICK_SLAB.get());
                add(output, ACBlockRegistry.RADROCK_BRICK_WALL.get());
                add(output, ACBlockRegistry.RADROCK_CHISELED.get());
                add(output, ACBlockRegistry.RADROCK_URANIUM_ORE.get());
                add(output, ACBlockRegistry.ACIDIC_RADROCK.get());
                add(output, ACBlockRegistry.GEOTHERMAL_VENT.get());
                add(output, ACBlockRegistry.GEOTHERMAL_VENT_MEDIUM.get());
                add(output, ACBlockRegistry.GEOTHERMAL_VENT_THIN.get());
                add(output, ACBlockRegistry.METAL_SWARF.get());
                add(output, ACBlockRegistry.SCRAP_METAL.get());
                add(output, ACBlockRegistry.RUSTY_SCRAP_METAL.get());
                add(output, ACBlockRegistry.SCRAP_METAL_PLATE.get());
                add(output, ACBlockRegistry.RUSTY_SCRAP_METAL_PLATE.get());
                add(output, ACBlockRegistry.METAL_REBAR.get());
                add(output, ACBlockRegistry.RUSTY_REBAR.get());
                add(output, ACBlockRegistry.METAL_SCAFFOLDING.get());
                add(output, ACBlockRegistry.RUSTY_SCAFFOLDING.get());
                add(output, ACBlockRegistry.METAL_BARREL.get());
                add(output, ACBlockRegistry.RUSTY_BARREL.get());
                add(output, ACBlockRegistry.WASTE_DRUM.get());
                add(output, ACBlockRegistry.UNDERWEED.get());
                add(output, ACBlockRegistry.URANIUM_ROD.get());
                add(output, ACBlockRegistry.BLOCK_OF_URANIUM.get());
                add(output, ACBlockRegistry.NUCLEAR_BOMB.get());
                add(output, ACBlockRegistry.SULFUR.get());
                add(output, ACBlockRegistry.SULFUR_BUD_SMALL.get());
                add(output, ACBlockRegistry.SULFUR_BUD_MEDIUM.get());
                add(output, ACBlockRegistry.SULFUR_BUD_LARGE.get());
                add(output, ACBlockRegistry.SULFUR_CLUSTER.get());
                add(output, ACBlockRegistry.CINDER_BLOCK.get());
                add(output, ACBlockRegistry.CINDER_BLOCK_STAIRS.get());
                add(output, ACBlockRegistry.CINDER_BLOCK_SLAB.get());
                add(output, ACBlockRegistry.CINDER_BLOCK_WALL.get());
                add(output, ACBlockRegistry.WHITE_RADON_LAMP.get());
                add(output, ACBlockRegistry.ORANGE_RADON_LAMP.get());
                add(output, ACBlockRegistry.MAGENTA_RADON_LAMP.get());
                add(output, ACBlockRegistry.LIGHT_BLUE_RADON_LAMP.get());
                add(output, ACBlockRegistry.YELLOW_RADON_LAMP.get());
                add(output, ACBlockRegistry.LIME_RADON_LAMP.get());
                add(output, ACBlockRegistry.PINK_RADON_LAMP.get());
                add(output, ACBlockRegistry.GRAY_RADON_LAMP.get());
                add(output, ACBlockRegistry.LIGHT_GRAY_RADON_LAMP.get());
                add(output, ACBlockRegistry.CYAN_RADON_LAMP.get());
                add(output, ACBlockRegistry.PURPLE_RADON_LAMP.get());
                add(output, ACBlockRegistry.BLUE_RADON_LAMP.get());
                add(output, ACBlockRegistry.BROWN_RADON_LAMP.get());
                add(output, ACBlockRegistry.GREEN_RADON_LAMP.get());
                add(output, ACBlockRegistry.RED_RADON_LAMP.get());
                add(output, ACBlockRegistry.BLACK_RADON_LAMP.get());
            })
            .build());

    public static final RegistryObject<CreativeModeTab> ABYSSAL_CHASM = DEF_REG.register("abyssal_chasm", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.alexscaves.abyssal_chasm"))
            .icon(() -> new ItemStack(ACItemRegistry.SUBMARINE.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .withTabsBefore(TOXIC_CAVES.getKey())
            .displayItems((enabledFeatures, output) -> {
                output.accept(CaveInfoItem.create(ACItemRegistry.CAVE_TABLET.get(), ACBiomeRegistry.ABYSSAL_CHASM));
                output.accept(CaveInfoItem.create(ACItemRegistry.CAVE_CODEX.get(), ACBiomeRegistry.ABYSSAL_CHASM));
                add(output, ACBlockRegistry.SPELUNKERY_TABLE.get());
                output.accept(CaveMapItem.createMap(ACBiomeRegistry.ABYSSAL_CHASM));
                ACItemRegistry.getSpawnEggsForTab(ACBiomeRegistry.ABYSSAL_CHASM).forEach((spawnEgg -> add(output, spawnEgg.get())));
                add(output, ACItemRegistry.SUBMARINE.get());
                add(output, ACItemRegistry.DIVING_HELMET.get());
                add(output, ACItemRegistry.DIVING_CHESTPLATE.get());
                add(output, ACItemRegistry.DIVING_LEGGINGS.get());
                add(output, ACItemRegistry.DIVING_BOOTS.get());
                add(output, ACItemRegistry.PEARL.get());
                add(output, ACItemRegistry.INK_BOMB.get());
                add(output, ACItemRegistry.GLOW_INK_BOMB.get());
                add(output, ACItemRegistry.ORTHOLANCE.get());
                add(output, ACItemRegistry.MAGIC_CONCH.get());
                add(output, ACItemRegistry.SEA_STAFF.get());
                add(output, ACBlockRegistry.ABYSSMARINE.get());
                add(output, ACBlockRegistry.ABYSSMARINE_STAIRS.get());
                add(output, ACBlockRegistry.ABYSSMARINE_SLAB.get());
                add(output, ACBlockRegistry.ABYSSMARINE_WALL.get());
                add(output, ACBlockRegistry.ABYSSAL_ALTAR.get());
                add(output, ACBlockRegistry.ABYSSMARINE_BRICKS.get());
                add(output, ACBlockRegistry.ABYSSMARINE_BRICK_STAIRS.get());
                add(output, ACBlockRegistry.ABYSSMARINE_BRICK_SLAB.get());
                add(output, ACBlockRegistry.ABYSSMARINE_BRICK_WALL.get());
                add(output, ACBlockRegistry.ABYSSMARINE_PILLAR.get());
                add(output, ACBlockRegistry.ABYSSMARINE_TILES.get());
                add(output, ACBlockRegistry.MUCK.get());
                add(output, ACBlockRegistry.GEOTHERMAL_VENT.get());
                add(output, ACBlockRegistry.GEOTHERMAL_VENT_MEDIUM.get());
                add(output, ACBlockRegistry.GEOTHERMAL_VENT_THIN.get());
                add(output, ACBlockRegistry.TUBE_WORM.get());
                add(output, ACBlockRegistry.SMOOTH_BONE.get());
                add(output, ACBlockRegistry.SMOOTH_BONE_STAIRS.get());
                add(output, ACBlockRegistry.SMOOTH_BONE_SLAB.get());
                add(output, ACBlockRegistry.SMOOTH_BONE_WALL.get());
                add(output, ACBlockRegistry.HOLLOW_BONE.get());
                add(output, ACBlockRegistry.THIN_BONE.get());
                add(output, ACBlockRegistry.BONE_NODULE.get());
                add(output, ACBlockRegistry.BONE_RIBS.get());
                add(output, ACBlockRegistry.BALEEN_BONE.get());
                add(output, ACBlockRegistry.BONE_WORMS.get());
                add(output, ACBlockRegistry.PING_PONG_SPONGE.get());
                add(output, ACBlockRegistry.DUSK_ANEMONE.get());
                add(output, ACBlockRegistry.TWILIGHT_ANEMONE.get());
                add(output, ACBlockRegistry.MIDNIGHT_ANEMONE.get());
                add(output, ACBlockRegistry.MUSSEL.get());
                add(output, ACBlockRegistry.DRAIN.get());
                add(output, ACBlockRegistry.SEA_GLASS.get());
                add(output, ACBlockRegistry.COPPER_VALVE.get());
                output.accept(ACEffectRegistry.createPotion(ACEffectRegistry.DEEPSIGHT_POTION.get()));
                output.accept(ACEffectRegistry.createPotion(ACEffectRegistry.LONG_DEEPSIGHT_POTION.get()));
            })
            .build());

    public static final RegistryObject<CreativeModeTab> FORLORN_HOLLOWS = DEF_REG.register("forlorn_hollows", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.alexscaves.forlorn_hollows"))
            .icon(() -> new ItemStack(ACBlockRegistry.PEERING_COPROLITH.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .withTabsBefore(ABYSSAL_CHASM.getKey())
            .displayItems((enabledFeatures, output) -> {
                output.accept(CaveInfoItem.create(ACItemRegistry.CAVE_TABLET.get(), ACBiomeRegistry.FORLORN_HOLLOWS));
                output.accept(CaveInfoItem.create(ACItemRegistry.CAVE_CODEX.get(), ACBiomeRegistry.FORLORN_HOLLOWS));
                add(output, ACBlockRegistry.SPELUNKERY_TABLE.get());
                output.accept(CaveMapItem.createMap(ACBiomeRegistry.FORLORN_HOLLOWS));
                ACItemRegistry.getSpawnEggsForTab(ACBiomeRegistry.FORLORN_HOLLOWS).forEach((spawnEgg -> add(output, spawnEgg.get())));
                add(output, ACBlockRegistry.GUANO_BLOCK.get());
                add(output, ACBlockRegistry.GUANO_LAYER.get());
                add(output, ACBlockRegistry.GUANOSTONE.get());
                add(output, ACBlockRegistry.GUANOSTONE_STAIRS.get());
                add(output, ACBlockRegistry.GUANOSTONE_SLAB.get());
                add(output, ACBlockRegistry.GUANOSTONE_WALL.get());
                add(output, ACBlockRegistry.GUANOSTONE_BRICKS.get());
                add(output, ACBlockRegistry.GUANOSTONE_BRICK_STAIRS.get());
                add(output, ACBlockRegistry.GUANOSTONE_BRICK_SLAB.get());
                add(output, ACBlockRegistry.GUANOSTONE_BRICK_WALL.get());
                add(output, ACBlockRegistry.GUANOSTONE_CHISELED.get());
                add(output, ACBlockRegistry.GUANOSTONE_TILES.get());
                add(output, ACBlockRegistry.GUANOSTONE_REDSTONE_ORE.get());
                add(output, ACBlockRegistry.COPROLITH.get());
                add(output, ACBlockRegistry.COPROLITH_STAIRS.get());
                add(output, ACBlockRegistry.COPROLITH_SLAB.get());
                add(output, ACBlockRegistry.COPROLITH_WALL.get());
                add(output, ACBlockRegistry.SMOOTH_COPROLITH.get());
                add(output, ACBlockRegistry.SMOOTH_COPROLITH_STAIRS.get());
                add(output, ACBlockRegistry.SMOOTH_COPROLITH_SLAB.get());
                add(output, ACBlockRegistry.SMOOTH_COPROLITH_WALL.get());
                add(output, ACBlockRegistry.COPROLITH_COAL_ORE.get());
                add(output, ACBlockRegistry.POROUS_COPROLITH.get());
                add(output, ACBlockRegistry.PEERING_COPROLITH.get());
                add(output, ACBlockRegistry.FORSAKEN_IDOL.get());
                add(output, ACBlockRegistry.THORNWOOD_LOG.get());
                add(output, ACBlockRegistry.THORNWOOD_BRANCH.get());
                add(output, ACBlockRegistry.THORNWOOD_WOOD.get());
                add(output, ACBlockRegistry.STRIPPED_THORNWOOD_LOG.get());
                add(output, ACBlockRegistry.STRIPPED_THORNWOOD_WOOD.get());
                add(output, ACBlockRegistry.THORNWOOD_PLANKS.get());
                add(output, ACBlockRegistry.THORNWOOD_PLANKS_STAIRS.get());
                add(output, ACBlockRegistry.THORNWOOD_PLANKS_SLAB.get());
                add(output, ACBlockRegistry.THORNWOOD_PLANKS_FENCE.get());
                add(output, ACItemRegistry.THORNWOOD_SIGN.get());
                add(output, ACItemRegistry.THORNWOOD_HANGING_SIGN.get());
                add(output, ACBlockRegistry.THORNWOOD_PRESSURE_PLATE.get());
                add(output, ACItemRegistry.THORNWOOD_DOOR.get());
                add(output, ACBlockRegistry.THORNWOOD_TRAPDOOR.get());
                add(output, ACBlockRegistry.THORNWOOD_BUTTON.get());
                add(output, ACBlockRegistry.THORNWOOD_FENCE_GATE.get());
                add(output, ACBlockRegistry.UNDERWEED.get());
            })
            .build());

    private static void add(CreativeModeTab.Output tab, ItemLike itemLike){
        if(itemLike instanceof CustomTabBehavior customTabBehavior){
            customTabBehavior.fillItemCategory(tab);
        }else{
            tab.accept(itemLike);
        }
    }
}
