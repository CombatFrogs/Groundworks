package com.hoborific.cryo.modding.world;

import com.hoborific.cryo.modding.GroundworksBlocks;
import com.hoborific.cryo.modding.SlateBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class BiomeFeatures {

    @Inject(at = @At(value = "HEAD"), method = "addMineables")
    private static void addMineables(Biome biome, CallbackInfo unusedCallbackInfo) {
        Block[] slateBlocks = new Block[]{
                SlateBlocks.getInstance().getIdentifiers(SlateBlocks.SlateColor.BLUE).getSlateBlock().getRight(),
                SlateBlocks.getInstance().getIdentifiers(SlateBlocks.SlateColor.GREEN).getSlateBlock().getRight(),
                SlateBlocks.getInstance().getIdentifiers(SlateBlocks.SlateColor.PURPLE).getSlateBlock().getRight()
        };

        // Slate variants should spawn in similar sized veins as 1.8 stones, with similar frequency.
        for (Block slateBlock : slateBlocks) {
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Biome.configureFeature(
                            net.minecraft.world.gen.feature.Feature.ORE,
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    slateBlock.getDefaultState(),
                                    33 /* size */),
                            Decorator.COUNT_RANGE,
                            new RangeDecoratorConfig(
                                    10 /* count */, 0 /* bottomOffset */, 0 /* topOffset */, 80 /* max */)));
        }

        // Chalk should spawn in similar sized veins as 1.8 stones, with significantly lower frequency.
        biome.addFeature(
                GenerationStep.Feature.UNDERGROUND_ORES,
                Biome.configureFeature(
                        net.minecraft.world.gen.feature.Feature.ORE,
                        new OreFeatureConfig(
                                OreFeatureConfig.Target.NATURAL_STONE,
                                GroundworksBlocks.CHALK.getDefaultState(),
                                33 /* size */),
                        Decorator.COUNT_RANGE,
                        new RangeDecoratorConfig(
                                3 /* count */, 0 /* bottomOffset */, 0 /* topOffset */, 80 /* max */)));
    }
}
