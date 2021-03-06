package State;

import State.States.*;
import models.entities.occupation.Occupation;
import models.entities.occupation.Smasher;
import models.entities.occupation.Sneak;
import models.entities.occupation.Summoner;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Dartyx on 4/9/2016.
 */
public class StateManager {
    private static ArrayList<State> states;
    private static State currentState;


    private static GameState gameState;
    private static InventoryState inventoryState;
    private static CreationState creationState;
    private static StartMenuState startMenuState;
    private static PauseMenuState pauseMenuState;
    private static SaveState saveState;
    private static EquipmentState equipmentState;
    private static GameOverState gameOverState;
    private static LoadState loadState;
    private static SettingState settingState;
    private static SkillTreeState skillTreeState;
    private static TradeState tradeState;

    private static JFrame jFrame;
    private static StateManager instance;

    private StateManager(JFrame jFrame) {
        this.jFrame = jFrame;
        this.states = new ArrayList<>();
    }

    public static synchronized StateManager getInstance(JFrame jFrame) {
        if (instance == null) {
            instance = new StateManager(jFrame);
            init();
        }
        return instance;
    }

    private static void init() {
        // Create states here

        //gameState = new GameState(instance, jFrame);

        creationState = new CreationState(instance, jFrame);
        startMenuState = new StartMenuState(instance, jFrame);
        pauseMenuState = new PauseMenuState(instance, jFrame);
        saveState = new SaveState(instance, jFrame);
        equipmentState = new EquipmentState(instance, jFrame);
        gameOverState = new GameOverState(instance, jFrame);
        loadState = new LoadState(instance, jFrame);
        settingState = new SettingState(instance, jFrame);

        tradeState = new TradeState(instance, jFrame);


        // Set the current state
        currentState = startMenuState;
        currentState.setActive();
        // Add states to the list
        states.add(gameState);
        states.add(inventoryState);

        //Initialize the states
        //initStates();
    }

    private static void initStates() {
        for (State s : states) {
            s.init();
        }
    }

    public void makeGameState(Occupation occupation) {

        this.gameState=new GameState(instance,jFrame,occupation);
        inventoryState = new InventoryState(instance, jFrame, gameState);
        int ayy=0;
        if(gameState.getAvatar().getOccupation() instanceof Summoner)ayy=0;
        else  if(gameState.getAvatar().getOccupation() instanceof Sneak)ayy=1;
        else  if(gameState.getAvatar().getOccupation() instanceof Smasher)ayy=2;
        skillTreeState = new SkillTreeState(instance, jFrame,ayy);
    }

    public void setJframe(JFrame jframe){
        this.jFrame=jframe;
    }

    public void updateCurrentState(){
        currentState.update();
    }

    public void renderCurrentState(Graphics g){
        currentState.render(g);
    }

    public void addJFrameToCurrentState(JFrame frame){
        currentState.addJFrame(frame);
    }

    public void changeToInventoryState(){
        //System.out.println("well its changing");
        if (currentState != inventoryState){
            currentState.setInactive();
            //System.out.println("Changing to inventorystate");
            currentState = inventoryState;
            currentState.setActive();
        }
    }

    public void changeToGameState(){
        //System.out.println("well its changing");

        if (currentState != gameState){
            currentState.setInactive();
            //System.out.println("Changing to gamestate");
            currentState = gameState;
            currentState.setActive();
        }
    }

    public void changeToPauseMenuState(){
        if (currentState != pauseMenuState){
            currentState.setInactive();
            System.out.println("Changing to pausemenustate");
            currentState = pauseMenuState;
            currentState.setActive();
        }
    }

    public void changeToCreationState(){
        if (currentState != creationState){
            currentState.setInactive();
            System.out.println("Changing to creationstate");
            currentState = creationState;
            currentState.setActive();
        }
    }

    public void changeToStartMenuState(){
        if (currentState != startMenuState){
            currentState.setInactive();
            System.out.println("Changing to startmenustate");
            currentState = startMenuState;
            currentState.setActive();
        }
    }

    public void changeToSaveState(){
        if (currentState != saveState){
            currentState.setInactive();
            System.out.println("Changing to savestate");
            currentState = saveState;
            currentState.setActive();
        }
    }
    public void changeToLoadState(){
        if (currentState != loadState){
            currentState.setInactive();
            System.out.println("Changing to savestate");
            currentState = loadState;
            currentState.setActive();
        }
    }
    public void changeToEquipmentState(){
        if (currentState != equipmentState){
            currentState.setInactive();
            System.out.println("Changing to equipmentstate");
            currentState = equipmentState;
            currentState.setActive();
        }
    }

    public void changeToGameOverState(){
        if (currentState != gameOverState){
            currentState.setInactive();
            System.out.println("Changing to gameoverstate");
            currentState = gameOverState;
            currentState.setInactive();
        }
    }

    public void changeToSettingState(){
        if (currentState != settingState){
            currentState.setInactive();
            System.out.println("Changing to settingstate");
            currentState = settingState;
            currentState.setActive();
        }
    }

    public void changeToSkillTreeState(){
        if (currentState != skillTreeState){
            currentState.setInactive();
            System.out.println("Changing to skilltreestate");
            currentState = skillTreeState;
            currentState.setActive();
        }
    }

    public void changeToTradeState(){
        if (currentState != tradeState){
            currentState.setInactive();
            System.out.println("Changing to tradestate");
            currentState = tradeState;
            currentState.setActive();
        }
    }

    public void renderMap(Graphics g){
        gameState.render(g);
    };

    public State getCurrentState(){
        return currentState;
    }

    public void testFunction(){
        System.out.println("HIII");
    }


}

