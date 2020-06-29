package net.eta_inf.trauma.component;

import nerdhub.cardinal.components.api.component.Component;
import net.eta_inf.trauma.Trauma;

public interface Injury extends Component {
    static <T> Injury get(T provider) {
        return Trauma.INJURY.get(provider);
    }

    int getInjuries();
    void setInjuries(int value);
    default void healInjuries(int value) {
        int actualAmount = Math.min(this.getInjuries(), value);
        this.setInjuries(this.getInjuries() - actualAmount);
    }
    default void addInjuries(int value) {
        this.setInjuries(this.getInjuries() + value);
    }
}
