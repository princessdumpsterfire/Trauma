package net.eta_inf.trauma.component;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.extension.TypeAwareComponent;
import net.eta_inf.trauma.Trauma;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;

public class EntityKnockdown implements Knockdown, TypeAwareComponent {
    protected boolean knockedDown;
    protected int knockdownTimer;
    protected LivingEntity owner;

    public EntityKnockdown(LivingEntity owner) {
        this.owner = owner;
        this.knockdownTimer = 0;
        this.knockedDown = false;
    }

    @Override
    public boolean isKnockedDown() { return this.knockedDown; }

    @Override
    public void setKnockedDown(boolean b) { this.knockedDown = b; }

    @Override
    public int getKnockdownTimer() { return this.knockdownTimer; }

    @Override
    public void setKnockdownTimer(int value) { this.knockdownTimer = value; }

    @Override
    public void fromTag(CompoundTag compoundTag) { this.knockedDown = compoundTag.getBoolean("knockedDown"); }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("knockedDown", this.knockedDown);
        tag.putInt("knockdownTimer", this.knockdownTimer);
        return tag;
    }

    @Override
    public ComponentType<Knockdown> getComponentType() {
        return (ComponentType<Knockdown>) Trauma.KNOCKDOWN;
    }

    @Override
    public void tick() {
        if (this.knockedDown) {
            this.knockdownTimer++;
        }
    }
}
