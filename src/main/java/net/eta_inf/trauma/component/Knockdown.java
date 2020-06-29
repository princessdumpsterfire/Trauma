package net.eta_inf.trauma.component;

import nerdhub.cardinal.components.api.component.Component;
import net.eta_inf.trauma.Trauma;

public interface Knockdown extends Component {
    static <T> Knockdown get(T provider) {
        return Trauma.KNOCKDOWN.get(provider);
    }

    boolean isKnockedDown();
    int getKnockdownTimer();
    void setKnockdownTimer(int value);
    void tick();
    void setKnockedDown(boolean b);
    default void knockDown(){
        this.setKnockedDown(true);
    };
    default void revive(){
        this.setKnockedDown(false);
        this.setKnockdownTimer(0);
    };
}
