import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.net.URL;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.awt.FontMetrics;

public class GUI{
    
	private Font pixelFont;
	
	private BufferedImage playerInfoImage;
	private BufferedImage healthBarImage;
	private BufferedImage attackBarImage;
	private BufferedImage dialogueBoxImage;
	private BufferedImage creditsBoxImage;
	
	private int displayTextIndex = 0;
	
	private ArrayList<String> displayTexts = new ArrayList<String>();
	private String currentText;
	private int startTextIndex = 0;
	// private String displayText = "";
	
    public GUI(){
		
		displayTexts.add("");
		
		try {
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Minecraft.ttf")).deriveFont(18f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Minecraft.ttf")));
		} catch(IOException | FontFormatException e) {
			System.out.println(e + " in " + this + ".java");
		}
		
		
        try {
			playerInfoImage = ImageIO.read(new File("images/GUI/playerInfo.png"));
			healthBarImage = ImageIO.read(new File("images/GUI/healthbar/healthbar10.png"));
			attackBarImage= ImageIO.read(new File("images/GUI/attackbar/attackBar10.png"));
			dialogueBoxImage = ImageIO.read(new File("images/GUI/dialogueBox.png"));
			creditsBoxImage = ImageIO.read(new File("images/GUI/creditsBox.png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
 
    }
    
 
    public void drawMe(Graphics g, int health, int attackCD, int coinCount){
		g.setColor(Color.WHITE);
		try {
			healthBarImage = ImageIO.read(new File("images/GUI/healthbar/healthbar" + health + ".png"));
			attackBarImage= ImageIO.read(new File("images/GUI/attackbar/attackBar" + attackCD + ".png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
		
		g.drawImage(playerInfoImage, 10, 10, null);
		g.drawImage(healthBarImage, 146, 14, null);
		g.drawImage(attackBarImage, 146, 55, null);
		
		g.setFont(pixelFont);
		g.drawString(coinCount + " gold", 177, 122);
		g.drawString(Game.getQuest()+"", 1260, 20);
    }
	
	public void resetDialogue() {
		displayTextIndex = 0;
		displayTexts.clear();
		displayTexts.add("");
		startTextIndex = 0;
	}
	
	
	public void drawDialogue(Graphics g, String text, int frame) {
		
		g.drawImage(dialogueBoxImage, 322, 464, null);
		g.setFont(pixelFont);
		String totalText = text;
		g.setColor(Color.WHITE);
		
		FontMetrics fontMetrics = g.getFontMetrics(pixelFont);
		
		if(frame<totalText.length()) {
			displayTexts.set(displayTextIndex, "");
		}
		for(int i = 0; i<= frame-startTextIndex; i++) {
			if(frame<totalText.length()) {
				

				
				int stringWidth = fontMetrics.stringWidth(displayTexts.get(displayTextIndex));
				if(stringWidth > 550 && i+startTextIndex < totalText.length() && totalText.charAt(i+startTextIndex)==' ') {
					
					startTextIndex += displayTexts.get(displayTextIndex).length()+1;
					System.out.println(startTextIndex);
					displayTexts.add("");
					displayTextIndex ++;
					
				}
				currentText = displayTexts.get(displayTextIndex);
				currentText = displayTexts.get(displayTextIndex);
				if(i+startTextIndex < totalText.length()) {
					currentText += totalText.charAt(i+startTextIndex);
				}
				displayTexts.set(displayTextIndex, currentText);
			} else {
				displayTexts.set(displayTextIndex, currentText);
			}
		}
		
		
		for(int i = 0; i<displayTexts.size(); i++) {
			g.drawString(displayTexts.get(i), 342, 504+(i*25));
		}
		
	}
	
	
	public void drawCredits(Graphics g, String text, int frame) {
		

		
		g.drawImage(creditsBoxImage, 292, 245, null);
		g.setFont(pixelFont);
		String totalText = text;
		g.setColor(Color.WHITE);
		
		FontMetrics fontMetrics = g.getFontMetrics(pixelFont);
		
		if(frame<totalText.length()) {
			displayTexts.set(displayTextIndex, "");
		}
		for(int i = 0; i<= frame-startTextIndex; i++) {
			if(frame<totalText.length()) {
				

				
				int stringWidth = fontMetrics.stringWidth(displayTexts.get(displayTextIndex));
				if(stringWidth > 550 && i+startTextIndex < totalText.length() && totalText.charAt(i+startTextIndex)==' ') {
					
					startTextIndex += displayTexts.get(displayTextIndex).length()+1;
					displayTexts.add("");
					displayTextIndex ++;
					
				}
				currentText = displayTexts.get(displayTextIndex);
				currentText = displayTexts.get(displayTextIndex);
				if(i+startTextIndex < totalText.length()) {
					currentText += totalText.charAt(i+startTextIndex);
				}
				displayTexts.set(displayTextIndex, currentText);
			} else {
				displayTexts.set(displayTextIndex, currentText);
			}
		}
		
		
		for(int i = 0; i<displayTexts.size(); i++) {
			g.drawString(displayTexts.get(i), 312, 270+(i*25));
		}
		
	}
	
	
	
}