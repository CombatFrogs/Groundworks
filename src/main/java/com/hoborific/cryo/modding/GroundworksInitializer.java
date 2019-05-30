package com.hoborific.cryo.modding;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

/**
 * Main initializer class for this mod, where e.g. blocks and items are registered.
 */
public class GroundworksInitializer implements ModInitializer {
    static final String MOD_ID = "groundworks";

    @Override
    public void onInitialize() {
        SlateBlocks.getInstance().registerBlocks();

        Registry.register(Registry.BLOCK, GroundworksIds.CHALK, GroundworksBlocks.CHALK);
        Registry.register(
                Registry.ITEM,
                GroundworksIds.CHALK,
                new BlockItem(GroundworksBlocks.CHALK, new Item.Settings().itemGroup(ItemGroup.MISC)));
    }
}
