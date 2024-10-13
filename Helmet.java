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

public class Helmet extends Item {
	
	private BufferedImage helmetImage;
	
	private int typeNumber;
	private int protection;
	
	
	public Helmet(int x, int y, int typeNumber) {
		super(x,y, 24, 24, "itemPickup", "helmet", typeNumber);
		
		this.typeNumber = typeNumber;
		/* if(typeNumber<2) {
			setProtection(1);
		} else if (typeNumber >1 && typeNumber<4) {
			setProtection(2);
		} else if (typeNumber == 4 || typeNumber == 6) {
			setProtection(3);
		} else if (typeNumber==5) {
			setProtection(3);
		} */
		
		setProtection(1);
		
		
		int col = 7;
		int row = 8;
		int index = 0;
		for(col = 7; col<16; col++) {
			if(index==typeNumber) {
				break;
			}
			index++;
		}

		
		
		 try {
			helmetImage = ImageIO.read(new File("images/items/items.png"));
			helmetImage = helmetImage.getSubimage(col*48, row*48, 48, 48);
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
 
		
	}


	@Override
	public void drawMe(Graphics g, int xDiff, int yDiff) {
		g.drawImage(helmetImage, getX()+xDiff, getY()+yDiff, null);
	}
	
	@Override
	public void drawMeInventory(Graphics g, int x, int y) {
		g.drawImage(helmetImage, x-2, y-2, null);
		if(getQuantity()>1) {
			g.setColor(Color.WHITE);
			g.setFont(getFont());
			g.drawString(getQuantity()+"", x+5, y+10);
		}
	}

	
}