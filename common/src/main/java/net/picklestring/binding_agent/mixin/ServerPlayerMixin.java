package net.picklestring.binding_agent.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.picklestring.binding_agent.internal.attachment.PlayerAttachmentAccess;
import net.picklestring.binding_agent.internal.attachment.PlayerAttachmentStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Inject(method = "restoreFrom", at = @At("TAIL"))
    private void binding_agent$copyAttachments(ServerPlayer oldPlayer, boolean wonGame, CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        PlayerAttachmentStorage.copy((PlayerAttachmentAccess) oldPlayer, (PlayerAttachmentAccess) self, self.registryAccess());
    }
}
