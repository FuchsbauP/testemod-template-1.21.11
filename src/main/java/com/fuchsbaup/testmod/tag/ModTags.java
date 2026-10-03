package com.fuchsbaup.testmod.tag;

import com.fuchsbaup.testmod.TestMod;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_AURIC_TOOL = createBlockTag("needs_auric_tool");
        public static final TagKey<Block> INCORRECT_FOR_AURIC_TOOL = createBlockTag("incorrect_for_auric_tool");

        private static TagKey<Block> createBlockTag(String name){
            return BlockTags.create(Identifier.fromNamespaceAndPath(TestMod.MODID, name));
        }
    }

    public static class Items{
        public static final TagKey<Item> AURIC_REPAIRABLES = createItemTag("auric_repairables");

        private static TagKey<Item> createItemTag(String name){
            return ItemTags.create(Identifier.fromNamespaceAndPath(TestMod.MODID, name));
        }
    }
}
