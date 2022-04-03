package daniking.reforged.client;

import daniking.reforged.Reforged;
import daniking.reforged.entity.JavelinEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class JavelinEntityRenderer <T extends JavelinEntity> extends EntityRenderer<T> {

    private static final Identifier TEXTURE = new Identifier(Reforged.ID, "textures/entity/javelin.png");
    private final ItemRenderer renderer;

    protected JavelinEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.renderer = ctx.getItemRenderer();
    }

    @Override
    public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        final ItemStack stack = entity.getJavelinStack();
        if (!stack.isEmpty()) {
            matrices.scale(2.0F, 2.0F, 2.0F);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(tickDelta, (entity).prevYaw, (entity).getYaw()) - 90.0F));
            matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(tickDelta, (entity).prevPitch, ((entity).getPitch()))));
            float shake = (float)entity.shake - tickDelta;
            if (shake > 0.0F) {
                float t = -MathHelper.sin(shake * 3.0F) * shake;
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(t));
            }
            matrices.multiply(Vec3f.NEGATIVE_Z.getDegreesQuaternion(140.0F));
            this.renderer.renderItem(stack, ModelTransformation.Mode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers,0);
        }
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(T entity) {
        return TEXTURE;
    }
}
