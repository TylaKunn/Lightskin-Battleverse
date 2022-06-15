import java.util.*;
public class Player extends GameObject
{
    private boolean state;
    private int speed;
    private int momentum;
    private float startLocation;
    private boolean reachedJumpPeak;
    private boolean returnToGround;
    private boolean hasHitPlatform;
    int jumpCtr = 0;
    
    public Player(int x, int y)
    {
        super(x,y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        speed = 5;
        startLocation = y;
        reachedJumpPeak = false;
        returnToGround = true;
        hasHitPlatform = false;
    }

    public void moveLeft() //if (posX < 0) posX = WORLD_WIDTH;
    {
        this.x -= speed;
    }

    public void moveRight() //if (posX > WORLD_WIDTH) posX = 0;
    {
        this.x += speed;
    }

    public void bounce()
    {
        this.y += speed;
    }

    public void fall()
    {
        this.y -= speed;
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
    