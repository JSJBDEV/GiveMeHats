package gd.rf.acro.givemehats.items;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;


public class FoxEarsItem extends TrinketItem {




   @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(new TranslatableComponent("text.foxears"));
    }




    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        if(slotContext.entity().isShiftKeyDown())
        {
            List<Animal> entities = slotContext.entity().getLevel().getEntitiesOfClass(Animal.class,new AABB(slotContext.entity().getX()-15,slotContext.entity().getY()-15,slotContext.entity().getZ(),slotContext.entity().getX()+15,slotContext.entity().getY()+15,slotContext.entity().getZ()+15));
            entities.forEach(entityq ->
            {
                entityq.setDeltaMovement(Vec3.ZERO);
            });
        }
    }
}


