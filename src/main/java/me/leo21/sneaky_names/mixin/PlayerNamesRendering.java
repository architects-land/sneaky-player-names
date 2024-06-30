package me.leo21.sneaky_names.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class PlayerNamesRendering<T extends Entity> {
	@Inject(at = @At("HEAD"), method = "render", cancellable = true)
	private void renderLabelOrNot(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
		if (!(entity instanceof PlayerEntity)) { return; }

		PlayerEntity clientPlayer = MinecraftClient.getInstance().player;

		if (clientPlayer == null) { return; }
		if (entity == clientPlayer) { return; }

		double maxDistance = 50;
		if (clientPlayer.squaredDistanceTo(entity) > Math.pow(maxDistance, 2)) {
			ci.cancel();
			return;
		}

		World world = clientPlayer.getWorld();

		BlockHitResult hitResult = world.raycast(new RaycastContext(
				clientPlayer.getCameraPosVec(tickDelta),
				entity.getEyePos(),
				RaycastContext.ShapeType.OUTLINE,
				RaycastContext.FluidHandling.NONE,
				clientPlayer
		));

		if (hitResult.getType() == HitResult.Type.BLOCK) {
			ci.cancel();
		}
	}
}