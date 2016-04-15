package models.entities;

import models.stats.CharacterStats;
import models.Item.Inventory;
import utilities.Point3D;

import java.util.ArrayList;

/**
 * Created by Breanna on 4/13/16.
 * Represents a NPC in the game.
 * Superclass for types of NPCs
 */
public abstract class NPC extends Entity {
    protected ArrayList<Observer> observers = new ArrayList<>();


    public abstract void notifyMove(Point3D point3D);
    public abstract void notifyThought();
}

