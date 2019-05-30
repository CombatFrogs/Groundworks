package com.hoborific.cryo.modding;

import com.google.common.collect.ImmutableMap;
import com.hoborific.cryo.modding.block.SimpleHorizontalFacingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;

import java.util.Map;

/**
 * Container for all variants of Slate blocks, including full blocks, stairs and slabs of raw slate,
 * polished slate, slate tiles and slate shingles.
 */
public class SlateBlocks {
    private static final SlateBlocks INSTANCE = new SlateBlocks();

    private final Map<SlateColor, SlateVariants> slateColorIdentifiers;

    private SlateBlocks() {
        this.slateColorIdentifiers =
                ImmutableMap.<SlateColor, SlateVariants>builder()
                        .put(SlateColor.BLUE, new SlateVariants(SlateColor.BLUE))
                        .put(SlateColor.GREEN, new SlateVariants(SlateColor.GREEN))
                        .put(SlateColor.PURPLE, new SlateVariants(SlateColor.PURPLE))
                        .build();
    }

    /**
     * Singleton instances are more testable than static classes -- feel free to PR some tests, I'm
     * not writing any.
     */
    public static SlateBlocks getInstance() {
        return INSTANCE;
    }

    /**
     * Register all slate block variants with the block and item registries.
     */
    void registerBlocks() {
        Map<Identifier, Block> blockIdMap =
                ImmutableMap.<Identifier, Block>builder()
                        .putAll(getIdentifiers(SlateColor.BLUE).getAll())
                        .putAll(getIdentifiers(SlateColor.GREEN).getAll())
                        .putAll(getIdentifiers(SlateColor.PURPLE).getAll())
                        .build();

        for (Map.Entry<Identifier, Block> blockEntry : blockIdMap.entrySet()) {
            Registry.register(Registry.BLOCK, blockEntry.getKey(), blockEntry.getValue());
            Registry.register(
                    Registry.ITEM,
                    blockEntry.getKey(),
                    new BlockItem(blockEntry.getValue(), new Item.Settings().itemGroup(ItemGroup.MISC)));
        }
    }

    /**
     * Returns all SlateVariants for a given slate color.
     */
    public SlateVariants getIdentifiers(SlateColor slateColor) {
        return slateColorIdentifiers.get(slateColor);
    }

    /**
     * Available colors of slate -- String literals are whack.
     */
    public enum SlateColor {
        BLUE,
        GREEN,
        PURPLE;

        String getSuffix() {
            switch (this) {
                case BLUE:
                    return "";
                case GREEN:
                    return "_green";
                case PURPLE:
                    return "_purple";
            }

            throw new IllegalStateException();
        }
    }

    /**
     * Object that holds the Identifier and Block objects for each variant.
     */
    public static class SlateVariants {
        private final Pair<Identifier, Block> slateBlock;
        private final Pair<Identifier, Block> slateSlab;
        private final Pair<Identifier, Block> polishedBlock;
        private final Pair<Identifier, Block> polishedSlab;
        private final Pair<Identifier, Block> shingleBlock;
        private final Pair<Identifier, Block> shingleSlab;
        private final Pair<Identifier, Block> tileBlock;
        private final Pair<Identifier, Block> tileSlab;

        SlateVariants(SlateColor slateColor) {
            String suffix = slateColor.getSuffix();

            slateBlock =
                    new Pair<>(
                            new Identifier(GroundworksInitializer.MOD_ID, "block_slate" + suffix),
                            new Block(GroundworksBlocks.STONELIKE_BLOCK_SETTINGS));
            slateSlab =
                    new Pair<>(
                            new Identifier(GroundworksInitializer.MOD_ID, "slab_slate" + suffix),
                            new SlabBlock(GroundworksBlocks.STONELIKE_BLOCK_SETTINGS));
            polishedBlock =
                    new Pair<>(
                            new Identifier(GroundworksInitializer.MOD_ID, "block_slate_polished" + suffix),
                            new Block(GroundworksBlocks.STONELIKE_BLOCK_SETTINGS));
            polishedSlab =
                    new Pair<>(
                            new Identifier(GroundworksInitializer.MOD_ID, "slab_slate_polished" + suffix),
                            new SlabBlock(GroundworksBlocks.STONELIKE_BLOCK_SETTINGS));
            shingleBlock =
                    new Pair<>(
                            new Identifier(GroundworksInitializer.MOD_ID, "block_slate_shingle" + suffix),
                            new SimpleHorizontalFacingBlock(GroundworksBlocks.STONELIKE_BLOCK_SETTINGS));
            shingleSlab =
                    new Pair<>(
                            new Identifier(GroundworksInitializer.MOD_ID, "slab_slate_shingle" + suffix),
                            new SlabBlock(GroundworksBlocks.STONELIKE_BLOCK_SETTINGS));
            tileBlock =
                    new Pair<>(
                            new Identifier(GroundworksInitializer.MOD_ID, "block_slate_tile" + suffix),
                            new Block(GroundworksBlocks.STONELIKE_BLOCK_SETTINGS));
            tileSlab =
                    new Pair<>(
                            new Identifier(GroundworksInitializer.MOD_ID, "slab_slate_tile" + suffix),
                            new SlabBlock(GroundworksBlocks.STONELIKE_BLOCK_SETTINGS));
        }

        /**
         * Returns the raw block for this slate type -- for worldgen.
         */
        public Pair<Identifier, Block> getSlateBlock() {
            return slateBlock;
        }

        /**
         * Returns all pairs of (ID, Block) for this slate type.
         */
        public ImmutableMap<Identifier, Block> getAll() {
            return ImmutableMap.<Identifier, Block>builder()
                    .put(slateBlock.getLeft(), slateBlock.getRight())
                    .put(slateSlab.getLeft(), slateSlab.getRight())
                    .put(polishedBlock.getLeft(), polishedBlock.getRight())
                    .put(polishedSlab.getLeft(), polishedSlab.getRight())
                    .put(shingleBlock.getLeft(), shingleBlock.getRight())
                    .put(shingleSlab.getLeft(), shingleSlab.getRight())
                    .put(tileBlock.getLeft(), tileBlock.getRight())
                    .put(tileSlab.getLeft(), tileSlab.getRight())
                    .build();
        }
    }
}
