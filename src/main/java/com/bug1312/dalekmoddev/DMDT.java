package com.bug1312.dalekmoddev;

import com.bug1312.dalekmoddev.commands.CommandCreateTardisImage;
import com.bug1312.dalekmoddev.commands.CommandCreateTardisSchem;
import com.bug1312.dalekmoddev.commands.CommandGetInteriorPos;
import com.bug1312.dalekmoddev.commands.CommandGiveChameleon;
import com.bug1312.dalekmoddev.commands.arguments.ChameleonArgument;
import com.google.common.base.Supplier;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;

import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(DMDT.MOD_ID)
public class DMDT {
	public static final String MOD_ID = "dalekmoddev";

	public DMDT() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void registerArguments(FMLCommonSetupEvent event) {
		registerArgument("chameleon", ChameleonArgument::chameleon);
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends ArgumentType<?>> void registerArgument(String id, Supplier<T> argument) {
	    ArgumentTypes.register(new ResourceLocation(MOD_ID, id).toString(), (Class<T>) argument.get().getClass(), new ArgumentSerializer<>(argument));	
	}
	
	@SubscribeEvent
	public void registerCommands(RegisterCommandsEvent event) {
		CommandDispatcher<CommandSource> d = event.getDispatcher();
		
		CommandCreateTardisImage.register(d);
		CommandCreateTardisSchem.register(d);
		CommandGetInteriorPos.register(d);
		CommandGiveChameleon.register(d);
	}

}
