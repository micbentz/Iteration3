package models.Map;

import models.Graphics.GraphicAssets;
import models.entities.Avatar;
import models.entities.Monster;
import models.entities.Pet;
import utilities.Point3D;

/**
 * Created by Michael on 4/7/16.
 */
public class StillWaterTile extends Tile implements TileVisitor {


    public StillWaterTile(Point3D point3D){
        super(point3D);
        this.image = GraphicAssets.StillWaterTile;
    }

    @Override
    public boolean visit(Avatar avatar) {
        //TODO: Remember to remove avatar from previous tile in interaction handler
        if(this.checkItem() && avatar.canSwim() && checkEntities() && checkHeightDifferential(avatar) && (avatar.isTrapped()==false)){
            this.insertEntity(avatar);
            applyItems(avatar);
            applyAreaEffect(avatar);
            return true;
        }
        System.out.println("FAILED");
        return avatar.canSwim();
    }

    @Override
    public boolean visit(Monster monster) {
        if(this.checkItem() && monster.canSwim() && checkEntities() && checkHeightDifferential(monster) && (monster.isTrapped()==false)){
            this.insertEntity(monster);
            return true;
        }

        return monster.canTraverse();
    }

    @Override
    public boolean visit(Pet pet) {
        if(this.checkItem() && pet.canSwim() && checkEntities() && checkHeightDifferential(pet) && (pet.isTrapped()==false)){
            this.insertEntity(pet);
            return true;
        }
        return pet.canSwim();
    }

    public String getType(){
        return "Water";
    }
}
