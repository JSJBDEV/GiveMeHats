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
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.List;


public class DwarvenHatItem extends TrinketItem implements TrinketRenderer {


    public DwarvenHatItem(Settings settings) {
        super(settings);
        
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("text.dwarven_hat"));
    }


    

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if(entity.getY()<20 && !entity.hasStatusEffect(StatusEffects.HASTE))
        {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE,200,2));
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