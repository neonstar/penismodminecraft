
package net.mcreator.penismod.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.mcreator.penismod.item.ItemPenisGem;
import net.mcreator.penismod.block.BlockPenisOre;
import net.mcreator.penismod.ElementsPenismod;

@ElementsPenismod.ModElement.Tag
public class RecipePenis extends ElementsPenismod.ModElement {
	public RecipePenis(ElementsPenismod instance) {
		super(instance, 9);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockPenisOre.block, (int) (1)), new ItemStack(ItemPenisGem.block, (int) (1)), 1F);
	}
}
