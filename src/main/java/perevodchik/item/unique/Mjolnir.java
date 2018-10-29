package perevodchik.item.unique;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import perevodchik.item.ItemHammer;

public class Mjolnir extends ItemHammer {

    public Mjolnir(ToolMaterial material, CreativeTabs tab, String name) {
        super(material);
        this.setRegistryName("mjolnir");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        return false;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        BlockPos LightingBoltPos = new BlockPos(playerIn);
        BlockPos.MutableBlockPos LighttingBoltMutablePos = new BlockPos.MutableBlockPos(LightingBoltPos);

        EnumFacing face = playerIn.getHorizontalFacing();

        int r = 2 + (int) (Math.random() * 5);
        while(r<2){
            r = 2 + (int) (Math.random() * 5);
        }
        int q = -6 + (int) (Math.random() * 13);


        if(face == EnumFacing.NORTH)
        {
            LighttingBoltMutablePos.setPos(LightingBoltPos.getX()+q, LightingBoltPos.getY(), LightingBoltPos.getZ()-r);
        }
        else if(face == EnumFacing.SOUTH)
        {
            LighttingBoltMutablePos.setPos(LightingBoltPos.getX()+q, LightingBoltPos.getY(), LightingBoltPos.getZ()+r);
        }
        else if(face == EnumFacing.WEST)
        {
            LighttingBoltMutablePos.setPos(LightingBoltPos.getX()-r, LightingBoltPos.getY(), LightingBoltPos.getZ()+q);
        }
        else if(face == EnumFacing.EAST)
        {
            LighttingBoltMutablePos.setPos(LightingBoltPos.getX()+r, LightingBoltPos.getY(), LightingBoltPos.getZ()+q);
        }

        EntityLightningBolt bolt = new EntityLightningBolt(worldIn, LighttingBoltMutablePos.getX(), LighttingBoltMutablePos.getY(), LighttingBoltMutablePos.getZ(), false);
        worldIn.spawnEntity(bolt);
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
}