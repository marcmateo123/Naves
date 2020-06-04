package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	
	public static BufferedImage ImageLoader (String path) { //METODO QUE RETORNA OBJETO DE TIPO BUFFER IMAGEN
		try {
			return ImageIO.read(Loader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; //EN CASO DE QUE NO PUEDA CARGAR LA IMAGEN, QUE NOS RETORNE UN NULL
	}
	
}