package com.fuchsbaup.testmod.datagen;

import com.fuchsbaup.testmod.TestMod;
import com.fuchsbaup.testmod.item.ModArmorMaterials;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEquipmentAssetProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;

    public ModEquipmentAssetProvider(PackOutput output ) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    private static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output){
        output.accept(ModArmorMaterials.AURIC_KEY, EquipmentClientInfo.builder()
                        .addHumanoidLayers(Identifier.fromNamespaceAndPath(TestMod.MODID, "auric"), false)
                .build());
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> map = new HashMap<>();
        bootstrap((equipmentAssetResourceKey, equipmentClientInfo) -> {
            if(map.putIfAbsent(equipmentAssetResourceKey, equipmentClientInfo) != null){
                throw new IllegalStateException("Tried to register equipment twice for id: " + equipmentAssetResourceKey);
            }
        });
        return DataProvider.saveAll(cachedOutput, EquipmentClientInfo.CODEC, this.pathProvider::json, map);
    }

    @Override
    public String getName() {
        return "Test Mod Equipment Definitions";
    }
}
