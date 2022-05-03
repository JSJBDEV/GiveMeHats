package gd.rf.acro.givemehats.mixin;

import gd.rf.acro.givemehats.GiveMeHats;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.RandomUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;

@Mixin(LivingEntity.class)
public abstract class AttackMixin {



    
    

    @Inject(method = "hurt", at = @At("TAIL"))
    private void damage(DamageSource source, float amount, CallbackInfoReturnable cir) {
        if(source.getDirectEntity() instanceof ServerPlayer)
        {
            LivingEntity entity = ((LivingEntity)(Object) this);
            Player player = (Player) source.getDirectEntity();
            
            ICuriosHelper component = CuriosApi.getCuriosHelper();

            
            if(component.findFirstCurio(player,GiveMeHats.GOLEM_BUCKET_ITEM.get()).isPresent())
            {

                if(RandomUtils.nextInt(0,5)==0)
                {
                    entity.setDeltaMovement(entity.getDeltaMovement().add(0,1,0));
                }
            }
            if(component.findFirstCurio(player,GiveMeHats.JOJO_HAT_ITEM.get()).isPresent())
            {


                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,100));
                entity.setHealth(entity.getHealth()-amount);


            }
            if(component.findFirstCurio(player,GiveMeHats.ELECTRIC_MOUSE_EARS_HAT_ITEM.get()).isPresent() && player.level.isRaining())
            {
                LightningBolt lightningEntity = new LightningBolt(EntityType.LIGHTNING_BOLT,entity.level);
                lightningEntity.teleportTo(entity.getX(),entity.getY(),entity.getZ());
                entity.getLevel().addFreshEntity(lightningEntity);
            }
            if(component.findFirstCurio(player,GiveMeHats.WOLF_EARS_ITEM.get()).isPresent())
            {
                if(entity.getType()== EntityType.SHEEP)
                {
                    Sheep sheepEntity = (Sheep) entity;
                    sheepEntity.shear(SoundSource.PLAYERS);
                }
            }
            if(component.findFirstCurio(player,GiveMeHats.HIPPIE_VIBES_ITEM.get()).isPresent())
            {
                
                if(entity.getType()== EntityType.SHEEP)
                {
                    Sheep sheepEntity = (Sheep) entity;
                    sheepEntity.setCustomName(new TextComponent("jeb_"));
                }
            }
            if(component.findFirstCurio(player,GiveMeHats.HALO_ITEM.get()).isPresent())
            {
                if(entity.getMobType().equals(MobType.UNDEAD))
                {

                    entity.hurt(DamageSource.playerAttack(player),4);
                }
            }
            if(component.findFirstCurio(player,GiveMeHats.WITCH_HAT_ITEM.get()).isPresent())
            {
                if(RandomUtils.nextInt(0,5)==0)
                {
                    int ff = RandomUtils.nextInt(0,3);
                    switch (ff)
                    {
                        case 0:
                            entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,1000,1));
                            break;
                        case 1:
                            entity.addEffect(new MobEffectInstance(MobEffects.POISON,1000,1));
                            break;
                        case 2:
                            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,1000,1));
                            break;
                    }
                }
            }

        }
    }




}
