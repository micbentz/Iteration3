package State;

import State.States.CreationState;
import State.States.GameState;
import State.States.StartMenuState;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Dartyx on 4/9/2016.
 */
public class StateManager {
    private static ArrayList<State> states;
    private static State currentState;


    private static GameState gameState;
    private CreationState creationState;
    private StartMenuState startMenuState;

    private static StateManager instance;
    private StateManager(){
        this.states = new ArrayList<>();
    }

    public static synchronized StateManager getInstance(){
        if (instance == null){
            instance = new StateManager();
            init();
        }
        return instance;
    }

    private static void init(){
        gameState = new GameState(instance);
        currentState = gameState;
        states.add(gameState);
    }

    public void updateCurrentState(){
        currentState.update();
    }

    public void renderCurrentState(Graphics g){
        currentState.render(g);
    }

    public void changeToInventoryState(){
        currentState = null;
    }

    public void changeToGameState(){
        if (currentState != gameState){
            currentState = gameState;
        }
    }

    public static State getCurrentState(){
        return currentState;
    }


}
