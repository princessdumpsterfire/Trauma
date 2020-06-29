package net.eta_inf.trauma.mixin;

import net.eta_inf.trauma.component.Injury;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity {

    public MixinPlayerEntity(EntityType type, World world){
        super(type, world);
    };

    @Inject(at = @At("HEAD"), method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", cancellable = true)
    private void onEntityHurt(final DamageSource source, final float amount, final CallbackInfo info) {
        if (!source.bypassesArmor()) {
            if (super.getEntityWorld().random.nextFloat() * super.getMaxHealth() < amount) {
                Injury injuries = Injury.get(this);
                injuries.addInjuries(1);
            }
        }
    }

    @Inject(at = @At("HEAD"), method="wakeUp(ZZ)V")
    private void onPlayerWake(final CallbackInfo info) {
        Injury.get(this).healInjuries(1);
    }

}
