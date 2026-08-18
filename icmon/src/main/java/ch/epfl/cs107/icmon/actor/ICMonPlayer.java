package ch.epfl.cs107.icmon.actor;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        30/11/2023
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.Oak;
import ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli.Voltali;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.area.maps.PlayerHouse;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.icmon.message.GamePlayMessage;
import ch.epfl.cs107.icmon.message.PassDoorMessage;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.actor.Interactor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;


public class ICMonPlayer extends ICMonActor implements Interactor {

    private final static int ANIMATION_DURATION = 10;

    private final static int MOVE_DURATION = 5;

    private final ICMonPlayerInteractionHandler handler; //manager of specific interactions

    private OrientedAnimation currentAnimation;

    private final OrientedAnimation waterAnimation;

    private final OrientedAnimation earthAnimation;
    private ICMon.ICMonGameState icMonGameState;



    private boolean inDialog ;
    private Dialog dialog; // models a speech he gives at a given moment
    private String favEvolution; // the players' favorite evolution (extension)
    private int favEvolutionInt; // the players' favorite evolution (to register it in player's house)



    // Constructor
    public ICMonPlayer(Area owner, Orientation orientation,DiscreteCoordinates coordinates, ICMon.ICMonGameState icMonGameState) {
        super(owner, orientation,coordinates);
        resetMotion();
        this.icMonGameState = icMonGameState;
        handler = new ICMonPlayerInteractionHandler();
        currentAnimation = new OrientedAnimation("actors/player", ANIMATION_DURATION / 2, Orientation.DOWN, this);
        earthAnimation = new OrientedAnimation("actors/player", ANIMATION_DURATION / 2, getOrientation(), ICMonPlayer.this);
        waterAnimation = new OrientedAnimation("actors/player_water", ANIMATION_DURATION / 2, getOrientation(), ICMonPlayer.this);
    }

    /**
     * sets the animation to current animation
     * @param currentAnimation
     */
    public void setCurrentAnimation(OrientedAnimation currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    /**
     *
     * @return your favorite Evoli evolution
     */
    public String getFavEvolution() {
        if(favEvolution == null){
            return "null";
        }
        return favEvolution;
    }

    /**
     * sets favEvolution to your favorite Evoli evolution
     * @param favEvolution
     */
    private void setFavEvolution(String favEvolution) {
        this.favEvolution = favEvolution;
    }

    /**
     * allows the dialogue to start and join the right text to a dialogue in the context of the evoliHouseEvent
     * @param text
     */
    public void evoliHouseEvent(String text) {
        openDialog(text);
        setFavEvolution(text);
        inDialog = true;
    }

    // update method which manages the movements
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Keyboard keyboard = getOwnerArea().getKeyboard();
        if(!inDialog) {
            if (!isDisplacementOccurs()) {
                currentAnimation.reset();
            } else {
                currentAnimation.update(deltaTime);
            }

            moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
            moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
            moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
            moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
        }
        else if(keyboard.get(Keyboard.SPACE).isPressed()) {
            dialog.update(deltaTime);

            if (dialog.isCompleted()){
                inDialog = false;
            }
        }


        if(icMonGameState.getCurrentAreaString().equals("evoliHouse")) {
            if(keyboard.get(Keyboard.V).isPressed()){
                evoliHouseEvent("voltali");
            }
            if(keyboard.get(Keyboard.A).isPressed()){
                evoliHouseEvent("aquali");
            }
            if(keyboard.get(Keyboard.M).isPressed()){
                evoliHouseEvent("mentali");
            }
            if(keyboard.get(Keyboard.F).isPressed()){
                evoliHouseEvent("phylalli");
            }
            if(keyboard.get(Keyboard.G).isPressed()){
                evoliHouseEvent("givrali");
            }
            if(keyboard.get(Keyboard.W).isPressed()){
                evoliHouseEvent("nymphali");
            }
            if(keyboard.get(Keyboard.N).isPressed()){
                evoliHouseEvent("noctali");
            }
            if(keyboard.get(Keyboard.P).isPressed()){
                evoliHouseEvent("pyroli");
            }


        }

    }


    // draws the player
    @Override
    public void draw(ch.epfl.cs107.play.window.Canvas canvas) {
        currentAnimation.draw(canvas);
        if(inDialog){
            dialog.draw(canvas);
        }
    }

