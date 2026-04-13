package net.picklestring.binding_agent.mixin;

import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.picklestring.binding_agent.api.attachment.PlayerAttachmentType;
import net.picklestring.binding_agent.internal.attachment.PlayerAttachmentAccess;
import net.picklestring.binding_agent.internal.attachment.PlayerAttachmentStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin implements PlayerAttachmentAccess {
    @Unique
    private final Map<PlayerAttachmentType<?>, Object> binding_agent$attachments = new IdentityHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> T binding_agent$getAttachment(PlayerAttachmentType<T> type) {
        return (T) this.binding_agent$attachments.computeIfAbsent(type, ignored -> type.createValue());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T binding_agent$getAttachmentIfPresent(PlayerAttachmentType<T> type) {
        return (T) this.binding_agent$attachments.get(type);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void binding_agent$saveAttachments(ValueOutput output, CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        PlayerAttachmentStorage.save(this, output, self.registryAccess());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void binding_agent$loadAttachments(ValueInput input, CallbackInfo ci) {
        PlayerAttachmentStorage.load(this, input, input.lookup());
    }
}
