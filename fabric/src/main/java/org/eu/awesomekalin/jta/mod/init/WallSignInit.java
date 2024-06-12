package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.wallsigns.DangerElectricShock;
import org.mtr.mapping.holder.Block;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.BlockRegistryObject;

public class WallSignInit {
    public static final BlockRegistryObject DANGER_ELECTRIC_SHOCK = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "danger_electric_shock_wall_sign"), () -> new Block(new DangerElectricShock()), CreativeTabInit.JTA_WALL_SIGNS);
    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Wall Signs");
    }
}
