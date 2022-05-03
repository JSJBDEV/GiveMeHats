package gd.rf.acro.givemehats.items;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import gd.rf.acro.givemehats.GiveMeHats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.function.Supplier;

public class TrinketItem extends Item implements ICurioItem, ICurioRenderer, Supplier<ICurioRenderer> {
    public TrinketItem() {
        super(new Item.Properties().tab(GiveMeHats.TAB));
    }
    public TrinketItem(Properties properties)
    {
        super(properties);
    }
    @Override
    public ICurioRenderer get() {
        return this;
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        //matrixStack.pushPose();
        BakedModel itemModel = itemRenderer.getModel(stack,slotContext.entity().level,slotContext.entity(),1);

        GiveMeHats.translateToFace(matrixStack, renderLayerParent.getModel(),slotContext.entity(),netHeadYaw,headPitch);
        matrixStack.scale(-1f,-1f,1f);
        matrixStack.translate(0,0.7,0.3f); //0.7?



        itemRenderer.render(stack, ItemTransforms.TransformType.FIXED,true,matrixStack,renderTypeBuffer,light,0, itemModel);
        //matrixStack.popPose();
    }
}
