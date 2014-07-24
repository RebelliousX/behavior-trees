package net.obviam.bt.ai;

import net.obviam.bt.Board;
import net.obviam.bt.Droid;

public class MoveTo extends Routine {

    final int destX;
    final int destY;

    public MoveTo(Droid droid, Board board, int destX, int destY) {
        super(droid, board);
        this.destX = destX;
        this.destY = destY;
    }

    @Override
    public void act(Droid droid, Board board) {
        if (isRunning() && !droid.isAlive()) {
            this.state = RoutineState.Failure;
            return;
        }
        if (isRunning() && !isAtDestination()) {
            moveDroid();
        }
    }

    private void moveDroid() {
        if (destY != droid.getY()) {
            if (destY > droid.getY()) {
                droid.setY(droid.getY() + 1);
            } else {
                droid.setY(droid.getY() - 1);
            }
        }
        if (destX != droid.getX()) {
            if (destX > droid.getX()) {
                droid.setX(droid.getX() + 1);
            } else {
                droid.setX(droid.getX() - 1);
            }
        }
        if (isAtDestination()) {
            this.state = RoutineState.Success;
        }
    }

    private boolean isAtDestination() {
        return destX == droid.getX() && destY == droid.getY();
    }
}