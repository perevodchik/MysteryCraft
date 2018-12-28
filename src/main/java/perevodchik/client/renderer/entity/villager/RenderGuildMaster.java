package perevodchik.client.renderer.entity.villager;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import perevodchik.entity.villager.EntityGuildMaster;

import javax.annotation.Nullable;

public class RenderGuildMaster extends RenderLiving<EntityGuildMaster> {

    public RenderGuildMaster(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelVillager(0.0F), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityGuildMaster entity) {
        return null;
    }
}
