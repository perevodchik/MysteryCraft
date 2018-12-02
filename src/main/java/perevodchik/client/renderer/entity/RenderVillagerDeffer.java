package perevodchik.client.renderer.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import perevodchik.Main;
import perevodchik.entity.EntityVillagerDeffer;

import javax.annotation.Nullable;

public class RenderVillagerDeffer  extends RenderLiving<EntityVillagerDeffer> {
    private static final ResourceLocation textureLoc = new ResourceLocation(Main.MODID + ":textures/entity/villager_deffer.png");

    public RenderVillagerDeffer(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityVillagerDeffer entity) {
        return textureLoc;
    }
}