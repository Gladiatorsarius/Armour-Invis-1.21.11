package net.mcreator.swordssmp.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;

import net.mcreator.swordssmp.init.SwordssmpModItems;

public class DescendingDarkRightclickedProcedure {
	public static InteractionResult execute(LevelAccessor world, Entity entity, InteractionHand hand) {
		if (entity == null)
			return InteractionResult.PASS;
		if (!(entity instanceof Player player))
			return InteractionResult.PASS;
		ItemStack mainHandStack = player.getMainHandItem();
		if (mainHandStack.getItem() != SwordssmpModItems.DESCENDING_DARK)
			return InteractionResult.PASS;
		CustomData customData = mainHandStack.get(DataComponents.CUSTOM_DATA);
		CompoundTag cooldownTag = customData != null ? customData.copyTag() : new CompoundTag();
		boolean tagChanged = false;
		String ownerId = cooldownTag.getString("DescendingDarkCooldownOwner").orElse("");
		if (!ownerId.isEmpty() && !ownerId.equals(player.getStringUUID())) {
			cooldownTag.remove("DescendingDarkCooldownUntil");
			cooldownTag.remove("DescendingDarkCooldownOwner");
			tagChanged = true;
		}
		long now = 0L;
		if (world instanceof Level _level) {
			now = _level.getGameTime();
		}
		long cooldownUntil = cooldownTag.getLong("DescendingDarkCooldownUntil").orElse(0L);
		if (cooldownUntil > 0L) {
			if (now < cooldownUntil)
				return InteractionResult.FAIL;
			cooldownTag.remove("DescendingDarkCooldownUntil");
			cooldownTag.remove("DescendingDarkCooldownOwner");
			tagChanged = true;
		}
		if (tagChanged)
			mainHandStack.set(DataComponents.CUSTOM_DATA, CustomData.of(cooldownTag));
		if (player.getCooldowns().isOnCooldown(mainHandStack))
			return InteractionResult.FAIL;
		player.startUsingItem(hand);
		return InteractionResult.CONSUME;
	}
}
