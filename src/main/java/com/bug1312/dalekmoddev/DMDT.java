package com.bug1312.dalekmoddev;

import com.bug1312.dalekmoddev.commands.CommandCreateTardisImage;
import com.bug1312.dalekmoddev.commands.CommandCreateTardisSchem;
import com.bug1312.dalekmoddev.commands.CommandGetInteriorPos;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(DMDT.MOD_ID)
public class DMDT {
	public static final String MOD_ID = "dalekmoddev";

	public DMDT() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void registerCommands(RegisterCommandsEvent event) {
		CommandCreateTardisImage.register(event.getDispatcher());
		CommandCreateTardisSchem.register(event.getDispatcher());
		CommandGetInteriorPos.register(event.getDispatcher());
	}

}
