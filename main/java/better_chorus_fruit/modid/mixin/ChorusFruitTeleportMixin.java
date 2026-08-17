package better_chorus_fruit.modid.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.consume.TeleportRandomlyConsumeEffect;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TeleportRandomlyConsumeEffect.class)
public class ChorusFruitTeleportMixin {
	private static final int OVERWORLD_VOID_TOP = -128;
	private static final int OVERWORLD_BUILD_LIMIT = 320;
	private static final int NETHER_VOID_TOP = -64;
	private static final int NETHER_BUILD_LIMIT = 128;
	private static final int END_VOID_TOP = -64;
	private static final int END_BUILD_LIMIT = 256;

	@Redirect(
		method = "onConsume",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;teleport(DDDZ)Z")
	)
	private boolean betterChorusFruit$redirectTeleport(LivingEntity entity, double x, double y, double z, boolean discardFallingBlocks) {
		if (entity instanceof PlayerEntity) {
			int minY;
			int maxY;
			if (entity.getEntityWorld().getRegistryKey() == World.OVERWORLD) {
				minY = OVERWORLD_VOID_TOP;
				maxY = OVERWORLD_BUILD_LIMIT;
			} else if (entity.getEntityWorld().getRegistryKey() == World.NETHER) {
				minY = NETHER_VOID_TOP;
				maxY = NETHER_BUILD_LIMIT;
			} else if (entity.getEntityWorld().getRegistryKey() == World.END) {
				minY = END_VOID_TOP;
				maxY = END_BUILD_LIMIT;
			} else {
				return entity.teleport(x, y, z, discardFallingBlocks);
			}
			if (entity.getY() <= minY) {
				y = entity.getRandom().nextInt(maxY - minY + 1) + minY;
			}
		}
		return entity.teleport(x, y, z, discardFallingBlocks);
	}
}
