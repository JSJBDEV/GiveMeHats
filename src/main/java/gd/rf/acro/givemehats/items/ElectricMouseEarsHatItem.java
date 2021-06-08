package gd.rf.acro.givemehats.items;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.client.TrinketRenderer;
import gd.rf.acro.givemehats.GiveMeHats;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;


public class ElectricMouseEarsHatItem extends TrinketItem implements TrinketRenderer {


    public ElectricMouseEarsHatItem(Settings settings) {
        super(settings);
        
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("text.emousehat"));
    }


    

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if(entity.isSneaking() && entity.world.isRaining())
        {
            List<MobEntity> entities = entity.getEntityWorld().getEntitiesByClass(MobEntity.class,new Box(entity.getX()-15,entity.getY()-15,entity.getZ(),entity.getX()+15,entity.getY()+15,entity.getZ()+15), LivingEntity::isAlive);
            entities.forEach(entityq ->
            {
                if(RandomUtils.nextInt(0,100)==0 && entityq instanceof Monster)
                {
                    LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT,entityq.world);
                    lightningEntity.teleport(entityq.getX(),entityq.getY(),entityq.getZ());
                    entityq.getEntityWorld().spawnEntity(lightningEntity);
                }
            });
        }
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        GiveMeHats.translateToFace(matrixStack,contextModel,entity,headYaw,headPitch);
        matrixStack.scale(-1f,-1f,1f);
        matrixStack.translate(0,0.7,0.3f);
        itemRenderer.renderItem(stack, ModelTransformation.Mode.FIXED,light, OverlayTexture.DEFAULT_UV,matrixStack,vertexConsumers,0);
    }
}
