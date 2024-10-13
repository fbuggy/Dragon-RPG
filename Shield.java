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

public class Shield extends Item {
	
	private BufferedImage shieldImage;
	
	private int typeNumber;
	private int protection;
	
	
	public Shield(int x, int y, int typeNumber) {
		super(x,y, 24, 24, "itemPickup", "shield", typeNumber);
		
		this.typeNumber = typeNumber;
		setProtection(1);
		
		
		int col = 0;
		int row = 9;
		int index = 0;
		for(col = 0; col<3; col++) {
			if(index==typeNumber) {
				break;
			}
			index++;
		}

		
		
		 try {
			shieldImage = ImageIO.read(new File("images/items/items.png"));
			shieldImage = shieldImage.getSubimage(col*48, row*48, 48, 48);
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
 
		
	}


	@Override
	public void drawMe(Graphics g, int xDiff, int yDiff) {
		g.drawImage(shieldImage, getX()+xDiff, getY()+yDiff, null);
	}
	
	@Override
	public void drawMeInventory(Graphics g, int x, int y) {
		g.drawImage(shieldImage, x-2, y-2, null);
		if(getQuantity()>1) {
			g.setColor(Color.WHITE);
			g.setFont(getFont());
			g.drawString(getQuantity()+"", x+5, y+10);
		}
	}

	
}