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

public class Sword extends Item {
	
	private BufferedImage swordImage;
	
	private int typeNumber;
	
	private int col, row;
	
	public Sword(int x, int y, int typeNumber) {
		super(x,y, 24, 24, "itemPickup", "sword", typeNumber);
		
		this.typeNumber = typeNumber;
		if(typeNumber<3) {
			setDamage(3);
			setAttackCD(30); //frames
		} else if (typeNumber>=3 && typeNumber<=9) {
			setDamage(5);
			setAttackCD(40);
		} else if (typeNumber >9 && typeNumber<15) {
			setDamage(8);
			setAttackCD(40);
		}
		if(typeNumber == 15) {
			setDamage(2);
			setAttackCD(15);
		}
		if(typeNumber == 16) {
			setDamage(1);
			setAttackCD(30);
		}
		
		
		
		col = 0;
		row = 0;
		int index = 0;
		for(row = 3; row<5; row++) {
			for(col = 0; col<16; col++) {
				if(index==typeNumber) {
					break;
				}
				index++;
			}
			if(index==typeNumber && col<16) {
				break;
			}
			
		}
		
		
		
		try {
			swordImage = ImageIO.read(new File("images/items/items.png"));
			swordImage = swordImage.getSubimage(col*48, row*48, 48, 48);
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
 
		
	}


	@Override
	public void drawMe(Graphics g, int xDiff, int yDiff) {
		g.drawImage(swordImage, getX()+xDiff, getY()+yDiff, null);
	}
	
	@Override
	public void drawMeInventory(Graphics g, int x, int y) {
		g.drawImage(swordImage, x-2, y-2, null);
		
		if(getQuantity()>1) {
			g.setColor(Color.WHITE);
			g.setFont(getFont());
			g.drawString(getQuantity()+"", x+5, y+10);
		}
	}
	
	
}