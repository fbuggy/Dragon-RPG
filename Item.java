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

public class Item {
	private int x;
	private int y;
	private int width;
	private int height;
	private String type;
	private String pickupSound;
	private int typeNumber;
	private int quantity;
	private Font pixelFont;
	private int attackCD = 0;
	private int protection = 0;
	private int damage = 0;
	private int health = 0;
	private int movementSpeed = 0;
	
	public Item(int x, int y, int width, int height, String pickupSound, String type, int typeNumber) {	
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.pickupSound = pickupSound;
		this.type = type;
		this.typeNumber = typeNumber;
		quantity = 1;

		
		try {
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Minecraft.ttf")).deriveFont(12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Minecraft.ttf")));
		} catch(IOException | FontFormatException e) {
			System.out.println(e + " in " + this + ".java");
		}
	}
	
	/* public Item(Item oldItem, int quantity) {	
		this.x = oldItem.getX();
		this.y = oldItem.getY();
		this.width = oldItem.getWidth();
		this.height = oldItem.getHeight();
		this.pickupSound = oldItem.getPickupSound();
		this.type = oldItem.getType();
		this.typeNumber = oldItem.getTypeNumber();
		this.quantity = quantity;
		this.damage = getDamage();
		this.protection = getProtection();
		this.attackCD = getAttackCD();
		this.health = getHealth();
		
		try {
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Minecraft.ttf")).deriveFont(12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Minecraft.ttf")));
		} catch(IOException | FontFormatException e) {
			System.out.println(e + " in " + this + ".java");
		}
		
		
	} */
	

	
	public void drawMe(Graphics g, int xDiff, int yDiff) {
		g.setColor(Color.BLACK);
		g.fillRect(x+xDiff, y+yDiff, width, height);
	}
	
	public void drawMeInventory(Graphics g, int x, int y) {
		
	}
	
	public boolean isCoin() {
		return false;
	}
	
	public String getPickupSound() {
		return pickupSound;
	}
	
	public String getType() {
		return type;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getProtection() {
		return protection;
	}
	
	public void setProtection(int protection) {
		this.protection = protection;
	}
	
	public int getAttackCD() {
		return attackCD;
	}
	
	public void setAttackCD(int attackCD) {
		this.attackCD = attackCD;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getMovementSpeed() {
		return movementSpeed;
	}
	
	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
	
	public int getTypeNumber() {
		return typeNumber;
	}
	
	public void modifyQuantity(int addition) {
		quantity += addition;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int newQuantity) {
		quantity = newQuantity;
	}
	
	public Font getFont() {
		return pixelFont;
	}
	
	
	
	
	
	
	
	
	
	
	public String toString() {
		// return "Type: " + type + "x: " + x + ", y: " + y + ", width: " + width + ", height: " + height + ", damage: " + damage + ", protection: " + protection + ", attackCD: " + attackCD + ", health: " + health + ", typeNumber: " + typeNumber + "quantity: " + quantity;
		return "Type: " + type + ", typeNumber: " + typeNumber + "quantity: " + quantity;
	}
	

}