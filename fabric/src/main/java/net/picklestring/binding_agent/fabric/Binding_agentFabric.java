package net.picklestring.binding_agent.fabric;

import net.picklestring.binding_agent.Binding_agent;
import net.fabricmc.api.ModInitializer;

public final class Binding_agentFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        Binding_agent.init();
    }
}
