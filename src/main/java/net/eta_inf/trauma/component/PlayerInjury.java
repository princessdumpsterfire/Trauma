package net.eta_inf.trauma.component;

import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerInjury extends EntityInjury implements EntitySyncedComponent {
    public PlayerInjury(PlayerEntity owner) {
        super(owner, 0);
    }

    @Override
    public void sync() {
        if (!this.getEntity().world.isClient) {
            // We only sync with the holder, not with everyone around
            this.syncWith((ServerPlayerEntity) this.getEntity());
        }
    }

    @Override
    public void setInjuries(int value) {
        super.setInjuries(value);
        this.sync();
    }

    @Override
    public LivingEntity getEntity() {
        return this.owner;
    }

}
