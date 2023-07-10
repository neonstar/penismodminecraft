package net.mcreator.penismod.procedure;

import net.minecraft.world.World;

import net.mcreator.penismod.ElementsPenismod;

@ElementsPenismod.ModElement.Tag
public class ProcedureMegaBombBulletHitsBlock extends ElementsPenismod.ModElement {
	public ProcedureMegaBombBulletHitsBlock(ElementsPenismod instance) {
		super(instance, 13);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MegaBombBulletHitsBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MegaBombBulletHitsBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MegaBombBulletHitsBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MegaBombBulletHitsBlock!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (!world.isRemote) {
			world.createExplosion(null, (int) x, (int) y, (int) z, (float) 8, true);
		}
	}
}
