package net.picklestring.binding_agent.internal.attachment;

import net.picklestring.binding_agent.api.attachment.PlayerAttachmentType;

public interface PlayerAttachmentAccess {
    <T> T binding_agent$getAttachment(PlayerAttachmentType<T> type);

    <T> T binding_agent$getAttachmentIfPresent(PlayerAttachmentType<T> type);
}
