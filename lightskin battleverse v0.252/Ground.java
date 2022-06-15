public class Ground extends GameObject
{
    private int xPos;
    private int yPos;
    private boolean hasTouched;

    public Ground() 
    {
        super(0, 10, Constants.WORLD_WIDTH, 10);
    }

}