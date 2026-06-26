package net.lunacatgirl.binding_agent.api.attachment;

import java.util.Objects;
import java.util.function.Supplier;
import net.minecraft.resources.Identifier;

public final class PlayerAttachmentType<T> {
    private final Identifier id;
    private final Supplier<T> factory;
    private final PlayerAttachmentSerializer<T> serializer;

    PlayerAttachmentType(Identifier id, Supplier<T> factory, PlayerAttachmentSerializer<T> serializer) {
        this.id = Objects.requireNonNull(id, "id");
        this.factory = Objects.requireNonNull(factory, "factory");
        this.serializer = Objects.requireNonNull(serializer, "serializer");
    }

    public Identifier id() {
        return this.id;
    }

    public PlayerAttachmentSerializer<T> serializer() {
        return this.serializer;
    }

    public T createValue() {
        return this.factory.get();
    }
}
