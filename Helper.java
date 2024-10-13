import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.net.URL;
import java.util.ArrayList;
import java.awt.AlphaComposite;

public class Helper{
	
	
	private BufferedImage helperImage;
	private int x;
	private int y;
	private int width;
	private int height;
	private int xTile;
	private int yTile;
	private int dialogueIndex = 0;
	
	private Color shadowColor = new Color(0, 0, 0, 100);
	
	private boolean canTalk = false;
	private boolean firstTime = true;
	
	private ArrayList<String> dialogues1;
	private ArrayList<String> dialogues2;
	private ArrayList<String> dialogues3;
	private ArrayList<String> currentDialogue;
	
	public Helper(int xTile, int yTile) {
		
		this.width = 64;
		this.height = 64;
		x = -1280 + (xTile*32);
		y = -608 + (yTile*32);
		this.xTile = xTile;
		this.yTile = yTile;
		
		dialogues1 = new ArrayList<String>();
		dialogues2 = new ArrayList<String>();
		dialogues3 = new ArrayList<String>();
		currentDialogue = new ArrayList<String>();
		
		dialogues1.add("Greetings traveler! Welcome to our humble town!");
		dialogues1.add("What's that? Looking for somewhere to make some money? Well, I'll tell you what, you can earn some gold right here and now! What say you?");
		dialogues1.add("Well that's great! Here's our problem: North of here, just past those rocks in the forest, there's a cave FULL of goblins, scary ones at that! They've been coming here and stealing all our food and taking our people!");
		dialogues1.add("We need help from an adventerous feller like you! Believe me I've tried to get every single one of the people here to go in there and fight, but we just aren't fit enough to do it. Even Strong Sam won't even --");
		dialogues1.add("Oh no! There's one of them goblins right there across the river!! Here, take this short sword and get 'em! When you're finished with him, clear out that cave and I'll have a handsome reward for you!");
		
		
		dialogues2.add("My oh my!! He returns! And with such tremendous riches!");
		dialogues2.add("This truly is a splendid surprise! How many goblins were there? Are they ALL gone now?!?!");
		dialogues2.add("Why that's wonderful! Did you find anything interesting?");
		dialogues2.add("Steps to a DEEPER cave? Out of the people who entered that cave, few ever returned. Not one of them told of a deeper cave... where was it?");
		dialogues2.add("Ah, I see, the southernmost part of the cave, furthest away from the tunnel. That would explain why we had not seen it before. I'll say, what a curious day! I don't want any more trouble for my people, so what if I told you I could pay you more to explore the cave? I can give you some extra armor if you need.");
		dialogues2.add("Splendid! I hope to see you back here soon! Don't get lost!");
		
		
		dialogues3.add("You're back!!!");
		dialogues3.add("It looks like you are very hurt! What did you find down there that caused you so much trouble?");
		dialogues3.add("A DRAGON!!?!?!?!!?");
		dialogues3.add("You truly are a miracle worker!! We have been in turmoil for longer than I can remember, then you just come walking by and solve all our problems! Whatever we have to offer will surely not be enough to compensate for what you have done for us...");
		dialogues3.add("Our village must be ever in your service, young one! Why don't we go down to the tavern and celebrate your victories? We shall name this day after you and forever remember you as the hero of our village!!!");
		
		try {
			
			helperImage = ImageIO.read(new File("images/helper/helper.png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
	}
    
	public void drawMe(Graphics g, int xDiff, int yDiff) {
		g.setColor(shadowColor);
		g.fillOval(x+xDiff+8, y+yDiff+52, 48, 16);
		g.drawImage(helperImage, x+xDiff, y+yDiff, null);
		
	}
	
	public void drawMe(Graphics g) {
		g.setColor(shadowColor);
		g.fillOval(x+8, y+52, 48, 16);
		g.drawImage(helperImage, x, y, null);
	}
	
	public boolean checkTalk(int playerXTile, int playerYTile, double mouseX, double mouseY, int xDiff, int yDiff) {
		double distance = Math.sqrt(Math.pow( (playerXTile-xTile), 2)  +  Math.pow( (playerYTile - yTile), 2));
		

		if(distance <= 4 && mouseX >= x+xDiff && mouseX <= x+xDiff+width && mouseY >= y+yDiff && mouseY <=y+yDiff+height) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public String talk(int currentQuest, int dialogueIndex) {
		
		
		this.dialogueIndex = dialogueIndex;
		
		if(currentQuest == 1) {
			currentDialogue = dialogues1;
		} else if (currentQuest == 2) {
			currentDialogue = dialogues2;
		} else if (currentQuest == 3) {
			currentDialogue = dialogues3;
		}
		
		
		String returnedString = currentDialogue.get(dialogueIndex);
		return returnedString;
		
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getXTile() {
		return xTile;
	}
	
	public int getYTile() {
		return yTile;
	}
	
	
	public int getDialogueLength(int currentQuest) {
		if(currentQuest == 1) {
			return dialogues1.size();
		} else if (currentQuest == 2) {
			return dialogues2.size();
		} else if (currentQuest == 3) {
			return dialogues3.size();
		}
		
		return 0;
	}
	
	public boolean getFirstTime() {
		return firstTime;
	}
	
	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
		
		if(!firstTime && Game.getQuest() == 1) {
			dialogues1.clear();
			dialogues1.add("Just head up there! You might want to take some food with ya since the gear we gave you may be... err... a little weak. Good luck!");
		} else if (!firstTime && Game.getQuest() == 2) {
			dialogues2.clear();
			dialogues2.add("I hope to see you back here soon! Don't get lost!");
		}
	}
	
	
	
}