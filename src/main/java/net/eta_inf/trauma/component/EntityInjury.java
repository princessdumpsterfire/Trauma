package net.eta_inf.trauma.component;

import net.minecraft.entity.LivingEntity;

public class EntityInjury extends BaseInjury {
    protected LivingEntity owner;

    public EntityInjury(LivingEntity owner, int baseInjuries) {
        this.owner = owner;
        this.injuries = baseInjuries;
    }
}
