package net.mcreator.swordssmp.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;

import net.mcreator.swordssmp.init.SwordssmpModItems;

public class DescendingDarkReleaseUsingProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof Player player))
			return;

		boolean needsReset = false;
		if (player.getAttribute(Attributes.GRAVITY) != null) {
			if (player.getAttribute(Attributes.GRAVITY).getBaseValue() != player.getAttribute(Attributes.GRAVITY).getAttribute().value().getDefaultValue()) {
				needsReset = true;
				player.getAttribute(Attributes.GRAVITY).setBaseValue(player.getAttribute(Attributes.GRAVITY).getAttribute().value().getDefaultValue());
			}
		}
		if (player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER) != null) {
			if (player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).getBaseValue() != player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).getAttribute().value().getDefaultValue()) {
				needsReset = true;
				player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).setBaseValue(player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).getAttribute().value().getDefaultValue());
			}
		}
		if (needsReset) {
			ItemStack mainHandStack = player.getMainHandItem();
			if (mainHandStack.getItem() == SwordssmpModItems.DESCENDING_DARK) {
				CustomData customData = mainHandStack.get(DataComponents.CUSTOM_DATA);
				CompoundTag cooldownTag = customData != null ? customData.copyTag() : new CompoundTag();
				long now = 0L;
				if (world instanceof Level _level) {
					now = _level.getGameTime();
				}
				cooldownTag.putLong("DescendingDarkCooldownUntil", now + 200L);
				cooldownTag.putString("DescendingDarkCooldownOwner", player.getStringUUID());
				mainHandStack.set(DataComponents.CUSTOM_DATA, CustomData.of(cooldownTag));
				if (!world.isClientSide()) {
					player.getCooldowns().addCooldown(mainHandStack, 200);
				}
			}
		}
	}
}
