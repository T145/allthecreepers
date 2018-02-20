package T145.elementalcreepers.common.entities;

import T145.elementalcreepers.common.config.ConfigGeneral;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCookieCreeper extends EntityBaseCreeper {

	public EntityCookieCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		for (int i = 0; i < ConfigGeneral.cookieCreeperAmount; ++i) {
			EntityItem cookie = new EntityItem(world, posX, posY, posZ, new ItemStack(Items.COOKIE));
			cookie.motionY = 0.5D;
			world.spawnEntity(cookie);
		}
	}
}