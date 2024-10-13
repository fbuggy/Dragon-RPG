import javax.swing.JFrame;
import java.awt.Point;
import java.awt.PointerInfo; 
 
public class Runner {
   
    public static void main(String[] args) {
 
        // JFrame frame = new JFrame("Arpegg.io");
		JFrame frame = new JFrame("Da Dragon");
		
		
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create panel and add it to the frame
        Game gameObject = new Game();
         
        frame.add(gameObject);
        frame.pack();
        frame.setVisible(true);
		frame.setIconImage(frame.getToolkit().createImage("images/icons/DaDragonIcon.png"));



        // frame.setIconImage(createImage("images/icon/DaDragonIcon.png").getImage());
		 
        gameObject.animate();
    }
}