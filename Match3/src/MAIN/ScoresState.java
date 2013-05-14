package MAIN;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Animation.Effects.Text;
import Scores.ScoresInfo;



public class ScoresState extends BasicGameState{

	private static final int ID = 2;
	public final float WIDTH, HEIGHT;
	private Image background;
	private static ScoresInfo scoresList;
	private Input input;
	private Text text;
	
	
	public ScoresState(float _WIDTH, float _HEIGHT)
	{
		WIDTH = _WIDTH;
		HEIGHT = _HEIGHT;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		 try{
				background = new Image("Content/ImageFiles/Bricks2.png");
				scoresList = retrieveScores();
				input = container.getInput();
				text = new Text();
				}catch(SlickException e){}
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		
		background.draw(0,0, WIDTH, HEIGHT);

		text.draw("High Scores", WIDTH * 0.35f, HEIGHT * 0.15f, WIDTH * 0.03f, WIDTH * 0.03f, Color.gray);
		text.draw("High Scores", WIDTH * 0.35f+2, HEIGHT * 0.15f+2, WIDTH * 0.03f, WIDTH * 0.03f, Color.black);
		
		text.draw("press N to enter name", WIDTH * 0.15f, HEIGHT * 0.85f,  WIDTH * 0.03f,WIDTH * 0.03f, Color.gray );
		text.draw("press N to enter name", WIDTH * 0.15f+2, HEIGHT * 0.85f+2,  WIDTH * 0.03f,WIDTH * 0.03f, Color.black );

		text.draw("press Enter to continue", WIDTH * 0.15f, HEIGHT * 0.90f,  WIDTH * 0.03f,WIDTH * 0.03f, Color.gray );
		text.draw("press Enter to continue", WIDTH * 0.15f+2, HEIGHT * 0.90f+2,  WIDTH * 0.03f,WIDTH * 0.03f, Color.black );
		
		int indexOfLastScoreInList = scoresList.getLastIndex();
		int indexOfLastDisplayedScore = 11 < indexOfLastScoreInList ?
				                        12 : indexOfLastScoreInList + 1;
		int indexOfLastAddedScore = scoresList.getLastScoreIndex();
		
		Color lineColor;
		for(int i = 0; i < indexOfLastDisplayedScore; i++)
		{
			lineColor = i == indexOfLastAddedScore ? Color.orange : Color.darkGray;
			text.draw(scoresList.getSingleScore(i), WIDTH * 0.05f,(HEIGHT * 0.25f) + (float)(i *(WIDTH * 0.03f)), WIDTH * 0.025f,WIDTH * 0.03f, lineColor);
		}
		
		if (indexOfLastAddedScore == 0) {
<<<<<<< HEAD
			text.draw("!!! NEW RECORD !!!", WIDTH * 0.05f, HEIGHT * 0.18f,  WIDTH * 0.05f,WIDTH * 0.05f, Color.green );
=======
// 			text.draw(":::: NEW RECORD ::::", WIDTH * 0.15f, HEIGHT * 0.75f,  WIDTH * 0.03f,WIDTH * 0.03f, Color.green );
>>>>>>> Add a name state
		}
	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		
		
		// get the most recent scores list
		scoresList = retrieveScores();
		if(input.isKeyPressed(Input.KEY_ENTER))
		{
			game.enterState(3);
		}
		if(input.isKeyDown(Input.KEY_ESCAPE))
		{
			container.exit();
		}
		if(input.isKeyDown(Input.KEY_N))
		{
			game.enterState(4);
		}		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	
	/**
	 * Retrieve the scores list. </br>
	 * ScoresInfos object are serializable and are stored in the
	 * file 'Content/Backups/scores.ser'.</br>
	 * If the file does not exist, a new ScoresInfo object is instantiated,
	 * since it is the first time the game runs locally. It returns
	 * this new object.</br>
	 * If the file exists, it retrieves and returns the ScoresInfo object
	 *  saved in it.
	 * @return ScoresInfo object that contains the scores list
	 */
	public static ScoresInfo retrieveScores() {
		ScoresInfo list = null;
		
	    try {
	      File f = new File("Content/Backups/scores.ser");
	      if (f.exists()) {
	    	  FileInputStream fichier = new FileInputStream(f);
		      ObjectInputStream ois = new ObjectInputStream(fichier);
		      list = (ScoresInfo) ois.readObject();
	      } else {
	    	  list = new ScoresInfo();
	      }
	      
	    } 
	    catch (java.io.IOException e) {
	      e.printStackTrace();
	    }
	    catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    }
	    
	    return list;
	}
	
	/**
	 * Save the scores list into the 'Content/Backups/scores.ser' file
	 * (uses serialization)
	 * @param list ScoresInfo object that stores the scores list
	 */
	public static void saveScores(ScoresInfo list) {
	    try {
	        FileOutputStream fichier 
	           = new FileOutputStream("Content/Backups/scores.ser");
	        ObjectOutputStream oos = new ObjectOutputStream(fichier);
	        oos.writeObject(list);
	        oos.flush();
	        oos.close();
	      }
	      catch (java.io.IOException e) {
	        e.printStackTrace();
	      }
	}
	
	
	
}
