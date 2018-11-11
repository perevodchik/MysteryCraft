package perevodchik.enums;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import java.util.Random;

public class TradeList implements EntityVillager.ITradeList {

    private ItemStack stackToBuy;
    private ItemStack stackToPayFirst;
    private ItemStack stackToPaySecond;
    private EntityVillager.PriceInfo price;

    public TradeList(ItemStack stackToBuy, ItemStack stackToPay, int priceFrom, int priceTo){
        this.stackToBuy = stackToBuy;
        this.stackToPayFirst = stackToPay;
        this.price = new EntityVillager.PriceInfo(priceFrom, priceTo);
    }

    /*
    public TradeList(ItemStack stackToBuy, ItemStack stackToPayFirst, ItemStack stackToPaySecond, int priceFrom, int priceTo){
     this.stackToBuy = stackToBuy;
     this.stackToPayFirst = stackToPayFirst;
     this.stackToPaySecond = stackToPaySecond;
     this.price = new EntityVillager.PriceInfo(priceFrom, priceTo);
    }
    */

    @Override
    public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
        int actualPrice = 1;

        if (price != null)
        {
            actualPrice = price.getPrice(random);
        }

        ItemStack stackToPay = new ItemStack(Items.EMERALD, actualPrice, 0);

        /*
        if(!stackToPaySecond.isEmpty()) {
            recipeList.add(new MerchantRecipe(stackToPayFirst, stackToPaySecond, stackToBuy));
        } else
        */
        recipeList.add(new MerchantRecipe(stackToPay, stackToBuy));
    }

}
