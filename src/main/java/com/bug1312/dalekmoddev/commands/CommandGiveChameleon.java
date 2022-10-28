package com.bug1312.dalekmoddev.commands;

import com.bug1312.dalekmoddev.commands.arguments.ChameleonArgument;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.swdteam.common.init.DMItems;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class CommandGiveChameleon {

	// Input chameleon and get a circuit.
	// Gives chameleon circuit with skin ID set to inputed value

	private static final LiteralArgumentBuilder<CommandSource> INITIAL = Commands.literal("dev-cartridge");
	private static final RequiredArgumentBuilder<CommandSource, ResourceLocation> SKIN = Commands.argument("skin", ChameleonArgument.chameleon());
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(INITIAL.then(SKIN.executes((commandContext) -> {
			ServerPlayerEntity player = commandContext.getSource().getPlayerOrException();
			String skin = commandContext.getArgument("skin", ResourceLocation.class).toString();
			
			ItemStack stack = new ItemStack(DMItems.CHAMELEON_CATRTIDGE.get());
			CompoundNBT tag = stack.getOrCreateTag();
			tag.putString("skin_id", skin);
			
			player.addItem(stack);
			return 1;
		})));
	}
	
}

