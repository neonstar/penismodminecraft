
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
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;

import net.mcreator.penismod.procedure.ProcedureHuyRightClickedOnBlock;
import net.mcreator.penismod.ElementsPenismod;

import java.util.List;

import com.google.common.collect.Multimap;

@ElementsPenismod.ModElement.Tag
public class ItemHuy extends ElementsPenismod.ModElement {
	@GameRegistry.ObjectHolder("penismod:huy")
	public static final Item block = null;
	public ItemHuy(ElementsPenismod instance) {
		super(instance, 3);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("penismod:huy", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(40);
			maxStackSize = 1;
			setUnlocalizedName("huy");
			setRegistryName("huy");
			setCreativeTab(CreativeTabs.MISC);
		}

		@Override
		public int getItemEnchantability() {
			return 1;
		}

		@Override
		public int getMaxItemUseDuration(ItemStack itemstack) {
			return 10;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, IBlockState par2Block) {
			return 1.5F;
		}

		@Override
		public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot slot) {
			Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(slot);
			if (slot == EntityEquipmentSlot.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
						new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Item modifier", (double) 46, 0));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Item modifier", -2.4, 0));
			}
			return multimap;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add("\u041C\u043E\u0431\u0430\u043C \u043F\u0438\u0437\u0434\u0430");
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
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureHuyRightClickedOnBlock.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
