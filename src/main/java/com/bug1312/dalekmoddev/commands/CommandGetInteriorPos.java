package com.bug1312.dalekmoddev.commands;

import com.bug1312.dalekmoddev.helpers.DevChatHelper;
import com.bug1312.dalekmoddev.helpers.TardisPosHelper;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class CommandGetInteriorPos {

	// Stand and look where you want to enter, you don't need to be precise and will automatically center you and round rotations.
	// Outputs door_offset block_offset in the center of the block and default_rotation rounded to the nearest 90 degree.
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("dev-pos").executes((commandContext) -> {
			ServerPlayerEntity player = commandContext.getSource().getPlayerOrException();

			BlockPos schemStart = TardisPosHelper.getSchemStart(player.blockPosition());
			BlockPos offsetBp = player.blockPosition().subtract(schemStart);
			Vector3d offset = new Vector3d(offsetBp.getX() + 0.5, offsetBp.getY(), offsetBp.getZ() + 0.5);

			DevChatHelper.sendInfo(player, "Door Offset: " + offset.toString());
			DevChatHelper.sendInfo(player, "Block Offset: (0, -2, 0)");
			DevChatHelper.sendInfo(player, "Rotation: " + (int) Direction.fromYRot(player.yRot).toYRot());
			
			return 1;
		}));
	}
}
