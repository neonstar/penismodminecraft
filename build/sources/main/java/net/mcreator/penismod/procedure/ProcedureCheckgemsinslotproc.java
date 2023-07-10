package net.mcreator.penismod.procedure;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.mcreator.penismod.item.ItemPenisGem;
import net.mcreator.penismod.item.ItemBebraitem;
import net.mcreator.penismod.ElementsPenismod;

@ElementsPenismod.ModElement.Tag
public class ProcedureCheckgemsinslotproc extends ElementsPenismod.ModElement {
	public ProcedureCheckgemsinslotproc(ElementsPenismod instance) {
		super(instance, 33);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Checkgemsinslotproc!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(ItemPenisGem.block, (int) (1)).getItem())) {
			if (entity instanceof EntityPlayer) {
				ItemStack _setstack = new ItemStack(ItemBebraitem.block, (int) (1));
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
			}
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemPenisGem.block, (int) (1)).getItem(), -1, (int) 1, null);
		}
	}
}
