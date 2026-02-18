package twox.luminnautic.command;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import twox.luminnautic.Luminnautic;

public final class NauticDepthsCommand {
	private static final ResourceKey<Level> NAUTIC_DEPTHS_WORLD = ResourceKey.create(
		Registries.DIMENSION,
		new ResourceLocation(Luminnautic.MOD_ID, "nautic_depths")
	);
	private static final SimpleCommandExceptionType MISSING_DIMENSION = new SimpleCommandExceptionType(
		Component.literal("Dimension luminnautic:nautic_depths is unavailable.")
	);

	public static void register() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(
			literal("nauticdepths")
				.requires(source -> source.hasPermission(2))
				.executes(context -> teleport(context.getSource(), 0.0, 120.0, 0.0))
				.then(argument("x", DoubleArgumentType.doubleArg())
					.then(argument("y", DoubleArgumentType.doubleArg())
						.then(argument("z", DoubleArgumentType.doubleArg())
							.executes(context -> teleport(
								context.getSource(),
								DoubleArgumentType.getDouble(context, "x"),
								DoubleArgumentType.getDouble(context, "y"),
								DoubleArgumentType.getDouble(context, "z")
							))
						)
					)
				)
		));
	}

	private static int teleport(CommandSourceStack source, double x, double y, double z) throws CommandSyntaxException {
		ServerPlayer player = source.getPlayerOrException();
		ServerLevel target = source.getServer().getLevel(NAUTIC_DEPTHS_WORLD);
		if (target == null) {
			throw MISSING_DIMENSION.create();
		}

		player.teleportTo(target, x, y, z, player.getYRot(), player.getXRot());
		source.sendSuccess(() -> Component.literal("Teleported to Nautic Depths: " + x + " " + y + " " + z), false);
		return Command.SINGLE_SUCCESS;
	}
}
