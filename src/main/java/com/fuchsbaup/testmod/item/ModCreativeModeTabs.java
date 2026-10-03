package com.fuchsbaup.testmod.item;

import com.fuchsbaup.testmod.TestMod;
import com.fuchsbaup.testmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MODID);

    public static final Supplier<CreativeModeTab> TEST_MOD_AURIC = CREATIVE_MODE_TABS.register("test_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.AURIC_SHARD.get()))
                    .title(Component.translatable("creativetab.testmod.test_mod"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_AURIC);
                        output.accept(ModItems.AURIC_SHARD);

                        output.accept(ModItems.AURIC_AXE);
                        output.accept(ModItems.AURIC_HOE);
                        output.accept(ModItems.AURIC_PICKAXE);
                        output.accept(ModItems.AURIC_SHOVEL);
                        output.accept(ModItems.AURIC_SWORD);

                        output.accept(ModBlocks.AURIC_ORE);
                        output.accept(ModBlocks.AURIC_DEEPSLATE_ORE);
                        output.accept(ModBlocks.AURIC_BLOCK);
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
