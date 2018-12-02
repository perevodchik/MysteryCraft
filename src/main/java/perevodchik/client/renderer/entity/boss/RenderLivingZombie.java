package perevodchik.client.renderer.entity.boss;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import perevodchik.Main;
import perevodchik.entity.boss.EntityLivingZombie;

import javax.annotation.Nullable;

public class RenderLivingZombie extends RenderLiving<EntityLivingZombie> {
    private static final ResourceLocation textureLoc = new ResourceLocation(Main.MODID + ":textures/entity/living_zombie.png");

    public RenderLivingZombie(RenderManager manager) {
        super(manager, new ModelZombie(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityLivingZombie entity) {
        return textureLoc;
    }
}