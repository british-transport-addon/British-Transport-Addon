package org.eu.awesomekalin.jta.mod.util;

import org.mtr.mapping.holder.Box;
import org.mtr.mapping.holder.Vector3d;

public class BoxUtil {

    public static Box of(Vector3d center, double dx, double dy, double dz) {
        return new Box(center.getXMapped() - dx / 2.0, center.getYMapped() - dy / 2.0, center.getZMapped() - dz / 2.0, center.getXMapped() + dx / 2.0, center.getYMapped() + dy / 2.0, center.getZMapped() + dz / 2.0);
    }
}
