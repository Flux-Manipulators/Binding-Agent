package net.lunacatgirl.binding_agent.internal.attachment;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.lunacatgirl.binding_agent.api.attachment.PlayerAttachmentRegistry;
import net.lunacatgirl.binding_agent.api.attachment.PlayerAttachmentType;

public final class PlayerAttachmentStorage {
    public static final String ROOT_TAG = "binding_agent.attachments";

    private PlayerAttachmentStorage() {
    }

    public static void save(PlayerAttachmentAccess access, ValueOutput output, HolderLookup.Provider registries) {
        CompoundTag attachmentsTag = new CompoundTag();

        for (PlayerAttachmentType<?> type : PlayerAttachmentRegistry.all()) {
            saveAttachment(access, attachmentsTag, registries, type);
        }

        if (!attachmentsTag.isEmpty()) {
            output.store(ROOT_TAG, CompoundTag.CODEC, attachmentsTag);
        }
    }

    public static void load(PlayerAttachmentAccess access, ValueInput input, HolderLookup.Provider registries) {
        CompoundTag attachmentsTag = input.read(ROOT_TAG, CompoundTag.CODEC).orElse(null);
        if (attachmentsTag == null) {
            return;
        }

        for (PlayerAttachmentType<?> type : PlayerAttachmentRegistry.all()) {
            loadAttachment(access, attachmentsTag, registries, type);
        }
    }

    public static void copy(PlayerAttachmentAccess source, PlayerAttachmentAccess target, HolderLookup.Provider registries) {
        for (PlayerAttachmentType<?> type : PlayerAttachmentRegistry.all()) {
            copyAttachment(source, target, registries, type);
        }
    }

    private static <T> void saveAttachment(
        PlayerAttachmentAccess access,
        CompoundTag attachmentsTag,
        HolderLookup.Provider registries,
        PlayerAttachmentType<T> type
    ) {
        T attachment = access.binding_agent$getAttachmentIfPresent(type);
        if (attachment == null) {
            return;
        }

        attachmentsTag.put(type.id().toString(), type.serializer().save(attachment, registries));
    }

    private static <T> void loadAttachment(
        PlayerAttachmentAccess access,
        CompoundTag attachmentsTag,
        HolderLookup.Provider registries,
        PlayerAttachmentType<T> type
    ) {
        String id = type.id().toString();
        CompoundTag attachmentTag = attachmentsTag.getCompound(id).orElse(null);
        if (attachmentTag == null) {
            return;
        }

        T attachment = access.binding_agent$getAttachment(type);
        type.serializer().load(attachment, attachmentTag, registries);
    }

    private static <T> void copyAttachment(
        PlayerAttachmentAccess source,
        PlayerAttachmentAccess target,
        HolderLookup.Provider registries,
        PlayerAttachmentType<T> type
    ) {
        T sourceAttachment = source.binding_agent$getAttachmentIfPresent(type);
        if (sourceAttachment == null) {
            return;
        }

        T targetAttachment = target.binding_agent$getAttachment(type);
        type.serializer().copy(sourceAttachment, targetAttachment, registries);
    }
}
