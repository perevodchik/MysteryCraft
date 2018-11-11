package perevodchik.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import perevodchik.Main;
import perevodchik.client.model.ModelGhost;
import perevodchik.entity.EntityGhost;

@SideOnly(Side.CLIENT)
class RenderGhost extends RenderLiving<EntityGhost>{

    private static final ResourceLocation ghostTexture = new ResourceLocation(Main.MODID + ":textures/entity/ghost.png"); // Initialized it and changed it to private static final

    public RenderGhost(RenderManager rendermanagerIn, ModelGhost model, float shadowSize) {
        super(rendermanagerIn, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGhost entity) {
        return this.ghostTexture == null ? new ResourceLocation(Main.MODID + ":textures/entity/ghost") : this.ghostTexture; //Added this
    }
}