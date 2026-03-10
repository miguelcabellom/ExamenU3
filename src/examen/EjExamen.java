package examen;

import java.util.Scanner;

public class EjExamen {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		char[][] sala = new char[6][10];
		for (int fila = 0; fila < sala.length; fila++) {
			for (int columna = 0; columna < sala[0].length; columna++) {
				sala[fila][columna] = 'L';
			}
		}
		
		pintarSala(sala);
		
		sc.close();
	}
	
	static void pintarSala(char[][] sala) {
		for (int fila = -1; fila < sala.length; fila++) {
			for (int columna = 0; columna < sala[0].length; columna++) {
				if (fila == -1) {
					if (columna == 0) {
						System.out.print(" ");
					}
					System.out.print(" " + (columna+1));
				} else {
					if (columna == 0) {
						System.out.print((fila+1));
					}
					System.out.print(" " + sala[fila][columna]);
				}
			}
			System.out.println("");
		}
	}
	
	static boolean  esPosicionValida(char[][] sala, int fila, int columna) {
		boolean posicionValida = true;
		if (fila > sala.length || columna > sala[0].length ) {
			posicionValida = false;
		}
		return posicionValida;
	}
	
	static int contarEstado(char[][] sala, int estado) {
		int cantidad = 0;
		for (int fila = 0; fila < sala.length; fila++) {
			for (int columna = 0; columna < sala.length; columna++) {
				
			}
		}
		return cantidad;
	}
	
	//static void confirmarReservas(char[][] sala)
	//static void cancelarReservas(char[][] sala)
	//static void mostrarEstadisticas(char[][] sala)	
	

}
