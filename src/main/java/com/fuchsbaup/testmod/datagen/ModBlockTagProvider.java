package com.fuchsbaup.testmod.datagen;

import com.fuchsbaup.testmod.TestMod;
import com.fuchsbaup.testmod.block.ModBlocks;
import com.fuchsbaup.testmod.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TestMod.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.AURIC_ORE.getKey())
                .add(ModBlocks.AURIC_DEEPSLATE_ORE.getKey())
                .add(ModBlocks.AURIC_BLOCK.getKey());

        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.AURIC_ORE.getKey());

        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.AURIC_DEEPSLATE_ORE.getKey());

        tag(ModTags.Blocks.NEEDS_AURIC_TOOL)
                .addTags(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.AURIC_BLOCK.getKey());

        tag(ModTags.Blocks.INCORRECT_FOR_AURIC_TOOL)
                .addTags(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_AURIC_TOOL);
    }
}
