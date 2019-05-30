package com.hoborific.cryo.modding;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

/**
 * {@link net.minecraft.block.Block} objects for block added by this mod.
 */
public class GroundworksBlocks {
    // Misc. blocks not handled elsewhere.
    public static final Block CHALK =
            new Block(FabricBlockSettings.of(Material.STONE).hardness(1.0f).resistance(2.5f).build());
    /**
     * Block settings for blocks which behave like stone (i.e. appropriate tool and hardness).
     */
    static final Block.Settings STONELIKE_BLOCK_SETTINGS =
            FabricBlockSettings.of(Material.STONE)
                    .breakByTool(FabricToolTags.PICKAXES)
                    .hardness(1.5f)
                    .resistance(6.0f)
                    .build();

    /**
     * Do not instantiate.
     */
    private GroundworksBlocks() {
    }
}
