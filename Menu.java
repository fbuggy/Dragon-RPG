import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.awt.FontMetrics;

public class Menu{
	
	
	private Button infoButton;
	private Button quitButton;
	private Button playButton;
	private Button backButton;
	
	private ArrayList<Button> startMenuButtonList;
	private ArrayList<Button> infoButtonList;
	private ArrayList<Button> deathButtonList;
	
	private BufferedImage backgroundImage;
	private BufferedImage infoTextImage, miniInventoryImage, swordInventoryImage, swordInventoryEmptyImage, cleaveImage, playerInfoImage, helperImage;
	private BufferedImage fireballImage;
	
	private Font titleFont;
	private Font pixelFont;
	
	private Color titleColor = new Color(166, 44, 0);
	private Color titleShadowColor = new Color(105, 28, 0);
	
	private int infoIndex = 0;
	
	private ArrayList<String> infoTexts;
	
	private String screen = "STARTMENU";
	
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	public Menu() {
		
		startMenuButtonList = new ArrayList<Button>();
		infoButtonList = new ArrayList<Button>();
		deathButtonList = new ArrayList<Button>();
		
		startMenuButtonList.add(new Button("infoButton", 524, 450));
		startMenuButtonList.add(new Button("quitButton", 524, 550));
		startMenuButtonList.add(new Button("playButton", 524, 350));
		startMenuButtonList.add(new Button("easyButton", 1022, 620));
		startMenuButtonList.add(new Button("mediumButton", 1022, 520));
		startMenuButtonList.add(new Button("hardButton", 1022, 420));
		
		infoButtonList.add(new Button("backButton", 50, 50));
		infoButtonList.add(new Button("leftButton", 50, 595));
		infoButtonList.add(new Button("rightButton", 1155, 595));
		
		
		playerList.add(new Player(468, 378));
		playerList.add(new Player(561, 378));
		playerList.add(new Player(655, 378));
		playerList.add(new Player(748, 378));
		
		deathButtonList.add(new Button("playButton", 524, 450));
		deathButtonList.add(new Button("quitButton", 524, 550));
		
		//y=542 is bottom margin
		
		
		infoTexts = new ArrayList<String>();
		infoTexts.add("Press W, A, S, and D to walk in all four directions. There will be obstacles in your way, so make sure to find ways around them! In the different regions around the map, there are connecting paths, stairs, and tunnels that lead to separate places to complete quests."); //add 4 walking animations of character
		infoTexts.add("Press E to access your inventory. After picking up an item, you can use it from your inventory. If you pick up a coin, it will not fill up your inventory, and is displayed in the top left. Different types of items have different uses, and are used differently in the inventory. If you click a food, it will heal you. If you click on armor, it will replace your current armor. If you click on a sword, it will replace your current sword. At the bottom right of the inventory, you can see the stats that your armor and weapons have."); //maybe a miniature inventory
		infoTexts.add("While playing the game, you will have to fight monsters. Line up your mouse in their direction, click, and you will hit any close-by enemies with a cleave attack. After attacking, you will have to rest before attacking again, as indicated by the blue bar in the top left. When it's full, your ready to attack again. If you get hit by a monster, you will see your red bar decrease. This is your health, and if it reaches 0, you die! Watch out!"); //add the two bars, possibly a picture of character doing cleave attack
		infoTexts.add("During your quest, you must collect items to gain power and help rid a village of their problems. To start your adventure, you must talk to the villager, by clicking on him while close enough to talk, and click to continue the conversation. He will aid you in your adventure, so you better listen to him!"); //add picture of helper
		
		
		try {
			backgroundImage = ImageIO.read(new File("images/backgrounds/menuBackground2.png"));
			infoTextImage = ImageIO.read(new File("images/info/infoText.png"));
			miniInventoryImage = ImageIO.read(new File("images/info/miniInventory.png"));
			swordInventoryImage = ImageIO.read(new File("images/info/inventorySword.png"));
			swordInventoryEmptyImage = ImageIO.read(new File("images/info/inventorySwordEmpty.png"));
			cleaveImage = ImageIO.read(new File("images/info/cleave.png"));
			playerInfoImage = ImageIO.read(new File("images/info/playerInfo.png"));
			fireballImage = ImageIO.read(new File("images/icons/fireball.png"));
			helperImage = ImageIO.read(new File("images/helper/helper.png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
		
		
		
		
		
		try {
			titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/limit.ttf")).deriveFont(150f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/limit.ttf")));
			
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Minecraft.ttf")).deriveFont(16f);
			 ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Minecraft.ttf")));
		} catch(IOException | FontFormatException e) {
			System.out.println(e + " in " + this + ".java");
		}
		
		
		
		
	}
    

 
    public void drawStartMenu(Graphics g){
		
		screen = "STARTMENU";

		g.drawImage(backgroundImage, -320, -220, null);
		g.drawImage(fireballImage, 300, 250,null);
		
		
		for(Button each : startMenuButtonList) {
			each.drawMe(g);
			each.checkLit(Game.getMouseX(), Game.getMouseY());
		}
		
		// g.setColor(Color.BLACK);
		// g.fillRect(300, 250,680, 100);
		// g.drawImage(fireballImage, 300, 200,null);
		g.setFont(titleFont);
		g.setColor(titleShadowColor);
		for(int i = 0; i<=5; i++) {
			g.drawString("Da Dragon", 300+i, 250+i);
		}
		g.setColor(titleColor);
		g.drawString("Da Dragon", 300, 250);
		
		
		
    }
	
