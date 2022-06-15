public class Kumalala extends Player
{
    private int state;
    private int[] states;
    private int speed;
    private int jumpSpeed;
    int jumpCtr = 0;
    
    public Kumalala(int x, int y)
    {
        super(x,y);
        state = 0;
        speed = 5;
        jumpSpeed = 14;
    }
}
