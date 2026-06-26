package net.picklestring.binding_agent.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.picklestring.binding_agent.api.attachment.PlayerAttachments;
import net.picklestring.binding_agent.debug.BindingAgentDebug;
import net.picklestring.binding_agent.inbuilt.IntAttachment;

public final class BindingAgentTestCommand {
    private BindingAgentTestCommand() {
    }

    public static void init() {
        CommandRegistrationEvent.EVENT.register((dispatcher, registry, selection) -> dispatcher.register(
            Commands.literal("binding_agent")
                .then(Commands.literal("attachment")
                    .then(Commands.literal("get")
                        .executes(context -> getMana(context.getSource()))
                    )
                    .then(Commands.literal("set")
                        .then(Commands.argument("value", IntegerArgumentType.integer())
                            .executes(context -> setMana(
                                context.getSource(),
                                IntegerArgumentType.getInteger(context, "value")
                            ))
                        )
                    )
                )
        ));
    }

    private static int getMana(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        IntAttachment attachment = PlayerAttachments.get(player, BindingAgentDebug.MANA);
        int value = attachment.value();
        source.sendSuccess(() -> Component.literal("Mana attachment value: " + value), false);
        return value;
    }

    private static int setMana(CommandSourceStack source, int value) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        IntAttachment attachment = PlayerAttachments.get(player, BindingAgentDebug.MANA);
        attachment.setValue(value);
        source.sendSuccess(() -> Component.literal("Set mana attachment to " + value), false);
        return value;
    }
}
