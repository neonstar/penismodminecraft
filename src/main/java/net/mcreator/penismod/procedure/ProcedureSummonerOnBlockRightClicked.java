package net.mcreator.penismod.procedure;

import net.minecraftforge.fml.common.FMLCommonHandler;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.Entity;

import net.mcreator.penismod.entity.EntityAida;
import net.mcreator.penismod.ElementsPenismod;

@ElementsPenismod.ModElement.Tag
public class ProcedureSummonerOnBlockRightClicked extends ElementsPenismod.ModElement {
	public ProcedureSummonerOnBlockRightClicked(ElementsPenismod instance) {
		super(instance, 17);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SummonerOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SummonerOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SummonerOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SummonerOnBlockRightClicked!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (!world.isRemote) {
			Entity entityToSpawn = new EntityAida.EntityCustom(world);
			if (entityToSpawn != null) {
				entityToSpawn.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360F, 0.0F);
				world.spawnEntity(entityToSpawn);
			}
		}
		world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
		{
			MinecraftServer mcserv = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new TextComponentString(
						"\u0410\u0445\u0430\u0445\u0430\u0445, \u0435\u0431\u0430\u0442\u044C \u0442\u044B \u0434\u0435\u0431\u0438\u043B)) \u043D\u0443 \u0442\u0435\u0431\u0435 \u043F\u0438\u0437\u0434\u0430 \u043F\u043E\u043B\u0443\u0447\u0430\u0435\u0442\u0441\u044F"));
		}
	}
}
