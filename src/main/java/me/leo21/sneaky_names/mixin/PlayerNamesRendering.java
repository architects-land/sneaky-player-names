package me.leo21.sneaky_names.mixin;

import me.leo21.sneaky_names.SneakyPlayerNames;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
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

		MinecraftClient mc = MinecraftClient.getInstance();

		PlayerEntity clientPlayer = mc.player;

		if (clientPlayer == null) { return; }
		if (entity == clientPlayer) { return; }

		int maxDistance = SneakyPlayerNames.CONFIG.MAX_DISTANCE;
		if (maxDistance == 0 ||
				(maxDistance > 0 && clientPlayer.squaredDistanceTo(entity) > Math.pow(maxDistance, 2))
		) {
			ci.cancel();
			return;
		}

		if (!SneakyPlayerNames.CONFIG.HIDE_PLAYER_NAMES) { return; }

		World world = clientPlayer.getWorld();
		Camera camera = mc.gameRenderer.getCamera();

		BlockHitResult hitResult = world.raycast(new RaycastContext(
				camera.getPos(),
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