package net.eta_inf.trauma;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.eta_inf.trauma.component.*;
import net.eta_inf.trauma.item.SpitefulFetishItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Trauma implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "trauma";
    public static final String MOD_NAME = "Trauma";

    public static final SpitefulFetishItem SPITEFUL_FETISH_ITEM = new SpitefulFetishItem(new Item.Settings().group(ItemGroup.MISC));

    public static final ComponentType<Injury> INJURY =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("trauma:injury"), Injury.class)
                .attach(EntityComponentCallback.event(PlayerEntity.class), PlayerInjury::new);

    public static final ComponentType<Knockdown> KNOCKDOWN =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("trauma:knockdown"), Knockdown.class)
                .attach(EntityComponentCallback.event(PlayerEntity.class), PlayerKnockdown::new);

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        Registry.register(Registry.ITEM, new Identifier("trauma", "spiteful_fetish"), SPITEFUL_FETISH_ITEM);
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}