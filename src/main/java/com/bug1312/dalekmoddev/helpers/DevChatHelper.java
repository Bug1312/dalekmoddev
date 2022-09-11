package com.bug1312.dalekmoddev.helpers;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

public class DevChatHelper {

	public static int sendError(ServerPlayerEntity player, String error) {
		player.displayClientMessage(new StringTextComponent(error).setStyle(Style.EMPTY.withColor(TextFormatting.RED)), true);
		return 0;
	}
	
	public static int sendInfo(ServerPlayerEntity player, String info) {
		player.displayClientMessage(new StringTextComponent(info), false);
		return 1;
	}
	
}
