package graphics;

import java.awt.image.BufferedImage;

public class Assets { //LOS ASSETS SON LOS SONIDOS Y LAS IMAGENES QUE CONTENDRA NUESTRO JUEGO ('ASSETS' ES UN ALIAS QUE YO LE HE DADO)
	
	public static BufferedImage player;//IMAGEN PARA NUESTRO JUGADOR
	
	public static void init() {
		player = Loader.ImageLoader("/ships/playerShip1_blue.png"); //CARGAMOS LA IMAGEN DE NUESTRO JUGADOR
	}
	
}
