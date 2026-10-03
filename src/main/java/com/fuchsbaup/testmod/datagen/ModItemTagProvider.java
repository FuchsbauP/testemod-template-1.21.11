package com.fuchsbaup.testmod.datagen;

import com.fuchsbaup.testmod.TestMod;
import com.fuchsbaup.testmod.item.ModItems;
import com.fuchsbaup.testmod.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TestMod.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.AURIC_REPAIRABLES).add(ModItems.AURIC_SHARD.get());

        tag(ItemTags.AXES).add(ModItems.AURIC_AXE.get());
        tag(ItemTags.HOES).add(ModItems.AURIC_HOE.get());
        tag(ItemTags.PICKAXES).add(ModItems.AURIC_PICKAXE.get());
        tag(ItemTags.SHOVELS).add(ModItems.AURIC_SHOVEL.get());
        tag(ItemTags.SWORDS).add(ModItems.AURIC_SWORD.get());

        tag(ItemTags.HEAD_ARMOR).add(ModItems.AURIC_HELMET.get());
        tag(ItemTags.CHEST_ARMOR).add(ModItems.AURIC_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR).add(ModItems.AURIC_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR).add(ModItems.AURIC_BOOTS.get());
    }
}
