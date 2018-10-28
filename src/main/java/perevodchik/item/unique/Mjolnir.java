package perevodchik.item.unique;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import perevodchik.item.ItemHammer;

public class Mjolnir extends ItemHammer {

    public Mjolnir(ToolMaterial material, CreativeTabs tab, String name) {
        super(material);
        this.setRegistryName("mjolnir");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        return false;
    }
}