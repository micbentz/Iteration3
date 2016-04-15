package State.States;

import State.State;
import View.StateViews.GameStateView;
import controllers.StateControllers.GameStateController;
import models.Map.Map;

import State.StateManager;
import java.awt.*;

/**
 * Created by Dartyx on 4/13/2016.
 */
public class GameState extends State{
    private GameStateView gameStateView;
    private GameStateController gameStateController;
    private Map map;


    public GameState(StateManager stateManager){
        super(stateManager);
        gameStateView = new GameStateView();
        gameStateController = new GameStateController(this.stateManager);
    }

    @Override
    protected void update() {
        gameStateController.update();
    }

    @Override
    protected void render(Graphics g){
        gameStateView.render(g);
    }



}