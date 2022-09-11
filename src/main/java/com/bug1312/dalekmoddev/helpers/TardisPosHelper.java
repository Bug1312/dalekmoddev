package com.bug1312.dalekmoddev.helpers;

import com.swdteam.common.init.DMTardis;

import net.minecraft.util.math.BlockPos;

public class TardisPosHelper {

	public static BlockPos getSchemStart(BlockPos pos) {		
		int id = DMTardis.getIDForXZ(pos.getX(), pos.getZ());
		
		BlockPos start = new BlockPos(DMTardis.getXZForMap(id).getX() * 256, 0, DMTardis.getXZForMap(id).getZ() * 256);

		return start.offset(113, 106, 113);
	}
	
}
