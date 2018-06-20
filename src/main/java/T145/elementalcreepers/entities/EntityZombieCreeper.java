package T145.elementalcreepers.entities;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityZombieCreeper extends EntityBaseCreeper {

	private int creeperCount;

	public EntityZombieCreeper(World world) {
		super(world);
	}

	public void addCreeper() {
		++creeperCount;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setInteger("CreeperCount", creeperCount);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		creeperCount = tag.getInteger("CreeperCount");
	}

	@Override
	public void detonate() {
		if (creeperCount == 0) { // blow up like a normal creeper
			float f = getPowered() ? 2.0F : 1.0F;
			world.createExplosion(this, posX, posY, posZ, 3 * f, ForgeEventFactory.getMobGriefingEvent(world, this));
		} else {
			if (getPowered()) {
				creeperCount *= 2;
			}

			for (int k = 0; k < creeperCount; ++k) {
				float f = ((k % 2) - 0.5F) * 1 / 4.0F;
				float f1 = ((k / 2) - 0.5F) * 1 / 4.0F;

				EntityLivingBase creeper = (EntityLivingBase) ElementalCreepers.CREEPER_REGISTRY.get(rand.nextInt(ElementalCreepers.CREEPER_REGISTRY.size())).newInstance(world);

				if (hasCustomName()) {
					creeper.setCustomNameTag(getCustomNameTag());
				}

				creeper.setRevengeTarget(getAttackTarget());
				creeper.motionX = (0.8F * (rand.nextBoolean() ? 1 : -1));
				creeper.motionY = 0.5D;
				creeper.motionZ = (0.8F * (rand.nextBoolean() ? 1 : -1));

				creeper.setLocationAndAngles(posX + f, posY + 0.5D, posZ + f1, rand.nextFloat() * 360.0F, 0.0F);
				world.spawnEntity(creeper);
			}
		}
	}
}