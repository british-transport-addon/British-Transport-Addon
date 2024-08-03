package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.InitClient;
import org.lwjgl.glfw.GLFW;
import org.mtr.mapping.holder.KeyBinding;

public final class KeyBindingsInit {

	public static final KeyBinding TALK_RADIO = InitClient.REGISTRY_CLIENT.registerKeyBinding("jta.key.talk_radio", GLFW.GLFW_KEY_B, "jta.category");;

	public static void init() {
		Init.LOGGER.info("Registering British Transit Addon key bindings");
	}
}