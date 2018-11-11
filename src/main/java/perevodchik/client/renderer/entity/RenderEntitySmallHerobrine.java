package perevodchik.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import perevodchik.Main;
import perevodchik.entity.EntitySmallHerobrine;

import javax.annotation.Nonnull;

public class RenderEntitySmallHerobrine extends RenderLiving<EntitySmallHerobrine> {
    /*Расположение текстуры моба(создаём новую папку в текстурах, имя её entity, туда и кладём наши текстуры)*/
    private ResourceLocation mobTexture = new ResourceLocation(Main.MODID + ":textures/entity/skin_herobrine.png");

    /*
    Конструктор рендера,
    теперь о super:
        1 параметр - наш RenderManager,
        2 параметр - наша модель,
        3 параметр - размер тени(стандартно 0.5F)
    */
    public RenderEntitySmallHerobrine(RenderManager manager) {
        super(manager, new ModelBiped(), 0.5F);
    }

    public static Factory FACTORY = new Factory();

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntitySmallHerobrine entity) {
        /*Возвращаем текстуру моба*/
        return mobTexture;
    }

    /*--------->НАШ РЕНДЕР ФЭКТОРИ <---------*/
    public static class Factory implements IRenderFactory<EntitySmallHerobrine> {
        @Override
        public Render<? super EntitySmallHerobrine> createRenderFor(RenderManager manager) {
            /*И наконец-то из всего этого создаём рендер*/
            return new RenderEntitySmallHerobrine(manager);
        }
    }
}