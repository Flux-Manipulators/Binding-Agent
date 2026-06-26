package net.lunacatgirl.binding_agent.inbuilt;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.lunacatgirl.binding_agent.api.attachment.PlayerAttachmentSerializer;

public class DoubleAttachment {
    public static PlayerAttachmentSerializer<DoubleAttachment> Serializer;

    private double value;

    public double value() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    private static class DoubleSerializer implements PlayerAttachmentSerializer<DoubleAttachment> {
        @Override
        public CompoundTag save(DoubleAttachment attachment, HolderLookup.Provider registries) {
            CompoundTag tag = new CompoundTag();
            tag.putDouble("value", attachment.value());
            return tag;
        }

        @Override
        public void load(DoubleAttachment attachment, CompoundTag tag, HolderLookup.Provider registries) {
            attachment.setValue(tag.getDoubleOr("value", 0.0d));
        }
    }

    static {
        Serializer = new DoubleSerializer();
    }
}
