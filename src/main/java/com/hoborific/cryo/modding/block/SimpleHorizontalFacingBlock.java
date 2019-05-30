package com.hoborific.cryo.modding.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;

public class SimpleHorizontalFacingBlock extends HorizontalFacingBlock {
    public SimpleHorizontalFacingBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext) {
        return this.getDefaultState().with(FACING, itemPlacementContext.getPlayerHorizontalFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactoryBuilder) {
        stateFactoryBuilder.add(FACING);
    }
}
