import java.util.*;
public class Player extends GameObject
{
    private int state;
    private int[] states;
    private int speed;
    private int jumpSpeed;
    int jumpCtr = 0;
    
    public Player(int x, int y)
    {
        super(x,y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        state = 0;
        speed = 5;
        jumpSpeed = 14;
    }

    public void setJumpSpeed(int x) {jumpSpeed = x;}
    public int getJumpSpeed() {return jumpSpeed;}
    
    public void moveLeft() //if (posX < 0) posX = WORLD_WIDTH;
    {
        this.x -= speed;
    }

    public void moveRight() //if (posX > WORLD_WIDTH) posX = 0;
    {
        this.x += speed;
    }
    
    public boolean hasReachedLimit() //double jump
    {
        boolean limit = false;
        jumpCtr++;
        if(jumpCtr <= 2)
        {
            limit = true;
        }
        if(jumpCtr > 2)
        {
            jumpCtr = 0;
        }
        return limit;
    }
}
    