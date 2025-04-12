package dev.andante.tpworld

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.command.argument.DimensionArgumentType
import net.minecraft.server.command.CommandManager.argument
import net.minecraft.server.command.CommandManager.literal

class Tpworld : ModInitializer {

    override fun onInitialize() {
        CommandRegistrationCallback.EVENT.register { dispatcher, registry, environment ->
            dispatcher.register(
                literal("tpworld")
                    .requires { it.hasPermissionLevel(2) }
                    .then(
                        argument("world", DimensionArgumentType.dimension())
                            .executes { context ->
                                val entity = context.source.entityOrThrow
                                val dimension = DimensionArgumentType.getDimensionArgument(context, "world")
                                entity.teleport(dimension, entity.x, entity.y, entity.z, setOf(), entity.yaw, entity.pitch, false)
                                1
                            }
                    )
            )
        }
    }
}
