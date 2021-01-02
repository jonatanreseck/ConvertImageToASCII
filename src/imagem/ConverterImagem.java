/**
 *  código baseado no video: 
 * 
 *   https://www.youtube.com/watch?v=1l2KJUslL1Y/
 */

package imagem;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ConverterImagem {

	private BufferedImage bfImagem;
	private PrintWriter pw;
	private FileWriter fw;
	
	private String[] asciiCode = new String[] {
		"$", "@", "B", "%", "8", "&", "W", "M", "#", "*", "o", "a", "h", "k",
		"b", "d", "p", "q", "w", "m", "Z", "O", "0", "Q", "L", "C", "J", "U",
		"Y", "X", "z", "c", "v", "u", "n", "x", "r", "j", "f", "t", "/", "\"", 
		"|", "(", ")", "1", "{", "}", "[", "]", "?", "-", "_", "+", "~", "<", 
		">", "i", "!", "1", "I", ";", ":", ",", "^", "`", "'", "."
	};
	
	public ConverterImagem() {
		
		try {
			pw = new PrintWriter(fw = new FileWriter(("image.txt"), true));
			bfImagem = ImageIO.read(ConverterImagem.class.getResource("/pikachu.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Deu erro aqui");
		}
	}
	
	public void start() {
		float codeRange = 255.0f / (float)asciiCode.length;
		
		for(int row=0; row<bfImagem.getHeight(); row++) {
			String linha = "";
			for(int col=0; col<bfImagem.getWidth(); col++) {
				//get the rgb value
				
				Color color = new Color(bfImagem.getRGB(col, row));
				double grayScaleValue = color.getRed() * 0.21 + color.getGreen() * 0.72 + color.getBlue() * 0.07;
				
				int index = (int) (grayScaleValue / codeRange);
				
				linha += asciiCode[index] + " ";

			}
			System.out.print(linha + "\n");
			try {
				pw.print(linha + "\n");
				pw.flush();
				fw.flush();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}
