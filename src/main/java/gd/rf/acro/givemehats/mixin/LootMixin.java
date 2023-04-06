package gd.rf.acro.givemehats.mixin;

import gd.rf.acro.givemehats.ConfigUtils;
import gd.rf.acro.givemehats.GiveMeHats;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.RandomUtils;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LootableContainerBlockEntity.class)
public abstract class LootMixin extends LockableContainerBlockEntity {


    @Shadow @Nullable protected Identifier lootTableId;

    @Shadow public abstract ItemStack getStack(int slot);

    @Shadow public abstract void setStack(int slot, ItemStack stack);

    @Shadow protected abstract DefaultedList<ItemStack> getInvStackList();

    @Shadow protected long lootTableSeed;

    protected LootMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }


    @Inject(method = "checkLootInteraction", at = @At("HEAD"))
    private void loot(PlayerEntity player, CallbackInfo ci)
    {
        if (this.lootTableId != null && this.world.getServer() != null)
        {
            Random random;
            if (player == null)
            {
                random = new Random(this.lootTableSeed);
            } else {
                random = player.getRandom();
            }
            for (int i = 0; i < Integer.parseInt(ConfigUtils.config.getOrDefault("max_hats_per_chest","3")); i++) {
                if(random.nextInt(Integer.parseInt(ConfigUtils.config.getOrDefault("no_hat_per_roll","3")))==0)
                {
                    this.getInvStackList().set(RandomUtils.nextInt(0,27),new ItemStack(GiveMeHats.LOADED_HATS.get(random.nextInt(GiveMeHats.LOADED_HATS.size()))));
                }
            }

        }

    }
}
