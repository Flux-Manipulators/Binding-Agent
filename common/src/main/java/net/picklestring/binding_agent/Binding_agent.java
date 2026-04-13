package net.picklestring.binding_agent;

import dev.architectury.platform.Platform;
import net.picklestring.binding_agent.debug.BindingAgentDebug;

public final class Binding_agent {
    public static final String MOD_ID = "binding_agent";

    public static void init() {
        if (Platform.isDevelopmentEnvironment()) {
            BindingAgentDebug.init();
        }
    }
}
