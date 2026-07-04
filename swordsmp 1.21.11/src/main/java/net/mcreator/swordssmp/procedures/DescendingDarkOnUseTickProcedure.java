package net.mcreator.swordssmp.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Entity;

public class DescendingDarkOnUseTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof Player player))
			return;

		if (player.onGround()) {
			if (player.getAttribute(Attributes.GRAVITY) != null) {
				player.getAttribute(Attributes.GRAVITY).setBaseValue(player.getAttribute(Attributes.GRAVITY).getAttribute().value().getDefaultValue());
			}
			if (player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER) != null) {
				player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).setBaseValue(player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).getAttribute().value().getDefaultValue());
			}
		} else {
			if (player.getAttribute(Attributes.GRAVITY) != null) {
				double current = player.getAttribute(Attributes.GRAVITY).getBaseValue();
				player.getAttribute(Attributes.GRAVITY).setBaseValue(current + 0.001);
			}
			if (player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER) != null) {
				player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).setBaseValue(0.0);
			}
		}
	}
}
