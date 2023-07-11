
package net.mcreator.penismod.util;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.util.ResourceLocation;

import net.mcreator.penismod.ElementsPenismod;

@ElementsPenismod.ModElement.Tag
public class LootTablePenislegendchest extends ElementsPenismod.ModElement {
	public LootTablePenislegendchest(ElementsPenismod instance) {
		super(instance, 53);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		LootTableList.register(new ResourceLocation("minecraft", "penislegendchest"));
	}
}
