package liugrape.game.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
	@Inject(at = @At("RETURN"), method = "loadLevel")
	private void init(CallbackInfo ci) {
		// This code will run when the Minecraft server loads a level
		MinecraftServer server = player.server;
		System.out.println("Hello from ExampleMixin!");
	}
}