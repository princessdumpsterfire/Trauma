package net.eta_inf.trauma.component;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.extension.CopyableComponent;
import nerdhub.cardinal.components.api.component.extension.TypeAwareComponent;
import net.eta_inf.trauma.Trauma;
import net.minecraft.nbt.CompoundTag;

public class BaseInjury implements Injury, CopyableComponent<Injury>, TypeAwareComponent {
    protected int injuries;

    public BaseInjury() {
        this(0);
    }

    public BaseInjury(int injuries) {
        this.injuries = injuries;
    }

    @Override
    public int getInjuries() {
        return this.injuries;
    }

    @Override
    public void setInjuries(int value) {
        this.injuries = value;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.injuries = tag.getInt("injuries");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("injuries", this.injuries);
        return tag;
    }

    @Override
    public ComponentType<Injury> getComponentType() {
        // Hardcoded but could be passed in the constructor
        return (ComponentType<Injury>) Trauma.INJURY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Injury)) return false;
        return this.injuries == ((Injury) o).getInjuries();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.injuries);
    }
}
