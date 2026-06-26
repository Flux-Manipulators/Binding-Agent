package net.lunacatgirl.binding_agent.api.attachment;

import net.minecraft.world.entity.player.Player;
import net.lunacatgirl.binding_agent.internal.attachment.PlayerAttachmentAccess;

public final class PlayerAttachments {
    private PlayerAttachments() {
    }

    public static <T> T get(Player player, PlayerAttachmentType<T> type) {
        return access(player).binding_agent$getAttachment(type);
    }

    public static <T> boolean has(Player player, PlayerAttachmentType<T> type) {
        return access(player).binding_agent$getAttachmentIfPresent(type) != null;
    }

    private static PlayerAttachmentAccess access(Player player) {
        return (PlayerAttachmentAccess) player;
    }
}
