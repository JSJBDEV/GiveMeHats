package gd.rf.acro.givemehats.items;

import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.Trinket;
import gd.rf.acro.givemehats.GiveMeHats;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

import static dev.emi.trinkets.api.TrinketItem.TRINKET_DISPENSER_BEHAVIOR;

public class SlimeHatItem extends Item implements Trinket {


    public SlimeHatItem(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this,TRINKET_DISPENSER_BEHAVIOR);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("text.slimehat"));
    }


    @Override
    public boolean canWearInSlot(String s, String s1) {
        return s.equals(SlotGroups.HEAD) && s1.equals(Slots.MASK);
    }

    @Override
    public void tick(PlayerEntity player, ItemStack stack) {
        //from slime block
        if(!player.getEntityWorld().getBlockState(player.getBlockPos().down()).isAir() && player.fallDistance>2){
            Vec3d vec3d = player.getVelocity();
            if (vec3d.y < 0.0D) {
                player.setVelocity(vec3d.x, -vec3d.y * 0.8, vec3d.z);
                player.fallDistance=0;
            }
        }
    }

    @Override
    public void render(String slot, MatrixStack matrixStack, VertexConsumerProvider vertexConsumer, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = new ItemStack(GiveMeHats.SLIME_HAT_ITEM);
        Trinket.translateToFace(matrixStack,model,player,headYaw,headPitch);
        matrixStack.scale(-1f,-1f,1f);
        matrixStack.translate(0,0.6,0.3f);
        itemRenderer.renderItem(stack, ModelTransformation.Mode.FIXED,light, OverlayTexture.DEFAULT_UV,matrixStack,vertexConsumer);

    }
}
