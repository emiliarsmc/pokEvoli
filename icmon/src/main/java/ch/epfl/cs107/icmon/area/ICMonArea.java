package ch.epfl.cs107.icmon.area;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        30/11/2023
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public abstract class ICMonArea extends Area {

    protected abstract void createArea();

    public abstract DiscreteCoordinates getPlayerSpawnPosition();

    // initializes the game area and behavior during the start of the game
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            setBehavior(new ICMonBehavior(window, getTitle()));
            createArea();
            return true;
        }
        return false;
    }

    @Override
    public final float getCameraScaleFactor() {
        return ICMon.CAMERA_SCALE_FACTOR;
    }

}
