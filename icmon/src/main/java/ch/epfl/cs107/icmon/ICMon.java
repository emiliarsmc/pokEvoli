package ch.epfl.cs107.icmon;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        30/11/2023
 */

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.*;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.icmon.gamelogic.events.EndOfTheGameEvent;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.icmon.message.PassDoorMessage;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

import java.util.ArrayList;
import java.util.List;

public class ICMon extends AreaGame {


    ArrayList <ICMonEvent> eventArrayList = new ArrayList<>(); //List of activ events
    ArrayList <ICMonEvent> toAdd = new ArrayList<>();    // Liste of started event that must be add from the activ ones
    ArrayList <ICMonEvent> toRemove = new ArrayList<>(); // List of completed event that must be remove from the activ ones

    private PassDoorMessage mailBox; // mailbox that manages the interaction between the player and the doors
    public final static float CAMERA_SCALE_FACTOR = 13.f;
    private ICMonPlayer player; // mainCharacter of the ggame

    private String currentAreaString = "town";  // gets the name of the area where the player is


    /**
     * creates new areas
     */
    private void createAreas() {
        addArea(new Town());
        addArea(new Lab());
        addArea(new Arena());
        addArea(new EvoliHouse());
        addArea(new Shop());
        addArea(new PlayerHouse());
    }


    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)){
            // create a game state
            ICMonGameState icMonGameState = new ICMonGameState();

            // create an event manager
            ICMonEventManager icMon = new ICMonEventManager();

            // create the current area
            createAreas();
            ICMonArea town = (ICMonArea) setCurrentArea("town", true);

            // create the player
            DiscreteCoordinates coords = new DiscreteCoordinates(5, 5);
            player = new ICMonPlayer(town, Orientation.UP, coords, icMonGameState);
            player.enterArea(town, coords);
            player.centerCamera();

            player.openDialog("welcome_to_icmon"); // welcome dialogue :)

            // create the ball
            ICBall icBall = new ICBall(town);

            // create an event linked to collecting the ball
            ICMonEvent event = new CollectItemEvent(player, icBall, icMon);

            // Start the event a print a message when it's started
            event.onStart(new RegisterInAreaAction(town, icBall));
            event.onStart(new LogAction("CollectItemEvent started !"));
            event.onComplete(new LogAction("CollectItemEvent completed !"));
            toAdd.add(event);


            // create the assistant
            ICShopAssistant assistant = new ICShopAssistant(town);
            event.onStart(new RegisterInAreaAction(town, assistant));


            // create a new event linked to the end of the game
            ICMonEvent eventEndGame = new EndOfTheGameEvent(player, assistant, icMon);
            // unregister the event
            eventEndGame.onStart(new UnRegisterEventAction(event,icMon));


            // a new event (endOfGameEvent) starts when the previous one has been completed (event)
            event.onComplete(new StartEventAction(eventEndGame));
            event.onComplete(new RegisterEventAction(eventEndGame, icMon));


            // start the event
            event.start();


            return true;
        }
        return false;

    }


    // allows the game to evolve during a given period of time
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);


        // the events that are meant to be started are added to the list of active events and those completed must disappear.
        for(var event : toAdd) {
           if(!eventArrayList.contains(event)) {
               eventArrayList.add(event);
           }
        }

        for(var event : toRemove) {
            if(!eventArrayList.contains(event)) {
                eventArrayList.remove(event);
            }
        }

        // clear tabs
        toRemove.clear();
        toAdd.clear();


        // reset the game when r is pressed
        Keyboard keyboard = getWindow().getKeyboard() ;
        Button key = keyboard.get(Keyboard.R) ;
        if(key.isDown()){
            resetGame();
        }


        // update the event
        for(var event : eventArrayList) {
            event.update(deltaTime);
        }


        // manage the messages between the game and the player
        if(mailBox != null){
            mailBox.process();
            mailBox = null;
        }

    }


    @Override
    public void end() {

    }

    @Override
    public String getTitle() {
        return "ICMon";
    }


    /**
     * resets the game to the beginning
     */
    private void resetGame() {
        begin(getWindow(), getFileSystem());
    }


    // materializes a “state” of the game
    public class ICMonGameState implements ICMonInteractionVisitor {
        private ICMonGameState() {
        }

        public String getCurrentAreaString() {
            return currentAreaString;
        }


        /**
         * iterates through the list of event and it makes the Interactable interact with each of the events,
         * thanks to dynamic linking
         * @param interactable
         * @param isCellInteraction
         */
        public void acceptInteraction(Interactable interactable , boolean isCellInteraction){
            for(var event : ICMon.this.eventArrayList){
                interactable.acceptInteraction(event, isCellInteraction);
            }
        }


        /**
         * to send a message to the mailbox
         * @param message
         */
        public void send(PassDoorMessage message) {
            mailBox = message;
        }


        /**
         * changes areas
         * @param doorCordinates
         * @param destinationArea
         * @param coordDestination
         */
        public void switchArea(List<DiscreteCoordinates> doorCordinates, String destinationArea,DiscreteCoordinates  coordDestination ){
            for (DiscreteCoordinates cell : doorCordinates){
                if(player.getCurrentCells().get(0).equals(cell)){
                    player.leaveArea();
                    ICMonArea area =(ICMonArea)setCurrentArea(destinationArea, false);
                    player.enterArea(area, coordDestination);
                }
            }
            currentAreaString = destinationArea;
        }
    }


    /**
     * to share selected methods from ICMon without giving full access to the class
     */
    public class ICMonEventManager implements ICMonInteractionVisitor{


        public ICMonEventManager(){

        }

        /**
         * adds events to the list of started events
         * @param event
         */
        public void registerEvent(ICMonEvent event){
            toAdd.add(event);
        }

        /**
         * removes events from the list of completed events
         * @param event
         */
        public void unRegisterEvent(ICMonEvent event) {
            toAdd.remove(event);
            toRemove.add(event);
        }


    }

}

