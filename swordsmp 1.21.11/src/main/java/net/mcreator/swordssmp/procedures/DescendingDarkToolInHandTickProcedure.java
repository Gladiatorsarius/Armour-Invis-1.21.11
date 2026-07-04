package net.mcreator.swordssmp.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Entity;

import net.mcreator.swordssmp.init.SwordssmpModItems;

public class DescendingDarkToolInHandTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof Player player))
			return;

		ItemStack mainHand = player.getMainHandItem();
		if (mainHand.getItem() != SwordssmpModItems.DESCENDING_DARK || !player.isUsingItem()) {
			if (player.getAttribute(Attributes.GRAVITY) != null) {
				player.getAttribute(Attributes.GRAVITY).setBaseValue(player.getAttribute(Attributes.GRAVITY).getAttribute().value().getDefaultValue());
			}
			if (player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER) != null) {
				player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).setBaseValue(player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).getAttribute().value().getDefaultValue());
			}
		}
	}
}
