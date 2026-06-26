package net.lunacatgirl.binding_agent.api.attachment;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public interface PlayerAttachmentSerializer<T> {
    CompoundTag save(T attachment, HolderLookup.Provider registries);

    void load(T attachment, CompoundTag tag, HolderLookup.Provider registries);

    default void copy(T source, T target, HolderLookup.Provider registries) {
        load(target, save(source, registries), registries);
    }
}
