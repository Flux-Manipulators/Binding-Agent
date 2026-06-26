package net.lunacatgirl.binding_agent.inbuilt;

import com.google.gson.JsonSerializer;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.lunacatgirl.binding_agent.api.attachment.PlayerAttachmentSerializer;

public class IntAttachment {
    public static PlayerAttachmentSerializer<IntAttachment> Serializer;

    private int value;

    public int value() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private static class IntSerializer implements PlayerAttachmentSerializer<IntAttachment> {
        @Override
        public CompoundTag save(IntAttachment attachment, HolderLookup.Provider registries) {
            CompoundTag tag = new CompoundTag();
            tag.putInt("value", attachment.value());
            return tag;
        }

        @Override
        public void load(IntAttachment attachment, CompoundTag tag, HolderLookup.Provider registries) {
            attachment.setValue(tag.getIntOr("value", 0));
        }
    }

    static {
        Serializer = new IntSerializer();
    }
}
