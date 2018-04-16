
/**
 *@param cubeToMove the position (0-8) of the cube that we wish to move.
 *@param movingDirection the direction in which we wish to move the cube.
*/
public class Action  {

    public Integer cubeToMove;
    public MovingDirection movingDirection;

    public enum MovingDirection{
        LEFT, RIGHT, UP, DOWN
    }

    public Action(Integer cubeToMove, MovingDirection movingDirection){
        this.cubeToMove = cubeToMove;
        this.movingDirection = movingDirection;
    }


}
