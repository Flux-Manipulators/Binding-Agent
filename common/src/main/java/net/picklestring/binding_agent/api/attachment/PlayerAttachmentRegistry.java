package net.picklestring.binding_agent.api.attachment;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import net.minecraft.resources.Identifier;

public final class PlayerAttachmentRegistry {
    private static final Map<Identifier, PlayerAttachmentType<?>> ATTACHMENTS = new LinkedHashMap<>();

    private PlayerAttachmentRegistry() {
    }

    public static <T> PlayerAttachmentType<T> register(
        String namespace,
        String path,
        Supplier<T> factory,
        PlayerAttachmentSerializer<T> serializer
    ) {
        return register(Identifier.fromNamespaceAndPath(namespace, path), factory, serializer);
    }

    public static synchronized <T> PlayerAttachmentType<T> register(
        Identifier id,
        Supplier<T> factory,
        PlayerAttachmentSerializer<T> serializer
    ) {
        Objects.requireNonNull(id, "id");
        if (ATTACHMENTS.containsKey(id)) {
            throw new IllegalStateException("Duplicate player attachment id: " + id);
        }

        PlayerAttachmentType<T> type = new PlayerAttachmentType<>(id, factory, serializer);
        ATTACHMENTS.put(id, type);
        return type;
    }

    public static Collection<PlayerAttachmentType<?>> all() {
        return Collections.unmodifiableCollection(ATTACHMENTS.values());
    }
}
