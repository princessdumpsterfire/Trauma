package net.eta_inf.trauma.mixin;

import net.eta_inf.trauma.Trauma;
import net.eta_inf.trauma.component.Injury;
import net.eta_inf.trauma.component.Knockdown;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

    @Shadow
    public abstract float getMaxHealth();

    @Shadow
    public abstract float getHealth();

    @Shadow
    public abstract void setHealth(float f);

    @Inject(at = @At(value = "HEAD"), method = "baseTick()V")
    private void checkKnockdown(CallbackInfo info) {
        if ((LivingEntity) (Object) this instanceof PlayerEntity) {
            Knockdown kd = Trauma.KNOCKDOWN.get(this);
            Injury inj = Trauma.INJURY.get(this);
            if (!kd.isKnockedDown() && this.getHealth() <= 0.0F) {
                kd.knockDown();
            } else if (kd.isKnockedDown() && this.getHealth() >= 2.0F) {
                kd.revive();
            }
            kd.tick();
            float f = this.getHealth();
            float maxHealable = this.getMaxHealth() - (float) inj.getInjuries() * 2;
            if (f > maxHealable) {
                this.setHealth(maxHealable);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "heal(F)V", cancellable = true)
    private void onEntityHeal(final float amount, final CallbackInfo info) {
        if ((LivingEntity) (Object) this instanceof PlayerEntity) {
            Injury injuries = Injury.get((Object) this);
            float f = this.getHealth();
            float maxHealable = this.getMaxHealth() - (float) injuries.getInjuries() * 2;
            if (f + amount > maxHealable) {
                this.setHealth(maxHealable);
            } else {
                this.setHealth(this.getHealth() + amount);
            }
            info.cancel();
        }
    }

    @Overwrite
    public boolean isDead() {
        if ((LivingEntity) (Object) this instanceof PlayerEntity) {
            return Knockdown.get(this).getKnockdownTimer() >= 1800;
        } else {
            return this.getHealth() <= 0.0F;
        }
    }
}