	public void drawBackground(Graphics g) {
		g.drawImage(backgroundImage, -320, -220, null);
	}
	
	
	public void drawInfo(Graphics g) {
		
		screen = "INFO";
		
		drawBackground(g);
		for(Button each : infoButtonList) {
			each.drawMe(g);
			each.checkLit(Game.getMouseX(), Game.getMouseY());
		}
		g.drawImage(infoTextImage, 343, 110, null);
		
		g.setFont(pixelFont);
		g.setColor(Color.WHITE);
		if(infoIndex == 0) {
			
			playerList.get(0).setY(378);
			
			for(Player each : playerList) {
				each.drawMe(g);
			}
			
			g.setColor(Color.WHITE);
			playerList.get(0).startWalkAnimation("Up");
			g.drawString("W", playerList.get(0).getX()+26, playerList.get(0).getY()+94);
			
			playerList.get(1).startWalkAnimation("Left");
			g.drawString("A", playerList.get(1).getX()+26, playerList.get(1).getY()+94);
			
			playerList.get(2).startWalkAnimation("Down");
			g.drawString("S", playerList.get(2).getX()+26, playerList.get(2).getY()+94);
			
			playerList.get(3).startWalkAnimation("Right");
			g.drawString("D", playerList.get(3).getX()+26, playerList.get(3).getY()+94);
			
		} else if (infoIndex == 1) {
			g.drawImage(miniInventoryImage, 442, 421, null);
			g.drawString("E", 442+28, 551);
			
			g.drawImage(swordInventoryEmptyImage, 610, 441, null);
			g.drawString("M1", 718, 551);
			g.drawImage(swordInventoryImage, 778, 441, null);
		} else if (infoIndex == 2) {
			playerList.get(0).stopWalkAnimation("Left");
			playerList.get(0).setY(428);
			playerList.get(0).drawMe(g);
			g.drawImage(cleaveImage, playerList.get(0).getX()-20, playerList.get(0).getY()-20, null);
			g.drawImage(playerInfoImage, playerList.get(0).getX()-20+108, playerList.get(0).getY()-20, null);
		} else if (infoIndex == 3) {
			g.drawImage(helperImage, 608, 378, null);
		}
		
		drawText(g, infoTexts.get(infoIndex));
		
	}
	
	
	
	public void drawDeathScreen(Graphics g) {
		
		screen = "DEAD";
		
		drawBackground(g);
		g.setFont(titleFont);
		g.setColor(titleShadowColor);
		
		
		for(int i = 0; i<=5; i++) {
			g.drawString("You Died. . .", 363+i, 250+i);
		}
		g.setColor(titleColor);
		g.drawString("You Died. . .", 363, 250);
		
		for(Button each : deathButtonList) {
			each.drawMe(g);
			each.checkLit(Game.getMouseX(), Game.getMouseY());
		}
		
	}
	



	
	public String checkButtonClick () {
		
		String type = "";
		
		if(screen.equals("STARTMENU")) {
			for(Button each : startMenuButtonList) {
				type = each.checkClick();
				
				if(!type.equals("")) {
					return type;
				}
			}
		} else if (screen.equals("INFO")) {
			for(Button each : infoButtonList) {
				type = each.checkClick();
				
				if(!type.equals("")) {
					return type;
				}
			}
		} else if (screen.equals("DEAD")) {
			for(Button each : deathButtonList) {
				type = each.checkClick();
				
				if(!type.equals("")) {
					return type;
				}
			}
		}
		
		return "";
	}
	
	public void drawText(Graphics g, String text) {

		g.setFont(pixelFont);
		String totalText = text;
		String currentText = "";
		g.setColor(Color.WHITE);
		
		FontMetrics fontMetrics = g.getFontMetrics(pixelFont);
		
		int currentTextIndex = 0;
		int displayListIndex = 0;
		int startTextIndex = 0;
		
		ArrayList<String> displayTexts = new ArrayList<String>();
		
		displayTexts.add("");
		
		
		while(currentTextIndex<totalText.length()) {
			
			
			int stringWidth = fontMetrics.stringWidth(displayTexts.get(displayListIndex));
			
			
			if(stringWidth > 457 && currentTextIndex < totalText.length() && totalText.charAt(currentTextIndex)==' ') { //checks if string is too big for text box, then if the total index is less than the total length (its not at the end of the string), and if its at a space, then go to the next line
				
				char newCharacter = totalText.charAt(currentTextIndex);
				currentText = currentText + newCharacter;
				displayTexts.set(displayListIndex, currentText);
				
				
				startTextIndex += displayTexts.get(displayListIndex).length();
				displayTexts.add("");
				displayListIndex ++;
				currentTextIndex = startTextIndex;
				currentText = "";
			}
			
			
			
			currentText = displayTexts.get(displayListIndex);
			
			if(currentTextIndex < totalText.length()) {
				char newCharacter = totalText.charAt(currentTextIndex);
				currentText = currentText + newCharacter;
				displayTexts.set(displayListIndex, currentText);
			}
			
			
			currentTextIndex++;
		}
		
		
		
		for(int i = 0; i<displayTexts.size(); i++) {
			g.drawString(displayTexts.get(i), 411, 195+(i*25));
		}
		
		
	}
	
	
	public void changeInfo(int modifier) {
		infoIndex += modifier;
		
		
		if(infoIndex>=infoTexts.size()) {
			infoIndex = 0;
		} else if (infoIndex <= -1) {
			infoIndex = infoTexts.size()-1;
		}
	}
}