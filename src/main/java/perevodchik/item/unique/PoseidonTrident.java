package perevodchik.item.unique;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import perevodchik.item.ItemLance;

public class PoseidonTrident extends ItemLance {

    public PoseidonTrident(ToolMaterial material, CreativeTabs tab, String name) {
        super(material);
        this.setRegistryName("poseidon_trident");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        Minecraft.getMinecraft().world.setBlockState(target.getPosition(), Blocks.FROSTED_ICE.getDefaultState());
        stack.damageItem(1, attacker);
        return true;
    }
}
