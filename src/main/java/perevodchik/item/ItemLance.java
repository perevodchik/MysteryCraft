package perevodchik.item;

import net.minecraft.item.Item;

public class ItemLance extends Item {
    private final Item.ToolMaterial material;

    public ItemLance(ToolMaterial material) {
        this.material = material;
    }
}
