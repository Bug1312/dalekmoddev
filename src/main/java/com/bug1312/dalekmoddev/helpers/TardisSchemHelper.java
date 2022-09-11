package com.bug1312.dalekmoddev.helpers;

import java.util.ArrayList;
import java.util.List;

import com.swdteam.util.world.Schematic;
import com.swdteam.util.world.SchematicUtils;
import com.swdteam.util.world.TileData;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TardisSchemHelper {

	public static void saveSchem(BlockPos start, BlockPos exit, World world, String name) {
		BlockPos blockpos = start;
		BlockPos blockpos1 = exit;

		BlockPos blockpos2 = new BlockPos(Math.min(blockpos.getX(), blockpos1.getX()), Math.min(blockpos.getY(), blockpos1.getY()), Math.min(blockpos.getZ(), blockpos1.getZ()));
		BlockPos blockpos3 = new BlockPos(Math.max(blockpos.getX(), blockpos1.getX()), Math.max(blockpos.getY(), blockpos1.getY()), Math.max(blockpos.getZ(), blockpos1.getZ()));
		int i = (blockpos3.getX() - blockpos2.getX() + 1) * (blockpos3.getY() - blockpos2.getY() + 1) * (blockpos3.getZ() - blockpos2.getZ() + 1);

		int[] blockMap = new int[i];
		List<TileData> tiles = new ArrayList<>();

		int index = 0;
		int x = blockpos3.getX() - blockpos2.getX();
		int y = blockpos3.getY() - blockpos2.getY();
		int z = blockpos3.getZ() - blockpos2.getZ();

		Schematic schem = new Schematic(x, y, z);

		if (blockpos2.getY() >= 0 && blockpos3.getY() < 256) {
			for (int i1 = blockpos2.getY(); i1 <= blockpos3.getY(); ++i1) {
				for (int j1 = blockpos2.getX(); j1 <= blockpos3.getX(); ++j1) {
					for (int l = blockpos2.getZ(); l <= blockpos3.getZ(); ++l) {
						BlockPos pos = new BlockPos(j1, i1, l);
						BlockState block = world.getBlockState(pos);

						blockMap[index] = schem.getBlockID(block);

						TileEntity te = world.getBlockEntity(pos);
						if (te != null) {
							CompoundNBT tag = new CompoundNBT();
							te.save(tag);
							TileData tileData = new TileData();

							tileData.setPos(new int[] { x - (blockpos3.getX() - pos.getX()), y - (blockpos3.getY() - pos.getY()), z - (blockpos3.getZ() - pos.getZ()) });
							tileData.setNbtData(tag);
							tiles.add(tileData);
						}

						index++;
					}
				}
			}

			schem.setBlockMap(blockMap);
			schem.setTileData(tiles);

			SchematicUtils.saveSchematic(schem, name);
		}
	}
	
}
