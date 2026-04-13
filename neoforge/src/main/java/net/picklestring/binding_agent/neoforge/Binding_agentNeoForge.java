package net.picklestring.binding_agent.neoforge;

import net.picklestring.binding_agent.Binding_agent;
import net.neoforged.fml.common.Mod;

@Mod(Binding_agent.MOD_ID)
public final class Binding_agentNeoForge {
    public Binding_agentNeoForge() {
        // Run our common setup.
        Binding_agent.init();
    }
}
