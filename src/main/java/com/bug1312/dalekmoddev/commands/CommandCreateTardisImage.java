package com.bug1312.dalekmoddev.commands;

import com.bug1312.dalekmoddev.helpers.DevChatHelper;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameType;

public class CommandCreateTardisImage {

	// Stand in front of the console looking in it's general direction.
	// Sets lighting, position, rotation, and FOV for you. You must edit the photo to fit with the rest.
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("dev-image").executes((commandContext) -> {
			ServerPlayerEntity player = commandContext.getSource().getPlayerOrException();
			
			try {
				// Get prior data
				Minecraft mc = Minecraft.getInstance();
				GameSettings options = mc.options;
								
				double beforeFov = options.fov;
				double beforeGamma = options.gamma;
				boolean beforeF1 = options.hideGui;
				PointOfView beforePov = options.getCameraType();
				float beforeYRot = player.yRot;
				float beforeXRot = player.xRot;
				Vector3d beforePlayerPos = player.position();
				GameType beforeGameType = player.gameMode.getGameModeForPlayer();
				BlockPos playerPos = player.blockPosition();
				Direction dir = Direction.fromYRot(player.yRot);
				BlockPos consolePos = playerPos.relative(dir, 2);
				BlockPos newPos = consolePos.relative(dir, -6);

				if (!options.fullscreen) return DevChatHelper.sendError(player, "You must be fullscreen!");	
				
				// Set data
				options.fov = 70;	
				options.gamma = 0;
				options.hideGui = true;
				options.setCameraType(PointOfView.FIRST_PERSON);
				
				player.setGameMode(GameType.SPECTATOR);
				player.yRot = dir.toYRot();
				player.xRot = -2;
				
				player.teleportTo(newPos.getX() + 0.5, newPos.getY(), newPos.getZ() + 0.5);
				
				// Screenshot
				Thread.sleep(400); // Time for client to fully update before attempting a screenshot
				ScreenShotHelper.grab(mc.gameDirectory, mc.getWindow().getWidth(), mc.getWindow().getHeight(), mc.getMainRenderTarget(), (shot) -> {
					mc.execute(() -> { 
						mc.gui.getChat().addMessage(shot);
						
						// Revert data
						options.fov = beforeFov;
						options.gamma = beforeGamma;
						options.hideGui = beforeF1;
						options.setCameraType(beforePov);

						player.setGameMode(beforeGameType);
						player.yRot = beforeYRot;
						player.xRot = beforeXRot;
						player.teleportTo(beforePlayerPos.x, beforePlayerPos.y, beforePlayerPos.z);
					});				
				});
			} catch (Exception e) { e.printStackTrace(); return 0; }
			
			return 1;
		}));
	}
	
}

