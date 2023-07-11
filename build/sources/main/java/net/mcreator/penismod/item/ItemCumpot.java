
package net.mcreator.penismod.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumActionResult;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;

import net.mcreator.penismod.procedure.ProcedureCumpotRightClickedOnBlock;
import net.mcreator.penismod.ElementsPenismod;

import java.util.List;

@ElementsPenismod.ModElement.Tag
public class ItemCumpot extends ElementsPenismod.ModElement {
	@GameRegistry.ObjectHolder("penismod:cumpot")
	public static final Item block = null;
	public ItemCumpot(ElementsPenismod instance) {
		super(instance, 36);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("penismod:cumpot", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 64;
			setUnlocalizedName("cumpot");
			setRegistryName("cumpot");
			setCreativeTab(CreativeTabs.MISC);
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getMaxItemUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, IBlockState par2Block) {
			return 1F;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(
					"\u0412\u0435\u0434\u0440\u043E \u0441 cum\u043E\u043C. \u0415\u0441\u043B\u0438 \u0432\u044B\u043F\u0438\u0442\u044C \u0434\u0430\u0441\u0442 \u0431\u0430\u0444\u0444 \u0441\u0438\u043B\u044B. \u0421 \u0448\u0438\u0444\u0442\u043E\u043C \u043C\u043E\u0436\u043D\u043E \u0440\u0430\u0437\u043B\u0438\u0442\u044C.");
		}

		@Override
		public EnumActionResult onItemUseFirst(EntityPlayer entity, World world, BlockPos pos, EnumFacing direction, float hitX, float hitY,
				float hitZ, EnumHand hand) {
			EnumActionResult retval = super.onItemUseFirst(entity, world, pos, direction, hitX, hitY, hitZ, hand);
			ItemStack itemstack = entity.getHeldItem(hand);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("itemstack", itemstack);
				$_dependencies.put("world", world);
				ProcedureCumpotRightClickedOnBlock.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
