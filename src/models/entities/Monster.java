package models.entities;


import models.AI.MonsterBrain;
import models.Map.Tile;
import models.stats.CharacterStats;
import models.stats.Stats;

/**
 * Created by Breanna on 4/13/16.
 * Monster is a hostile NPC in the game
 */

public class Monster extends NPC implements Movement{
    CharacterStats stats;
    private MonsterBrain monsterBrain;

    public Monster() {
        this.monsterBrain = new MonsterBrain();
        this.stats = new CharacterStats();
    }


    @Override
    public boolean canSwim() {return false;}

    @Override
    public boolean canTraverse() {
        return false;
    }

    @Override
    public boolean canWalk() {
        return true;
    }

    @Override
    public Stats getStats() {
        return stats;
    }

}

