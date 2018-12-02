package perevodchik.client.renderer.entity.passive;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import perevodchik.Main;
import perevodchik.entity.EntityDeer;

import javax.annotation.Nullable;

public class RenderDeer extends RenderLiving<EntityDeer> {
    private static final ResourceLocation textureLoc = new ResourceLocation(Main.MODID + ":textures/entity/deer.png");

    public RenderDeer(RenderManager manager, ModelBase model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDeer entity) {
        return textureLoc;
    }
}