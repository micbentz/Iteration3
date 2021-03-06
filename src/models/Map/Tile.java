package models.Map;

import models.AreaEffect.AreaEffect;
import models.Graphics.GraphicAssets;
import models.Graphics.ImageLoader;
import models.Item.Item;
import models.Item.OneShot;
import models.Item.Takeable.Takeable;
import models.entities.Avatar;
import models.entities.Entity;
import utilities.Point3D;
import java.util.Timer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Michael on 3/30/16.
 * Tile represents a single location on the map.
 * It is responsible for holding Entity, Item, AreaEffect, and Projectiles.
 */
public abstract class Tile implements TileVisitor {

    //Properties of tile
    protected Point3D point3D;
    protected BufferedImage image;
    protected int imageHeight;
    protected Entity entity;
    protected ArrayList<Item> items = new ArrayList<>();
    protected AreaEffect areaEffect;
    protected static Timer timer;

    //Methods of a tile
    public Tile(Point3D point3D){
        this.point3D = point3D;
        setImageHeight();
    }

    public BufferedImage getImage(){
        return image;
    }

    protected void setImage(BufferedImage image){
        this.image = image;
    }

    public Point3D getPoint3D(){
        return this.point3D;
    }
    //The coresponding tile's point3d

    public Entity getEntity(){ return entity; }

    public boolean hasEntity(){return(entity!=null);}

    public void insertEntity(Entity entity){
        this.entity = entity;
    }

    public void insertAreaEffect(AreaEffect areaEffect){
        this.areaEffect = areaEffect;
    }

    public void removeEntity(){
        this.entity = null;
    }

    public void removeAreaEffect(AreaEffect areaEffect){
        this.areaEffect = null;
    }

    //Checks if you can pass on the item on the tile.
    public boolean checkItem(){
        for(Item item: items){
            if(!item.onTouch())
                return false;
        }
        return true;
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public boolean checkEntities(){
        return entity == null;
    }

    public boolean checkHeightDifferential(Entity entity){
        System.out.println("jump height: " + entity.getStats().getJumpHeight() + " point z: " + point3D.getZ() + " entity height : " + entity.getLocation().getZ());
       if(entity.getStats().getJumpHeight() >= (point3D.getZ() - entity.getLocation().getZ()))
           return true;
        else
           return false;
    }

    public void applyItems(Entity entity){
        if(items.size() != 0) {
            for (Item item : items) {
                item.apply(entity);
                if((item instanceof Takeable) || (item instanceof OneShot)){
                    removeItem(item);
                }
            }
        }
    }

    public void applyAreaEffect(Avatar avatar){
        if(areaEffect != null) {
            this.areaEffect.activate(avatar);
        }
    }


    public int getDepth(){
        return point3D.getZ();
    }



    public void setImageHeight(){

        imageHeight=point3D.getZ()*10;
        /*switch (point3D.getZ()) {
            case 0:
                imageHeight = GraphicAssets.h1;
                break;
            case 1:
                imageHeight = GraphicAssets.h2;
                break;
            case 2:
                imageHeight = GraphicAssets.h3;
                break;
            case 3:
                imageHeight = GraphicAssets.h4;
                break;
            case 4:
                imageHeight = GraphicAssets.h5;
                break;
            case 5:
                imageHeight = GraphicAssets.h6;
                break;
            case 6:
                imageHeight = GraphicAssets.h7;
                break;
            case 7:
                imageHeight = GraphicAssets.h8;
                break;
            case 8:
                imageHeight = GraphicAssets.h9;
                break;
            case 9:
                imageHeight = GraphicAssets.h10;
                break;


        }*/
    }

    public int getImageHeight(){
        return imageHeight;
    }


    public void cancelTimer() {
        if (timer != null) {
            System.out.println("Timer cancelled");
            timer.cancel();
            timer.purge();
        }
        timer = null;
    }


    public AreaEffect getAreaEffect(){
        return areaEffect;
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public boolean hasAreaEffect(){
        if (areaEffect != null){
            return true;
        }
        return false;
    }

    public boolean hasItem(){
        if (items != null){
            return true;
        }
        return false;
    }

    public void insertItem(Item item){
        items.add(item);
    }


    public String getType(){ return "type";}
}
