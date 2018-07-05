package mcjty.ariente.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DroneRender extends RenderLiving<Drone> {
    private ResourceLocation mobTexture = new ResourceLocation("ariente:textures/entity/drone.png");
    private ResourceLocation mobShootingTexture = new ResourceLocation("ariente:textures/entity/drone_shooting.png");

    public DroneRender(RenderManager renderManagerIn) {
        super(renderManagerIn, new DroneModel(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(Drone entity) {
        return entity.isAttacking() ? mobShootingTexture : mobTexture;
    }

    public static final DroneRender.Factory FACTORY = new DroneRender.Factory();

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    @Override
    protected void preRenderCallback(Drone entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static class Factory implements IRenderFactory<Drone> {

        @Override
        public Render<? super Drone> createRenderFor(RenderManager manager) {
            return new DroneRender(manager);
        }

    }

}