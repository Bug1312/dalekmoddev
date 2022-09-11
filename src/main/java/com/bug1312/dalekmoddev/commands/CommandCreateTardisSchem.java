package com.bug1312.dalekmoddev.commands;

import com.bug1312.dalekmoddev.helpers.DevChatHelper;
import com.bug1312.dalekmoddev.helpers.TardisPosHelper;
import com.bug1312.dalekmoddev.helpers.TardisSchemHelper;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class CommandCreateTardisSchem {

	// Stand anywhere in the TARDIS. Make sure the TARDIS fits with the corridors of another TARDIS.
	// Saves TARDIS schem as "dev_output"
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("dev-schem").executes((commandContext) -> {
			ServerPlayerEntity player = commandContext.getSource().getPlayerOrException();
			
			BlockPos schemStart = TardisPosHelper.getSchemStart(player.blockPosition()),
					schemEnd = schemStart.offset(31, 40, 31);
			
			TardisSchemHelper.saveSchem(schemStart, schemEnd, player.getLevel(), "dev_output");
			return DevChatHelper.sendInfo(player, "Schematic saved as 'dev_output'");
		}));
	}
	
}
