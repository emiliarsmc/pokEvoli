package ch.epfl.cs107.icmon.actor.pokemon;


import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public abstract class Pokemon extends ICMonActor {

    private String name;
    private int hp; // number of life points
    private final int MAX_HP; // maximum number of life points
    private final int damage; // number of points of damage that can be done

    public Pokemon(Area owner, DiscreteCoordinates coordinates, String name, int MAX_HP, int damage) {
        super(owner, Orientation.DOWN, coordinates);
        this.name = name;
        this.hp = MAX_HP;
        this.MAX_HP = MAX_HP;
        this.damage = damage;
    }

    @Override
    public void draw(Canvas canvas) {
        Sprite sprite = new RPGSprite("pokemon/" + name, 1, 1, this);
        sprite.draw(canvas);
    }

    /**
     * list of cells occupied by the pokemon
     * @return (List<DiscrecteCoordinates>)
     */
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }


    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }



    @Override
    public boolean isViewInteractable() {
        return false;
    }

    /**
     * a pokemon can recieve a given number of damage points which will decrement its hp
     * @param (damage) int, damage received
     * @return (int)
     */
    public int looseHP(int damage) {
       hp-= damage;
       if(hp < 0) {
           return 0;
       } else {
           return hp;
       }
    }

    /**
     * if the hp of a pokemon is 0, it dies (the method returns true)
     * @return (boolean) true if the pokémon is dead
     * */
    public boolean isDead() {
        if(hp == 0) {
            return true;
        } else {
            return false;
        }
    }


    public final class PokemonProperties {

        public String name(){
            return null;
        }

        public float hp(){
            return 0f;
        }

        public float maxHp(){
            return 0f;
        }

        public int damage(){
            return 0;
        }

    }

}