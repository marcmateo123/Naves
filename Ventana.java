package main;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame; //IMPORTAMOS LIBRERIA

import graphics.Assets;
import states.GameState;

public class Ventana extends JFrame implements Runnable{ //EXTENDEMOS LA LIBRERIA JFRAME
	
	public static final int WIDTH = 800, HEIGHT = 600; //DECLARAMOS EL TAMAÑO DE LA VENTANA
	private Canvas canvas; //OBJETO (CTRL+SHIFT+O PARA IMPORTAR AUTOMATICAMENTE LA CLASE)
	private Thread thread;
	private boolean running; //BOOLEAN PARA EL 'WHILE' DEL RUN
	
	private BufferStrategy bs; //OBJETO (CTRL+SHIFT+O PARA IMPORTAR AUTOMATICAMENTE LA CLASE)
	private Graphics g; //OBJETO (CTRL+SHIFT+O PARA IMPORTAR AUTOMATICAMENTE LA CLASE)
	
	private final int FPS = 60; //ASIGNAMOS UNAS FPS FIJAS A LAS QUE CORRERA EL JUEGO, YA QUE SI NO, DEPENDERA DE CADA PC
	private double TARGETTIME = 1000000000/FPS; //TIEMPO REQUERIDO PARA AUMENTAR 1FPS (EL VALOR ESTA EN NANOSEGUNDOS YA QUE ES MUCHO MAS PRECISO)
	private double delta = 0; //ALMACENA TEMPORALMENTE EL TIEMPO
	private int AVERAGEFPS = FPS; //PROMEDIO DE FPS
	
	private GameState gameState;
	
	public Ventana() {
		
		setTitle("Juego Naves"); //AQUI DECLARAMOS EL TITULO DE LA VENTANA
		setSize (WIDTH,HEIGHT); //LE DAMOS LAS DIMENSIONES ASIGNADAS PREVIAMENTE
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//PERMITIMOS QUE EL USUARIIO PUEDA CERRAR LA VENTANA
		setResizable(false); //DE ESTA MANERA EL USUARIO NO PUEDE REDIMENSIONAR LA VENTANA
		setLocationRelativeTo(null); //CON ESTA OPCION LA VENTANA SE ABRIRA EN EL CENTRO DE LA PANTALLA
		setVisible(true);
		
		canvas = new Canvas(); //INICIAMOS EL OBJETO
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //DIMENSIONAMOS EL OBJETO
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(true); //RECIBIR ENTRADAS POR TECLADO
		add(canvas);
		
		
		
	}

	public static void main(String[] args) {
		new Ventana().start();

	}
	
	private void update() {
		gameState.update();
	}

	private void draw() {
		bs = canvas.getBufferStrategy();
		
		if(bs == null) { //DE ESTA MANERA SI EL BUFFER STRATEGY NO EXISTE LO CREAMOS
			canvas.createBufferStrategy(3); //LE ASIGNAMOS 3 PORQUE ES EL NUMERO DE BUFFERS QUE UTILIZA UN CANVAS
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//---------------------------
		
		g.fillRect(0, 0, WIDTH, HEIGHT); //ASIGNAMOS QUE EL RECTANGULO ESTÉ LLENO
		gameState.draw(g); //LE PASAMOS EL VALOR G YA QUE ES EL VALOR QUE LE HEMOS DEFINIDO EN CANVAS
		g.drawString(""+AVERAGEFPS, 10, 10); //NOS MUESTRA LAS FPS POR PANTALLA
		
		
		//---------------------------
		g.dispose();
		bs.show();
	}
	
	private void init () { //METODO PARA CARGAR NUESTROS RECURSOS DE ASSETS
		Assets.init();
		gameState = new GameState();
	}
	
	
	@Override
	public void run() {
		
		long now = 0; //REGISTRO DEL TIEMPO QUE PASA
		long lastTime = System.nanoTime(); //NOS DA LA HORA ACTUAL DEL SISTEMA EN NANOSEGUNDOS
		int frames = 0;
		long time = 0;
		
		init();
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime)/TARGETTIME; //OBTENEMOS EL TIEMPO QUE HA PASADO
			time += (now - lastTime);
			lastTime = now; //IGUALAMOS
			
			
			if(delta >= 1) {
				update();
				draw();
				delta --;
				frames++;
			}
			
			if(time >= 1000000000) { //IGUALAMOS LOS FRAMES Y EL TIEMPO A 0 PARA EMPEZAR A CONTAR
				AVERAGEFPS = frames;
				frames = 0;
				time = 0;
			}
		}
		
		stop();
	}

	private void start() {
		
		thread = new Thread(this); //LA CLASE QUE IMPLEMENTA LA INTERFAZ
		thread.start(); //LLAMA AL METODO RUN
		running = true;
	}
	
	private void stop() {
		
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}