package net.eta_inf.trauma.component;

import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerKnockdown extends EntityKnockdown implements EntitySyncedComponent {

    public PlayerKnockdown(PlayerEntity owner) { super(owner); };

    @Override
    public void sync() {
        if (!this.getEntity().world.isClient) {
            // We only sync with the holder, not with everyone around
            this.syncWith((ServerPlayerEntity) this.getEntity());
        }
    }

    @Override
    public void setKnockedDown(boolean knockedDown) {
        super.setKnockedDown(knockedDown);
        if (knockedDown) {
            this.owner.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100000, 4, false, false, true));
            this.owner.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 100000, 4, false, false, true));
            this.owner.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100000, 4, false, false, true));
        } else {
            this.owner.removeStatusEffect(StatusEffects.SLOWNESS);
            this.owner.removeStatusEffect(StatusEffects.MINING_FATIGUE);
            this.owner.removeStatusEffect(StatusEffects.WEAKNESS);
        }
        this.sync();
    }

    @Override
    public void tick() {
        super.tick();
        this.sync();
    }

    @Override
    public Entity getEntity() {
        return this.owner;
    }
}