    /**
     * indicates that the player agrees to have his interactions managed by an interaction manager
     * @param v (AreaInteractionVisitor) : the visitor
     * @param isCellInteraction
     */
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }


    // return true if the object is not traversable
    @Override
    public boolean takeCellSpace() {
        return true;
    }


    // true if it accepts contact interactions
    @Override
    public boolean isCellInteractable() {
        return true;
    }


    // true if it accepts remote interactions
    @Override
    public boolean isViewInteractable() {
        return true;
    }


    // list of cells occupied by the player
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }


    //list of cells in its field of vision
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }


    // true if it requests a contact interaction
    @Override
    public boolean wantsCellInteraction() {
        return true;
    }


    // if it requests remote interaction
    @Override
    public boolean wantsViewInteraction() {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        Button key = keyboard.get(Keyboard.L);
        if(keyboard.get(Keyboard.SPACE).isPressed() && inDialog){

        }



        if (key.isPressed() && !inDialog) {
            return true;
        } else {
            return false;
        }
    }


    // delegate the management of its interactions to the handler
    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler, isCellInteraction);
        icMonGameState.acceptInteraction(other, isCellInteraction);
    }


    /**
     * manages all the movements
     * @param orientation
     * @param b
     */
    private void moveIfPressed(Orientation orientation, Button b) {
        if (b.isDown()) {
            if (!isDisplacementOccurs()) {
                orientate(orientation);
                currentAnimation.orientate(orientation);
                move(MOVE_DURATION);
            }

        }
    }


    /**
     * the player leaves the current area
     */
    public void leaveArea() {
        getOwnerArea().unregisterActor(this);
    }


    /**
     * enters the area given as a parameter and resets the current motion information
     * @param area
     * @param position
     */
    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }


    /**
     * puts the focus on the mainplayer
     */
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);

    }


    /**
     * creates a dialog
     * @param dialog
     */
    public void openDialog(String dialog) {
        this.dialog = new Dialog(dialog);
        inDialog = true;

    }

    /**
     * sets dialog to inDialog
     * @param dialog
     */
    public void setDialog(boolean dialog) {
        this.inDialog = inDialog;
    }


    /**
     * must provide a definition of interaction methods of any ICMon game Interactor
     */
    private class ICMonPlayerInteractionHandler implements ICMonInteractionVisitor {

        // the method allows you to interact with the ball
        // the second parameter allows you to specify the desired mode of interaction: by contact (true) remotely (false)4
        @Override
        public void interactWith(ICBall icball, boolean isCellInteraction) {
            if (!isCellInteraction) {
                if (wantsViewInteraction() && icball.isViewInteractable() && (icball.getCurrentCells().equals(getFieldOfViewCells()))) {
                    icball.collect();
                }
            }
        }


        // the method allows you to interact with a cell
        @Override
        public void interactWith(ICMonBehavior.ICMonCell currentCell, boolean isCellInteraction) {
            if (isCellInteraction) {
                if (currentCell.getType().getIsWalkable() == ICMonBehavior.AllowedWalkingType.SURF) {
                    setCurrentAnimation(waterAnimation);
                } else {
                    setCurrentAnimation(earthAnimation);
                }
            }
        }


        // the method allows you to interact with a door
        @Override
        public void interactWith(Door door, boolean isCellInteraction) {
            if (isCellInteraction) {
                PassDoorMessage message = new PassDoorMessage(door, icMonGameState);
                icMonGameState.send(message);
            }
        }

        // the method allows you to interact with a sign
        @Override
        public void interactWith(Sign sign, boolean isCellInteraction) {
            if (!isCellInteraction) {
                    if(sign.isViewInteractable()) {
                        if(sign.getCurrentCells().contains(new DiscreteCoordinates(8,23))) {
                            openDialog("sign");
                        } else if(sign.getCurrentCells().contains(new DiscreteCoordinates(17,22))) {
                            openDialog("sign lab");
                        }else if(sign.getCurrentCells().contains(new DiscreteCoordinates(17,16))){
                            openDialog(("sign arena"));
                        }
                    }
            }
        }



        // the method allows you to interact with professor Oak
        @Override
        public void interactWith(Oak oak, boolean isCellInteraction) {
            if (!isCellInteraction) {
                if (oak.isViewInteractable()) {
                    if(getFavEvolution().equals("voltali")) {
                        evoliHouseEvent("prof-oak-voltali");
                    }
                    if(getFavEvolution().equals("aquali")) {
                        evoliHouseEvent("prof-oak-aquali");
                    }
                    if(getFavEvolution().equals("mentali")) {
                        evoliHouseEvent("prof-oak-mentali");
                    }
                    if(getFavEvolution().equals("phylalli")) {
                        evoliHouseEvent("prof-oak-phylalli");
                    }
                    if(getFavEvolution().equals("givrali")) {
                        evoliHouseEvent("prof-oak-givrali");
                    }
                    if(getFavEvolution().equals("nymphali")) {
                        evoliHouseEvent("prof-oak-nymphali");
                    }
                    if(getFavEvolution().equals("noctali")) {
                        evoliHouseEvent("prof-oak-noctali");
                    }
                    if(getFavEvolution().equals("pyroli")) {
                        evoliHouseEvent("prof-oak-pyroli");
                    }
                    if(getFavEvolution().equals("null")) {
                        evoliHouseEvent("prof-oak-null");
                    }
                }
            }
        }
    }
}



