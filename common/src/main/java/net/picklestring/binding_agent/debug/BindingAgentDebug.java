package net.picklestring.binding_agent.debug;

import net.picklestring.binding_agent.Binding_agent;
import net.picklestring.binding_agent.api.attachment.PlayerAttachmentRegistry;
import net.picklestring.binding_agent.api.attachment.PlayerAttachmentType;
import net.picklestring.binding_agent.command.BindingAgentTestCommand;
import net.picklestring.binding_agent.inbuilt.IntAttachment;

public final class BindingAgentDebug {
    public static final PlayerAttachmentType<IntAttachment> MANA = PlayerAttachmentRegistry.register(
        Binding_agent.MOD_ID,
        "mana",
        IntAttachment::new,
        IntAttachment.Serializer
    );

    private BindingAgentDebug() {
    }

    public static void init() {
        BindingAgentTestCommand.init();
    }
}
