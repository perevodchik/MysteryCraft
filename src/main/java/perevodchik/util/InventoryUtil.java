package perevodchik.util;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;

public class InventoryUtil {

    public static int getCountItemInInv(Item item, InventoryPlayer inv) {
        int t = 0;
        for(int c = 0; c < inv.getSizeInventory(); c++) {
            if(inv.getStackInSlot(c).item == item) {
                t += inv.getStackInSlot(c).stackSize;
            }
        }
        return t;
    }

    public static boolean removeItemsFromInv(Item item, int count, InventoryPlayer inv) {
        int t = count;
        int c = 0;

        while(t > 0) {
            if(inv.getStackInSlot(c).item == item) {
                if(inv.getStackInSlot(c).stackSize >= t) {
                    inv.getStackInSlot(c).shrink(t);
                    return true;
                } else {
                    t -= inv.getStackInSlot(c).stackSize;
                    inv.removeStackFromSlot(c);
                }
            }
            c++;
        }
        return true;
    }

}
