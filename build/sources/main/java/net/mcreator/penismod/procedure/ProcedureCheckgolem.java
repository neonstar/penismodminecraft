package net.mcreator.penismod.procedure;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.init.Blocks;
import net.minecraft.entity.Entity;

import net.mcreator.penismod.entity.EntityCumgolem;
import net.mcreator.penismod.block.BlockPenisOre;
import net.mcreator.penismod.ElementsPenismod;

@ElementsPenismod.ModElement.Tag
public class ProcedureCheckgolem extends ElementsPenismod.ModElement {
	public ProcedureCheckgolem(ElementsPenismod instance) {
		super(instance, 41);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Checkgolem!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Checkgolem!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Checkgolem!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Checkgolem!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((((((world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 0), (int) (z + 0)))).getBlock() == Blocks.LIT_PUMPKIN.getDefaultState()
				.getBlock())
				&& ((world.getBlockState(new BlockPos((int) (x + 0), (int) (y - 1), (int) (z + 0)))).getBlock() == BlockPenisOre.block
						.getDefaultState().getBlock()))
				&& ((world.getBlockState(new BlockPos((int) (x + 0), (int) (y - 2), (int) (z + 0)))).getBlock() == BlockPenisOre.block
						.getDefaultState().getBlock()))
				&& ((((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 3), (int) (z + 0)))).getBlock() == BlockPenisOre.block
						.getDefaultState().getBlock())
						|| ((world.getBlockState(new BlockPos((int) (x + 0), (int) (y - 3), (int) (z + 1)))).getBlock() == BlockPenisOre.block
								.getDefaultState().getBlock()))
						&& (((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 3), (int) (z + 0)))).getBlock() == BlockPenisOre.block
								.getDefaultState().getBlock())
								|| ((world.getBlockState(new BlockPos((int) (x + 0), (int) (y - 3), (int) (z - 1)))).getBlock() == BlockPenisOre.block
										.getDefaultState().getBlock()))))) {
			if (!world.isRemote) {
				Entity entityToSpawn = new EntityCumgolem.EntityCustom(world);
				if (entityToSpawn != null) {
					entityToSpawn.setLocationAndAngles(x, (y - 1), z, world.rand.nextFloat() * 360F, 0.0F);
					world.spawnEntity(entityToSpawn);
				}
			}
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			world.destroyBlock(new BlockPos((int) x, (int) (y - 1), (int) z), false);
			world.destroyBlock(new BlockPos((int) x, (int) (y - 2), (int) z), false);
			world.destroyBlock(new BlockPos((int) x, (int) (y - 3), (int) z), false);
			world.destroyBlock(new BlockPos((int) (x + 1), (int) (y - 3), (int) z), false);
			world.destroyBlock(new BlockPos((int) (x - 1), (int) (y - 3), (int) z), false);
			world.destroyBlock(new BlockPos((int) x, (int) (y - 3), (int) (z + 1)), false);
			world.destroyBlock(new BlockPos((int) x, (int) (y - 3), (int) (z - 1)), false);
		}
	}

	@SubscribeEvent
	public void onBlockPlace(BlockEvent.PlaceEvent event) {
		Entity entity = event.getPlayer();
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
		dependencies.put("ismainhand", event.getHand() == EnumHand.MAIN_HAND);
		dependencies.put("x", (int) event.getPos().getX());
		dependencies.put("y", (int) event.getPos().getY());
		dependencies.put("z", (int) event.getPos().getZ());
		dependencies.put("px", (int) entity.posX);
		dependencies.put("py", (int) entity.posY);
		dependencies.put("pz", (int) entity.posZ);
		dependencies.put("world", event.getWorld());
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
