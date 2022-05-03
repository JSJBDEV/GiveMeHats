package gd.rf.acro.givemehats.items;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;


public class VikingHatItem extends TrinketItem {


   @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(new TranslatableComponent("text.viking_hat"));
    }


    



    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        if(slotContext.entity().getLevel().isRaining() && !slotContext.entity().hasEffect(MobEffects.DAMAGE_BOOST))
        {
            slotContext.entity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,200));
        }
        if(slotContext.entity().isPassenger() && slotContext.entity().getVehicle() instanceof Boat && !slotContext.entity().hasEffect(MobEffects.NIGHT_VISION))
        {
            slotContext.entity().addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,400));
        }
    }
}
