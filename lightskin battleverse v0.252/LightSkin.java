import com.badlogic.gdx.ApplicationAdapter; 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer; 
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle; 
import com.badlogic.gdx.math.Circle; 
import com.badlogic.gdx.Input.Keys; 
import com.badlogic.gdx.math.Vector2; 
import com.badlogic.gdx.math.MathUtils; 
import com.badlogic.gdx.math.Intersector; 
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.*; 
import com.badlogic.gdx.graphics.*; 
import java.util.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.Preferences;

public class LightSkin extends ApplicationAdapter//A Pong object ___________ ApplicationAda
{
    private OrthographicCamera camera; //the camera to our world
    private Viewport viewport; //maintains the ratios of your world
    private ShapeRenderer renderer; //used to draw textures and fonts 
    private BitmapFont font; //used to draw fonts (text)
    private BitmapFont font1;
    private SpriteBatch batch; //also needed to draw fonts (text)
    private GameState gamestate;
    private GlyphLayout layout;

    private Texture p1Texture;
    private Player player1;

    private Texture selectScreen;
    private Texture nextButton_selected;
    private Rectangle nextButton;

    private Rectangle kumalaButton;
    private Texture selectKumala; 
    private Rectangle quandaleButton;
    private Texture selectQuandale;
    private Rectangle savestaButton;
    private Texture selectSavesta;

    private Texture kumalala;
    private Texture kumalalaRight;
    private Texture kumalalaLeft;

    private Texture savesta;
    private Texture chesta;
    private Texture quandale;
    private Texture kumalaChosen;
    private Texture savestaChosen;
    private Texture quandaleChosen;
    private int jumpSpeed;

    private Ground ground;
    private Texture groundTexture;

    private boolean doubleJump;
    private int jumpCtr;

    @Override//called once when the game is started (kind of like our constructor)
    public void create(){
        camera = new OrthographicCamera(); //camera for our world, it is not moving
        viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera); //maintains world units from screen units
        renderer = new ShapeRenderer(); 
        font = new BitmapFont(); 
        font.getData().setScale(0.8f, 0.8f);
        batch = new SpriteBatch(); 
        layout = new GlyphLayout();
        gamestate = GameState.SELECT_CHARACTER;

        selectScreen = new Texture(Gdx.files.internal("SELECT background.png"));
        nextButton_selected = new Texture(Gdx.files.internal("nextButton selected.png"));
        nextButton = new Rectangle(270,20,390,60);
        Gdx.files.internal("This PC/Downloads/lightskin battleverse v0.25/player sprites/kumalala/kumalala");
        selectKumala = new Texture(Gdx.files.internal("select kumala.png"));
        selectQuandale = new Texture(Gdx.files.internal("select quandale.png"));
        selectSavesta = new Texture(Gdx.files.internal("select savesta.png"));
        kumalaButton = new Rectangle(20, 145, 80,80);
        quandaleButton = new Rectangle(280, 150, 80,80);
        savestaButton = new Rectangle(20, 10, 100,100);

        kumalalaRight = new Texture(Gdx.files.internal("kumalalaRight.png"));
        kumalalaLeft = new Texture(Gdx.files.internal("kumalalaLeft.png"));

        kumalala = kumalalaRight;
        savesta = new Texture(Gdx.files.internal("savesta.png"));
        chesta = new Texture(Gdx.files.internal("chesta.png"));
        quandale = new Texture(Gdx.files.internal("quandale.png"));
        kumalaChosen = new Texture(Gdx.files.internal("kumalaChosen.png"));
        savestaChosen = new Texture(Gdx.files.internal("savestaChosen.png"));
        quandaleChosen = new Texture(Gdx.files.internal("quandaleChosen.png"));

        player1 = new Player(50, 50);
        p1Texture = kumalala;

        ground = new Ground();
        groundTexture = new Texture(Gdx.files.internal("ground.png"));
    }

    @Override//this is called 60 times a second, all the drawing is in here, or helper
    //methods that are called from here
    public void render(){
        viewport.apply(); 
        //these two lines wipe and reset the screen so when something action had happened
        //the screen won't have overlapping images
        Gdx.gl.glClearColor(0, 147/225f, 165/255f, 250); 
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(gamestate == gamestate.SELECT_CHARACTER) //MENU screen
        {
            selectCharacterScreen();
        }

        //OUT OF BOUNDS AND RESET
        if (player1.getX() > Constants.WORLD_WIDTH-26)
        {
            player1.setX(Constants.WORLD_WIDTH-26);
        }
        if (player1.getX() < 0)
        {
            player1.setX(0);
        }

        if(Gdx.input.isKeyPressed(Keys.D)) //MOVE RIGHT
        {
            player1.moveRight();
            //p1Texture = kumalalaRight;
        }
        if(Gdx.input.isKeyPressed(Keys.A)) //MOVE LEFT
        {
            player1.moveLeft();
            //p1Texture = kumalalaLeft;
        }
        if(Gdx.input.isKeyJustPressed(Keys.SPACE)) //MOVE RIGHT
        {
            jumpCtr++;
            if(jumpCtr > 2)
                jumpSpeed = jumpSpeed;
            else
                jumpSpeed = 14;
        }
        jumpSpeed -= 1;
        player1.setY(player1.getY() + jumpSpeed);
        if(gamestate == gamestate.GAME)
        {
            batch.begin();
            batch.draw(groundTexture,0,-10);
            batch.draw(p1Texture, player1.getX(), player1.getY(), 26, 26);
            batch.end();
        }
        if (player1.overlaps(ground))
        {
            jumpSpeed = 0;
            jumpCtr = 0;
            player1.setY(10);
        }
    }

    public void drawSELECTCHARACTER()
    {
        batch.begin();
        batch.draw(selectScreen, 0, 0);
        batch.draw(selectKumala, 20, 145, 80,80);
        batch.draw(selectQuandale, 280, 150, 80,80);
        batch.draw(selectSavesta, 20, 10, 100,100);
        batch.end();
    }

    public void selectCharacterScreen()
    {
        drawSELECTCHARACTER();
        Vector2 clickLoc = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        if(kumalaButton.contains(clickLoc))
        {
            batch.draw(kumalaChosen, 20, 145, 80,80);
            if(Gdx.input.justTouched())
            {
                p1Texture = kumalala;
            }
        }
        if(savestaButton.contains(clickLoc))
        {
            batch.draw(savestaChosen, 20, 10, 100,100);
            if(Gdx.input.justTouched())
            {
                p1Texture = savesta;
            }
        }
        if(quandaleButton.contains(clickLoc))
        {
            batch.draw(quandaleChosen, 280, 150, 80,80);
            if(Gdx.input.justTouched())
            {
                p1Texture = quandale;
            }
        }
        if(nextButton.contains(clickLoc))
        {
            batch.draw(nextButton_selected, 270, 20, 120,40);
            if(Gdx.input.justTouched())
            {
                gamestate = GameState.GAME;
            }
        }
        batch.end();
    }
    
    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true); 
    }

    @Override
    public void dispose(){
        renderer.dispose(); 
        batch.dispose(); 
    }

}