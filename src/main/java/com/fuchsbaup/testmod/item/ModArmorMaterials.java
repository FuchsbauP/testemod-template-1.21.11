package com.fuchsbaup.testmod.item;

import com.fuchsbaup.testmod.TestMod;
import com.fuchsbaup.testmod.tag.ModTags;
import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Map;

public class ModArmorMaterials {
    public static final ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID =
            ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));

    public static final ResourceKey<EquipmentAsset> AURIC_KEY =
            ResourceKey.create(ROOT_ID, Identifier.fromNamespaceAndPath(TestMod.MODID, "auric"));

    public static final ArmorMaterial AURIC_ARMOR_MATERIAL = new ArmorMaterial(35,
            makeDefense(5, 8, 10, 6, 11), 15, SoundEvents.ARMOR_EQUIP_NAUTILUS,
            2f,0.1f, ModTags.Items.AURIC_REPAIRABLES, AURIC_KEY);

    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest,
                ArmorType.HELMET, helm, ArmorType.BODY, body));
    }

}
