package perevodchik.enums;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import java.util.Objects;

public class MaterialList {
    static Item.ToolMaterial STAR = Objects.requireNonNull(EnumHelper.addToolMaterial("Star", 4, 3000, 12.0F, 11, 22)).setRepairItem(new ItemStack(Item.getItemById(Item.getIdFromItem(ObjList.StarIngot))));
    static Item.ToolMaterial SUN_IRON = Objects.requireNonNull(EnumHelper.addToolMaterial("Sun iron", 4, 3000, 12.0F, 11, 22)).setRepairItem(new ItemStack(ObjList.SunIngot));
    public static Item.ToolMaterial ELECTRUM = Objects.requireNonNull(EnumHelper.addToolMaterial("Electrum", 4, 1500, 10.0F, 11, 14)).setRepairItem(new ItemStack(ObjList.ElectrumIngot));
    public static Item.ToolMaterial MYFRIL = Objects.requireNonNull(EnumHelper.addToolMaterial("Myfril", 3, 1234, 10.0F, 11, 14)).setRepairItem(new ItemStack(Item.getItemById(Item.getIdFromItem(ObjList.MyfrilIngot))));
    public static Item.ToolMaterial ORYHALK = Objects.requireNonNull(EnumHelper.addToolMaterial("Oryhalk", 3, 1234, 10.0F, 11, 10)).setRepairItem(new ItemStack(Item.getItemById(Item.getIdFromItem(ObjList.OryhalkIngot))));
    static Item.ToolMaterial SOUL_STEEL = EnumHelper.addToolMaterial("Soul steel", 4, 3000, 14.0F, 13, 22);
}
