package models.Skills;

import models.effects.LinearEffect;
import models.entities.Avatar;
import models.entities.Entity;
import models.stats.StatusEffect;
import utilities.Point3D;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Breanna on 4/13/16.
 */
public class RangedWeapon extends ProjectableSkill implements ActiveSkill{

    public RangedWeapon(){
        MANACOST = 0;
    }

    @Override
    public void useSkill(Avatar avatar) {
        useRangedWeapon(avatar);
    }

    public void useRangedWeapon(Avatar avatar) {
        //Example of Ring of Operations!
        ArrayList<Point3D> affectedPoints = search();
        ArrayList<Entity> victims = findVictims(affectedPoints);
        projectile = new Projectile(10, StatusEffect.statusEffect.NONE);
        victimize(victims,projectile);
    }

    @Override
    public ArrayList<Point3D> search() {
        Entity.Orientation orientation = getOrientation();
        Point3D point = new Point3D(0,0,0);
        LinearEffect linearEffect= new LinearEffect(point,1,orientation);
        ArrayList<Point3D> affectedPoints;
        affectedPoints=linearEffect.getTilePoints();

        return affectedPoints;
    }



    @Override
    public int calculateDamage(Avatar avatar) {
        return (avatar.getStats().getOffensiveRating());
    }
}
