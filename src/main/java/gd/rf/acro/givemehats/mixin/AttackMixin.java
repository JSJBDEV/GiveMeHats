package gd.rf.acro.givemehats.mixin;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import gd.rf.acro.givemehats.GiveMeHats;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;

import net.minecraft.text.Text;
import org.apache.commons.lang3.RandomUtils;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class AttackMixin {


    @Shadow public abstract void sendPickup(Entity item, int count);

    @Shadow @Nullable public abstract LivingEntity getAttacker();

    @Shadow public abstract void enterCombat();

    @Inject(method = "damage", at = @At("TAIL"))
    private void damage(DamageSource source, float amount, CallbackInfoReturnable cir) {
        if(source.getAttacker() instanceof ServerPlayerEntity)
        {
            LivingEntity entity = ((LivingEntity)(Object) this);
            PlayerEntity player = (PlayerEntity) source.getAttacker();
            TrinketComponent component = TrinketsApi.getTrinketComponent(player).get();
            component.getAllEquipped();



            if(component.isEquipped(a->a.isOf(GiveMeHats.GOLEM_BUCKET_ITEM)))
            {

                if(RandomUtils.nextInt(0,5)==0)
                {
                    entity.addVelocity(0,1,0);
                }
            }
            if(component.isEquipped(a->a.isOf(GiveMeHats.JOJO_HAT_ITEM)))
            {

                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,100));
                entity.setHealth(entity.getHealth()-amount);


            }
            if(component.isEquipped(a->a.isOf(GiveMeHats.ELECTRIC_MOUSE_EARS_HAT_ITEM)) && player.getWorld().isRaining())
            {
                LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT,entity.getWorld());
                lightningEntity.teleport(entity.getX(),entity.getY(),entity.getZ());
                entity.getEntityWorld().spawnEntity(lightningEntity);
            }
            if(component.isEquipped(a->a.isOf(GiveMeHats.WOLF_EARS_ITEM)))
            {
                if(entity.getType()== EntityType.SHEEP)
                {
                    SheepEntity sheepEntity = (SheepEntity) entity;
                    sheepEntity.sheared(SoundCategory.PLAYERS);
                }
            }
            if(component.isEquipped(a->a.isOf(GiveMeHats.HIPPIE_VIBES_ITEM)))
            {
                if(entity.getType()== EntityType.SHEEP)
                {
                    SheepEntity sheepEntity = (SheepEntity) entity;
                    sheepEntity.setCustomName(Text.of("jeb_"));
                }
            }
            if(component.isEquipped(a->a.isOf(GiveMeHats.HALO_ITEM)))
            {
                if(entity.getGroup().equals(EntityGroup.UNDEAD))
                {

                    entity.damage(entity.getDamageSources().mobAttack(entity),4);
                }
            }
            if(component.isEquipped(a->a.isOf(GiveMeHats.WITCH_HAT_ITEM)))
            {
                if(RandomUtils.nextInt(0,5)==0)
                {
                    int ff = RandomUtils.nextInt(0,3);
                    switch (ff)
                    {
                        case 0:
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,1000,1));
                            break;
                        case 1:
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,1000,1));
                            break;
                        case 2:
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE,1000,1));
                            break;
                    }
                }
            }

        }
    }




}
