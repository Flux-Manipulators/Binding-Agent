package net.picklestring.binding_agent.debug;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.picklestring.binding_agent.Binding_agent;
import net.picklestring.binding_agent.api.attachment.PlayerAttachmentRegistry;
import net.picklestring.binding_agent.api.attachment.PlayerAttachmentSerializer;
import net.picklestring.binding_agent.api.attachment.PlayerAttachmentType;
import net.picklestring.binding_agent.command.BindingAgentTestCommand;
import net.picklestring.binding_agent.test.TestIntAttachment;

public final class BindingAgentDebug {
    public static final PlayerAttachmentType<TestIntAttachment> MANA = PlayerAttachmentRegistry.register(
        Binding_agent.MOD_ID,
        "mana",
        TestIntAttachment::new,
        new PlayerAttachmentSerializer<>() {
            @Override
            public CompoundTag save(TestIntAttachment attachment, HolderLookup.Provider registries) {
                CompoundTag tag = new CompoundTag();
                tag.putInt("value", attachment.value());
                return tag;
            }

            @Override
            public void load(TestIntAttachment attachment, CompoundTag tag, HolderLookup.Provider registries) {
                attachment.setValue(tag.getIntOr("value", 0));
            }
        }
    );

    private BindingAgentDebug() {
    }

    public static void init() {
        BindingAgentTestCommand.init();
    }
}
