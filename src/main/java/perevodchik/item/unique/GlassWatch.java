package perevodchik.item.unique;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import perevodchik.enums.RarityItemsList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class GlassWatch extends Item {

    public GlassWatch(String name, CreativeTabs tab){
        this.canRepair = false;
        this.setMaxStackSize(1);
        this.setMaxDamage(300);
        this.setRegistryName("glass_watch");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase player, EntityLivingBase Entity) {
        par1ItemStack.damageItem(1, Entity);
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        int hp = stack.getMaxDamage() - stack.getItemDamage();
        tooltip.add("§2Кишеньковий годинник брата Кронуса, який може змінювати день на ніч чи навпаки");
        tooltip.add("§2Користуватися з обережністю, раптом щось станеться...");
        tooltip.add("§2Прочність: " + hp + "/" + stack.getMaxDamage());
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ((EntityPlayerSP) playerIn).sendChatMessage("Гравець використав силу Кронуса, й змінив час...");
        ((EntityPlayerSP) playerIn).sendChatMessage(String.valueOf(worldIn.getTotalWorldTime()));

        if(worldIn.getWorldTime() > 0 && worldIn.getWorldTime() < 12200) {
            worldIn.setWorldTime(15000);
        } else if (worldIn.getWorldTime() > 12200){
            worldIn.setWorldTime(24000);
        }


        ItemStack stack = playerIn.getHeldItem(handIn);
        stack.damageItem(2, playerIn);
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return RarityItemsList.RARITY_LEGENDARY;
    }

}
