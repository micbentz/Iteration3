package State.States;

import State.State;
import View.StateViews.GameStateView;
import controllers.StateControllers.GameStateController;
import models.AreaEffect.*;
import models.Interaction.MovementHandler;
import models.Map.Map;

import State.StateManager;
import models.Map.Map3D;
import models.Skills.Projectile;
import models.entities.Avatar;
import models.entities.Entity;
import models.entities.NPC;
import models.entities.Villager;
import models.entities.occupation.Occupation;
import models.entities.occupation.Smasher;
import utilities.Point3D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Dartyx on 4/13/2016.
 */
public class GameState extends State{

    private GameStateView gameStateView;
    private GameStateController gameStateController;
    private MovementHandler movementHandler;
    private Avatar avatar;

    private Map3D map;

    private Villager villager;
    private ArrayList<Entity> entities;

    //AreaEffect
    private LevelUp levelUp;
    private Teleport teleport;
    private TakeDamage takeDamage;
    private HealDamage healDamage;
    private InstantDeath instantDeath;
    private Trap trap;

    private ArrayList<AreaEffect> areaEffects;


    //Projectile
    private Projectile projectile;
    private ArrayList<Projectile> projectiles;

    public GameState(StateManager stateManager, JFrame jFrame, Occupation occupation){
        super(stateManager, jFrame);
        avatar = new Avatar(occupation);
        avatar.setLocation(new Point3D(1,1,1));

        map = new Map3D(5);


        villager = new Villager();
        villager.setLocation(new Point3D(12,12,1));


        entities = new ArrayList<>();

        entities.add(avatar);
        entities.add(villager);


        //AreaEffects
        areaEffects = new ArrayList<>();
        teleport = new Teleport(new Point3D(10,10,1));
        takeDamage = new TakeDamage();
        healDamage = new HealDamage();
        instantDeath = new InstantDeath();
        levelUp = new LevelUp();
        trap = new Trap();

        areaEffects.add(levelUp);
        areaEffects.add(teleport);
        areaEffects.add(takeDamage);
        areaEffects.add(healDamage);
        areaEffects.add(instantDeath);
        areaEffects.add(trap);

        map = new Map3D(5);


        //map = new Map(25,25);
        //Here is where you insert things into the map

        //levelUp.setLocation(map.getRelevantTile(2,2).getPoint3D());
        //teleport.setLocation(map.getRelevantTile());

        map.getRelevantTile(12,12).insertEntity(villager);
        map.getRelevantTile(14,10).insertAreaEffect(levelUp);
        map.getRelevantTile(14,11).insertAreaEffect(teleport);
        map.getRelevantTile(14,12).insertAreaEffect(takeDamage);
        map.getRelevantTile(14,13).insertAreaEffect(healDamage);
        map.getRelevantTile(14,14).insertAreaEffect(instantDeath);
        map.getRelevantTile(14,15).insertAreaEffect(trap);


        movementHandler = new MovementHandler(map);
        gameStateView = new GameStateView(map,avatar,entities,areaEffects);
        gameStateController = new GameStateController(this.stateManager,this,jFrame, movementHandler,avatar);
    }

    public void setActive(){
        gameStateController.addToJframe();
    }

    public void setInactive(){
        gameStateController.removeFromJframe();
    }

    @Override
    public void init(){
        //gameStateController.init();
    }

    @Override
    protected void addJFrame(JFrame jFrame){
        gameStateController.addJFrame(jFrame);
    }

    @Override
    protected void update() {
        gameStateController.update();
    }

    @Override
    public void render(Graphics g){
        gameStateView.render(g);
    }



}
