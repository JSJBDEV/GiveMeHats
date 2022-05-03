package gd.rf.acro.givemehats.mixin;

import gd.rf.acro.givemehats.ConfigUtils;
import gd.rf.acro.givemehats.GiveMeHats;
import gd.rf.acro.givemehats.items.TrinketItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.RandomUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Random;

@Mixin(RandomizableContainerBlockEntity.class)
public abstract class LootMixin extends BaseContainerBlockEntity {


    @Shadow @Nullable protected ResourceLocation lootTable;

    @Shadow protected abstract NonNullList<ItemStack> getItems();

    protected LootMixin(BlockEntityType<?> p_155076_, BlockPos p_155077_, BlockState p_155078_) {
        super(p_155076_, p_155077_, p_155078_);
    }

    @Inject(method = "unpackLootTable", at = @At("HEAD"))
    private void loot(Player player, CallbackInfo ci)
    {

        if (this.lootTable != null && this.level.getServer() != null)
        {
            Random random = player.getRandom();
            for (int i = 0; i < Integer.parseInt(ConfigUtils.config.getOrDefault("max_hats_per_chest","3")); i++) {
                if(random.nextInt(Integer.parseInt(ConfigUtils.config.getOrDefault("no_hat_per_roll","3")))==0)
                {
                    this.getItems().set(RandomUtils.nextInt(0,27),new ItemStack((TrinketItem) GiveMeHats.LOADED_HATS.get(random.nextInt(GiveMeHats.LOADED_HATS.size())).get()));
                }
            }

        }

    }
}
