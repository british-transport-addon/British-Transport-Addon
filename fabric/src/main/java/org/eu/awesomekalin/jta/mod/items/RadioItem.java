package org.eu.awesomekalin.jta.mod.items;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.ItemExtension;

public class RadioItem extends ItemExtension {

    public RadioItem() {
        super(new ItemSettings().maxCount(1));
    }
}