package models.Item.Takeable.Equippable;

import models.Item.Takeable.TakeableItemVisitor;
import models.entities.Avatar;
import models.entities.Entity;
import models.stats.StatModifiers;

/**
 * Created by mazumderm on 4/14/2016.
 */
public class Slacks extends Equippable implements TakeableItemVisitor {

    public Slacks(String name, StatModifiers statModifiers, int price){
        super(name, statModifiers, price);
    }

    @Override
    public void onUse(Entity entity) {
        Avatar avatar = (Avatar)entity;
        if (avatar.getStats().getLevel() >= lvlReq){
            avatar.equipSlacks(this);
            statModifiers.apply(avatar.getStats());
        }
    }

    @Override
    public void unEquip(Entity entity) {
        Avatar avatar = (Avatar)entity;
        if (avatar.getStats().getLevel() >= lvlReq){
            avatar.unequipSlacks();
            statModifiers.remove(avatar.getStats());
        }
    }

}
