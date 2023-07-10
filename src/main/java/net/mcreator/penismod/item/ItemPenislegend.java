
package net.mcreator.penismod.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.mcreator.penismod.ElementsPenismod;

@ElementsPenismod.ModElement.Tag
public class ItemPenislegend extends ElementsPenismod.ModElement {
	@GameRegistry.ObjectHolder("penismod:penislegend")
	public static final Item block = null;
	public ItemPenislegend(ElementsPenismod instance) {
		super(instance, 39);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("penismod:penislegend", "inventory"));
	}
	public static class MusicDiscItemCustom extends ItemRecord {
		public MusicDiscItemCustom() {
			super("penislegend", ElementsPenismod.sounds.get(new ResourceLocation("penismod:legendofthepnis")));
			setUnlocalizedName("penislegend");
			setRegistryName("penislegend");
			setCreativeTab(CreativeTabs.MISC);
		}
	}
}
