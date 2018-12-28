package perevodchik.quest;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class QuestData {
    public static Item[] itemList = new Item[]{Items.EMERALD, Items.CARROT, Items.WHEAT, Items.GOLD_INGOT, Items.BONE, Items.BOW, Items.IRON_INGOT, Items.GOLDEN_AXE};
    /*public static Class[] classList = new Class[] {
            EntityZombie.class, EntitySkeleton.class, EntityCreeper.class,
            EntityZombie.class, EntityPigZombie.class, EntityDeer.class,
            EntitySpider.class, EntityCaveSpider.class, EntitySlime.class};*/
    public static Class[] classList = new Class[] {
            EntityZombie.class, EntitySkeleton.class, EntityCreeper.class,
            EntityZombie.class, EntityWitherSkeleton.class, EntityDragon.class};
}