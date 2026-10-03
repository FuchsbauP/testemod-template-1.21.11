package com.fuchsbaup.testmod.event;

import com.fuchsbaup.testmod.TestMod;
import com.fuchsbaup.testmod.data.ModDataAttachments;
import com.fuchsbaup.testmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = TestMod.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        Player player = event.getEntity();

        if(isWearingAirWalkingArmorItem(player)){
            handleAirWalking(player);
        } else if (player.getData(ModDataAttachments.IS_HOVERING)) {
            player.setData(ModDataAttachments.IS_HOVERING, false);
        }
    }

    private static boolean isWearingAirWalkingArmorItem(Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        return !boots.isEmpty() && boots.is(ModItems.AURIC_BOOTS.get());
    }

    private static void handleAirWalking(Player player) {
        player.fallDistance = 0f;

        if(player.level().isClientSide() && !player.onGround()){
            handleMidAirFlight(player);
            player.setData(ModDataAttachments.IS_HOVERING, true);
        }

        if (player.onGround()){
            player.setData(ModDataAttachments.IS_HOVERING, false);
        }
    }

    private static void handleMidAirFlight(Player player) {
        Vec3 delta = player.getDeltaMovement();

        if (delta.y >= 0f) {
            return;
        }

        double yPos = player.getY();
        double fractionalY = yPos - Math.floor(yPos); // 64.5178 - 64 = 0.5178

        BlockPos posBellow = player.blockPosition().below();
        BlockState stateBellow = player.level().getBlockState(posBellow);
        boolean hasSolidGroundBellow = !stateBellow.getCollisionShape(player.level(), posBellow).isEmpty();

        if (player.isCrouching()) {
            //Manual Descent
            player.setDeltaMovement(delta.x, -0.15f, delta.z);
        } else if (fractionalY > 0.85) {
            //Just walked of a Ledge
            player.setPos(player.getX(), Math.ceil(yPos) + 0.08, player.getZ());
            setPlayerHovering(player, delta);
        } else if (fractionalY > 0.04) {
            //Soft Sink
            player.setDeltaMovement(delta.x, -0.04, delta.z);
        } else if (hasSolidGroundBellow && fractionalY > 0.0) {
            //Ground Snap
            player.setDeltaMovement(delta.x, -0.04, delta.z);
        } else {
            //Grid Hover
            setPlayerHovering(player, delta);
        }

        spawnHoverCubeParticles(player);
    }

    private static void setPlayerHovering(Player player, Vec3 delta) {
        player.setDeltaMovement(delta.x, 0.0, delta.z);
        player.setOnGround(true);
    }

    public static void spawnHoverCubeParticles(Player player) {
        double playerX = Math.floor(player.getX());
        double playerY = Math.floor(player.getY() - 1);
        double playerZ = Math.floor(player.getZ());

        for (int i = 0; i < 12; i++) {
            double xPos = playerX;
            double yPos = playerY;
            double zPos = playerZ;

            int edge = player.getRandom().nextInt(12);
            double offset = player.getRandom().nextDouble();

            switch (edge) {
                case 0 -> { xPos += offset; }
                case 1 -> { xPos += offset; zPos += 1.0; }
                case 2 -> { zPos += offset; }
                case 3 -> { zPos += offset; xPos += 1.0; }

                case 4 -> { xPos += offset; yPos += 1.0; }
                case 5 -> { xPos += offset; zPos += 1.0; yPos += 1.0; }
                case 6 -> { zPos += offset; yPos += 1.0; }
                case 7 -> { zPos += offset; xPos += 1.0; yPos += 1.0; }

                case 8 -> { yPos += offset; }
                case 9 -> { yPos += offset; zPos += 1.0; }
                case 10 -> { yPos += offset; xPos += 1.0; }
                case 11 -> { yPos += offset; xPos += 1.0; zPos += 1.0; }
            }

            player.level().addParticle(ParticleTypes.END_ROD, xPos, yPos, zPos, 0.0, 0.0, 0.0);
        }
    }
}
