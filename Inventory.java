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
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Inventory{
    
	private Color overlayColor;
	private int x, y;
	private BufferedImage inventoryImage;
	
	private ArrayList<Item> inventoryItems;
	private ArrayList<Item> playerItems;
	
	private Font pixelFont;
	
	private int attackCD;
	private int protection;
	private int damage;
	private int movementSpeed;
	
	private int attackCDDifficultyModifier;
	private int protectionDifficultyModifier;
	private int damageDifficultyModifier;
	
	
    public Inventory(){
		
		try {
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Minecraft.ttf")).deriveFont(18f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Minecraft.ttf")));
		} catch(IOException | FontFormatException e) {
			System.out.println(e + " in " + this + ".java");
		}
		
		
		inventoryItems = new ArrayList<Item>();
		playerItems = new ArrayList<Item>();
		
		
		playerItems.add(new Sword(1, 1, 16));
	
        try {
			inventoryImage = ImageIO.read(new File("images/GUI/inventory.png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
 
    }
    
	public void addItem(Item newItem) {
		for(int i = 0; i<inventoryItems.size(); i++) {
			Item selectedItem = inventoryItems.get(i);
			if (newItem.getType().equals(selectedItem.getType()) && newItem.getTypeNumber()==selectedItem.getTypeNumber()) {
				selectedItem.modifyQuantity(1);
				return;
			}
		}
		
		if(inventoryItems.size()<24) {
			inventoryItems.add(newItem);
		}
		
	}
	
	
	public void checkClick(double mouseX, double mouseY) {
		double relativeX;
		double relativeY;
		int col;
		int row;
		int index;
		
		if(mouseX >= 430 && mouseX <= 634 && mouseY >= 174 && mouseY <= 486) {
			relativeX = mouseX-430;
			relativeY = mouseY-174;
			
			col = (int) relativeX/54;
			row = (int) relativeY/54;
			
			index = (row*4) + col;
			
			if(index<inventoryItems.size()) {
				Item clickedItem = inventoryItems.get(index);
				String clickedItemType = clickedItem.getType();
				
				if(!clickedItemType.equals("food")) {
					
					int playerItemsIndex = -1;
					for(int i =0 ; i<playerItems.size(); i++) {
						if(playerItems.get(i).getType().equals(clickedItemType)) {
							playerItemsIndex = i;
						}
					}
					if(playerItemsIndex==-1) {
						playerItems.add(clickedItem);
						clickedItem.modifyQuantity(-1);
					} else {
						Item newPlayerItem = playerItems.get(playerItemsIndex);
						
						playerItems.remove(playerItemsIndex);
						inventoryItems.remove(index);
						
						playerItems.add(clickedItem);
						addItem(newPlayerItem);
						// inventoryItems.set(index, playerItems.get(playerItemsIndex));
						
						
						
					}
					
				} else if (clickedItemType.equals("food")) {
					if(Game.getHealth()<10) {
						if(Game.getHealth()+clickedItem.getHealth()>10) {
							Game.setHealth(10);
						} else {
							Game.addToHealth(clickedItem.getHealth());
						}
						clickedItem.modifyQuantity(-1);
						playSound("heal");
					} 
				}
				if(clickedItem.getQuantity()<=0) {
					inventoryItems.remove(index);
				}
			}
			
			
			
			
			
			
		}
	}
	
	public ArrayList<Item> getPlayerItems() {
		return playerItems;
	}
	
	public int getProtection() {
		return protection;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getAttackCD() {
		return attackCD;
	}
	
	public int getMovementSpeed() {
		return movementSpeed;
	}
	
	
	public void setAttackCDModifier(int attackCDDifficultyModifier) {
		this.attackCDDifficultyModifier = attackCDDifficultyModifier;
	} 
	
	public void setProtectionModifier(int protectionDifficultyModifier) {
		this.protectionDifficultyModifier = protectionDifficultyModifier;
	}
	
	public void setDamageModifier(int damageDifficultyModifier) {
		this.damageDifficultyModifier = damageDifficultyModifier;
	}
	
	public void playSound(String soundName) {
		try {
            URL url = this.getClass().getClassLoader().getResource("sounds/sfx/" + soundName + ".wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
	}
	
 
    public void drawMe(Graphics g){
		overlayColor = new Color(0,0,0, 1);
		g.setColor(overlayColor);
		for(int i = 0; i<320; i++) {
			g.fillRect(640-(2*i), 360-(2*i), 1+(4*i), 1+(4*i));
			
		}
		g.drawImage(inventoryImage, 388, 50, null);
		
		int tempX = 430;
		int tempY = 174;
		int index = 0;
		
		for(int r = 0; r<6; r++) {
			for(int c = 0; c<4; c++) {
				if(index<inventoryItems.size()) {
					inventoryItems.get(index).drawMeInventory(g, tempX, tempY);
				}
				tempX+=54;
				index++;
			}
			tempX = 430;
			tempY += 54;
		}
		
		damage = 0;
		protection = 0 + protectionDifficultyModifier;
		attackCD = 0;
		movementSpeed = 0;
		
		for(Item each : playerItems) {
			switch(each.getType()) {
				case "sword":
					each.drawMeInventory(g, 698, 283);
					damage = each.getDamage() + damageDifficultyModifier;
					attackCD = each.getAttackCD() - attackCDDifficultyModifier;
					if(attackCD<=0) {
						attackCD = 1;
					}
					break;
				case "chestplate":
					each.drawMeInventory(g, 764, 251);
					protection += each.getProtection();
					break;
				case "helmet":
					each.drawMeInventory(g, 764, 180);
					protection+= each.getProtection();
					break;
				case "greaves":
					each.drawMeInventory(g, 764, 386);
					movementSpeed += each.getMovementSpeed();
					break;
				case "shield":
					each.drawMeInventory(g, 829, 283);
					protection += each.getProtection();
			}

		}
		
		g.setFont(pixelFont);
		g.setColor(Color.WHITE);
		g.drawString("Damage: " + damage, 703, 570);
		g.drawString("Attack Cooldown: " + attackCD, 703, 600);
		g.drawString("Protection: " + protection, 703, 630);
		
    }
}