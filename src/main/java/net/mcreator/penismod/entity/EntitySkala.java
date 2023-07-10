
package net.mcreator.penismod.entity;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.BossInfo;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.init.Items;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.Minecraft;
import net.minecraft.block.state.IBlockState;

import net.mcreator.penismod.procedure.ProcedureSkalaThisEntityKillsAnotherOne;
import net.mcreator.penismod.item.ItemMegaBomb;
import net.mcreator.penismod.item.ItemHuy;
import net.mcreator.penismod.ElementsPenismod;

import java.util.Random;
import java.util.Iterator;
import java.util.ArrayList;

@ElementsPenismod.ModElement.Tag
public class EntitySkala extends ElementsPenismod.ModElement {
	public static final int ENTITYID = 4;
	public static final int ENTITYID_RANGED = 5;
	public EntitySkala(ElementsPenismod instance) {
		super(instance, 21);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> EntityEntryBuilder.create().entity(EntityCustom.class).id(new ResourceLocation("penismod", "skala"), ENTITYID)
				.name("skala").tracker(64, 3, true).egg(-16764007, -1).build());
		elements.entities.add(() -> EntityEntryBuilder.create().entity(EntityArrowCustom.class)
				.id(new ResourceLocation("penismod", "entitybulletskala"), ENTITYID_RANGED).name("entitybulletskala").tracker(64, 1, true).build());
	}

	private Biome[] allbiomes(net.minecraft.util.registry.RegistryNamespaced<ResourceLocation, Biome> in) {
		Iterator<Biome> itr = in.iterator();
		ArrayList<Biome> ls = new ArrayList<Biome>();
		while (itr.hasNext())
			ls.add(itr.next());
		return ls.toArray(new Biome[ls.size()]);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustom.class, renderManager -> {
			RenderBiped customRender = new RenderBiped(renderManager, new ModelBiped(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("penismod:textures/scala.png");
				}
			};
			customRender.addLayer(new net.minecraft.client.renderer.entity.layers.LayerBipedArmor(customRender) {
				protected void initArmor() {
					this.modelLeggings = new ModelBiped(0.5f);
					this.modelArmor = new ModelBiped(1);
				}
			});
			return customRender;
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowCustom.class, renderManager -> {
			return new RenderSnowball<EntityArrowCustom>(renderManager, null, Minecraft.getMinecraft().getRenderItem()) {
				public ItemStack getStackToRender(EntityArrowCustom entity) {
					return new ItemStack(ItemMegaBomb.block, (int) (1));
				}
			};
		});
	}
	public static class EntityCustom extends EntityMob implements IRangedAttackMob {
		public EntityCustom(World world) {
			super(world);
			setSize(0.6f, 1.8f);
			experienceValue = 1000;
			this.isImmuneToFire = true;
			setNoAI(!true);
			setCustomNameTag("Ñêàëà Äóéýí");
			setAlwaysRenderNameTag(true);
			enablePersistence();
			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemHuy.block, (int) (1)));
			this.navigator = new PathNavigateFlying(this, this.world);
			this.moveHelper = new EntityFlyHelper(this);
		}

		@Override
		protected void initEntityAI() {
			super.initEntityAI();
			this.tasks.addTask(1, new EntityAIWander(this, 2));
			this.tasks.addTask(2, new EntityAIWander(this, 0.8, 20) {
				@Override
				protected Vec3d getPosition() {
					Random random = EntityCustom.this.getRNG();
					double dir_x = EntityCustom.this.posX + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = EntityCustom.this.posY + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = EntityCustom.this.posZ + ((random.nextFloat() * 2 - 1) * 16);
					return new Vec3d(dir_x, dir_y, dir_z);
				}
			});
			this.tasks.addTask(3, new EntityAILookIdle(this));
			this.tasks.addTask(4, new EntityAISwimming(this));
			this.tasks.addTask(5, new EntityAILeapAtTarget(this, (float) 0.8));
			this.tasks.addTask(6, new EntityAIBase() {
				{
					this.setMutexBits(1);
				}
				public boolean shouldExecute() {
					if (EntityCustom.this.getAttackTarget() != null && !EntityCustom.this.getMoveHelper().isUpdating()) {
						return true;
					} else {
						return false;
					}
				}

				@Override
				public boolean shouldContinueExecuting() {
					return EntityCustom.this.getMoveHelper().isUpdating() && EntityCustom.this.getAttackTarget() != null
							&& EntityCustom.this.getAttackTarget().isEntityAlive();
				}

				@Override
				public void startExecuting() {
					EntityLivingBase livingentity = EntityCustom.this.getAttackTarget();
					Vec3d vec3d = livingentity.getPositionEyes(1);
					EntityCustom.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
				}

				@Override
				public void updateTask() {
					EntityLivingBase livingentity = EntityCustom.this.getAttackTarget();
					double d0 = EntityCustom.this.getDistanceSq(livingentity);
					if (d0 <= getAttackReachSq(livingentity)) {
						EntityCustom.this.attackEntityAsMob(livingentity);
					} else if (d0 < 16) {
						Vec3d vec3d = livingentity.getPositionEyes(1);
						EntityCustom.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
					}
				}

				protected double getAttackReachSq(EntityLivingBase attackTarget) {
					return EntityCustom.this.width * 1.5 * EntityCustom.this.height * 1.5 + attackTarget.height;
				}
			});
			this.targetTasks.addTask(7, new EntityAIHurtByTarget(this, false));
			this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.25D, 20, 10.0F));
		}

		@Override
		public EnumCreatureAttribute getCreatureAttribute() {
			return EnumCreatureAttribute.UNDEFINED;
		}

		@Override
		protected boolean canDespawn() {
			return false;
		}

		@Override
		protected Item getDropItem() {
			return new ItemStack(Items.DIAMOND, (int) (1)).getItem();
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("block.note.bell"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public void fall(float l, float d) {
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof EntityArrow)
				return false;
			if (source.getImmediateSource() instanceof EntityPotion)
				return false;
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			if (source == DamageSource.LIGHTNING_BOLT)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void onKillEntity(EntityLivingBase entity) {
			super.onKillEntity(entity);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				ProcedureSkalaThisEntityKillsAnotherOne.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
			if (this.getEntityAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5D);
			if (this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2D);
			if (this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3000D);
			if (this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(30D);
			this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
			this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(2);
		}

		@Override
		public void setSwingingArms(boolean swingingArms) {
		}

		public void attackEntityWithRangedAttack(EntityLivingBase target, float flval) {
			EntityArrowCustom entityarrow = new EntityArrowCustom(this.world, this);
			double d0 = target.posY + (double) target.getEyeHeight() - 1.1;
			double d1 = target.posX - this.posX;
			double d3 = target.posZ - this.posZ;
			entityarrow.shoot(d1, d0 - entityarrow.posY + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.6F, 12.0F);
			this.world.spawnEntity(entityarrow);
		}

		@Override
		public boolean canBreatheUnderwater() {
			return true;
		}

		@Override
		public boolean getCanSpawnHere() {
			return true;
		}

		@Override
		public boolean isNotColliding() {
			return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
		}

		@Override
		public boolean isPushedByWater() {
			return false;
		}

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final BossInfoServer bossInfo = new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.NOTCHED_6);
		@Override
		public void addTrackingPlayer(EntityPlayerMP player) {
			super.addTrackingPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		@Override
		public void removeTrackingPlayer(EntityPlayerMP player) {
			super.removeTrackingPlayer(player);
			this.bossInfo.removePlayer(player);
		}

		@Override
		public void onUpdate() {
			super.onUpdate();
			this.setNoGravity(true);
			this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		public void onLivingUpdate() {
			super.onLivingUpdate();
			int i = (int) this.posX;
			int j = (int) this.posY;
			int k = (int) this.posZ;
			Random random = this.rand;
			if (true)
				for (int l = 0; l < 4; ++l) {
					double d0 = (i + 0.5) + (random.nextFloat() - 0.5) * 2D;
					double d1 = ((j + 0.7) + (random.nextFloat() - 0.5) * 2D * 100) + 0.5D;
					double d2 = (k + 0.5) + (random.nextFloat() - 0.5) * 2D;
					world.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0, 0, 0);
				}
		}
	}

	public static class EntityArrowCustom extends EntityTippedArrow {
		public EntityArrowCustom(World a) {
			super(a);
		}

		public EntityArrowCustom(World worldIn, double x, double y, double z) {
			super(worldIn, x, y, z);
		}

		public EntityArrowCustom(World worldIn, EntityLivingBase shooter) {
			super(worldIn, shooter);
		}
	}
}
