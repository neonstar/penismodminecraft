package net.mcreator.penismod.procedure;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.mcreator.penismod.ElementsPenismod;

@ElementsPenismod.ModElement.Tag
public class ProcedureHuesospotionPotionStartedapplied extends ElementsPenismod.ModElement {
	public ProcedureHuesospotionPotionStartedapplied(ElementsPenismod instance) {
		super(instance, 27);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure HuesospotionPotionStartedapplied!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 300);
	}
}
