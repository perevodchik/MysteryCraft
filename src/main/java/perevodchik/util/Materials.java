package perevodchik.util;

        import net.minecraft.item.Item;
        import net.minecraft.item.ItemStack;
        import net.minecraftforge.common.util.EnumHelper;

public class Materials {
    public static Item.ToolMaterial STAR = EnumHelper.addToolMaterial("Star", 4, 3000, 25.0F, 17, 22).setRepairItem(new ItemStack(Item.getItemById(Item.getIdFromItem(RPGCraftRegistry.StarIgnot))));
    static Item.ToolMaterial SUN_IRON = EnumHelper.addToolMaterial("Sun iron", 4, 3000, 25.0F, 1, 22).setRepairItem(new ItemStack(RPGCraftRegistry.SunIgnot));
    public static Item.ToolMaterial ELECTRUM = EnumHelper.addToolMaterial("Electrum", 4, 1500, 10.0F, 15, 14).setRepairItem(new ItemStack(RPGCraftRegistry.ElectrumIgnot));
    public static Item.ToolMaterial MYFRIL = EnumHelper.addToolMaterial("Myfril", 3, 1234, 10.0F, 10, 14).setRepairItem(new ItemStack(Item.getItemById(Item.getIdFromItem(RPGCraftRegistry.MyfrilIgnot))));
    public static Item.ToolMaterial ORYHALK = EnumHelper.addToolMaterial("Oryhalk", 3, 1234, 10.0F, 10, 10).setRepairItem(new ItemStack(Item.getItemById(Item.getIdFromItem(RPGCraftRegistry.OryhalkIgnot))));
    static Item.ToolMaterial SOUL_STEEL = EnumHelper.addToolMaterial("Soul steel", 4, 3000, 25.0F, 20, 22);
}
