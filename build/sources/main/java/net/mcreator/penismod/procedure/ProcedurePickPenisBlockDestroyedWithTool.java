package net.mcreator.penismod.procedure;

import net.minecraft.world.World;
import net.minecraft.entity.item.EntityXPOrb;

import net.mcreator.penismod.ElementsPenismod;

@ElementsPenismod.ModElement.Tag
public class ProcedurePickPenisBlockDestroyedWithTool extends ElementsPenismod.ModElement {
	public ProcedurePickPenisBlockDestroyedWithTool(ElementsPenismod instance) {
		super(instance, 14);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PickPenisBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PickPenisBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PickPenisBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PickPenisBlockDestroyedWithTool!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (!world.isRemote) {
			world.spawnEntity(new EntityXPOrb(world, x, y, z, (int) 1));
		}
	}
}
