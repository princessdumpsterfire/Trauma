package net.eta_inf.trauma.item;

import net.eta_inf.trauma.Trauma;
import net.eta_inf.trauma.component.Injury;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

public class SpitefulFetishItem extends Item {

    public SpitefulFetishItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        Injury injuries = Trauma.INJURY.get(playerEntity);
        if (playerEntity.isSneaking()) {
            injuries.healInjuries(1);
        } else {
            injuries.addInjuries(1);
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }
}
