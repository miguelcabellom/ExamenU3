package examen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EjExamen {
	
	static final char LIBRE = 'L';
	static final char RESERVADO = 'R';
	static final char OCUPADO = 'O';

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		char[][] sala = new char[6][10];
		for (int fila = 0; fila < sala.length; fila++) {
			for (int columna = 0; columna < sala[0].length; columna++) {
				sala[fila][columna] = LIBRE;
			}
		}
		int eleccion = 0;
		boolean eleccionValida = false;
		do {
			System.out.println("menu");
			do {
				try {
					eleccion = sc.nextInt();
					if (eleccion > 0 && eleccion < 7) {
						eleccionValida = true;
					} else {
						System.out.println("Opcion invalida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Solo numeros permitidos");
				} finally {
					sc.nextLine();
				}
			} while (!eleccionValida);
			
			if (eleccion == 1) {
				pintarSala(sala);
			} else if (eleccion == 2) {
				boolean valorCorrecto = false;
				int fila = 0;
				int columna = 0;
				do {
					try {
						System.out.println("Introduce la fila del asiento");
						fila  = sc.nextInt();
						System.out.println("Introduce la fila del asiento");
						columna = sc.nextInt();
						valorCorrecto = true;
					} catch (InputMismatchException e) {
						System.out.println("Introduce un numero por favor");
					} finally {
						sc.nextLine();
					}
				} while (!valorCorrecto);
				reservarAsiento(sala, (fila-1), (columna-1));
			} else if (eleccion == 3) {
				boolean valorCorrecto = false;
				int fila = 0;
				int numeroPersonas = 0;
				do {
					try {
						System.out.println("Introduce la fila");
						fila  = sc.nextInt();
						System.out.println("Introduce numero de personas");
						numeroPersonas = sc.nextInt();
						valorCorrecto = true;
					} catch (InputMismatchException e) {
						System.out.println("Introduce un numero por favor");
					} finally {
						sc.nextLine();
					}
				} while (!valorCorrecto);
				reservarGrupoEnFila(sala, fila, numeroPersonas);
			} else if (eleccion == 4) {
				confirmarReservas(sala);
			} else if (eleccion == 5) {
				cancelarReservas(sala);
			} else if (eleccion == 6) {
				
			}
		} while (eleccion != 7);
		
		//pintarSala(sala);
		//System.out.println(contarEstado(sala, LIBRE));
		//reservarAsiento(sala, 1, 4);
		//System.out.println(Arrays.toString(reservarGrupoEnFila(sala, 1, 7)));
		//pintarSala(sala);
		
		// para introducir fila y columna -1
		
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
	
	static boolean esPosicionValida(char[][] sala, int fila, int columna) {
		boolean posicionValida = true;
		if (fila > sala.length || columna > sala[0].length ) {
			posicionValida = false;
		}
		return posicionValida;
	}
	
	static int contarEstado(char[][] sala, char estado) {
		int cantidad = 0;
		for (int fila = 0; fila < sala.length; fila++) {
			for (int columna = 0; columna < sala[0].length; columna++) {
				if (sala[fila][columna] == estado) cantidad++;
			}
		}
		return cantidad;
	}
	
	static boolean reservarAsiento(char[][] sala, int fila, int columna) {
		boolean posibleReservar = false;
		if (esPosicionValida(sala, fila, columna)) {
			if (sala[fila][columna] == LIBRE) {
				sala[fila][columna] = RESERVADO;
				System.out.println("Asiento reservado");
			} else {
				System.out.println("El asiento no está disponible");
			}
		} else {
			System.out.println("Ese asiento no existe");
		}
		return posibleReservar;
	}
	
	static int[] reservarGrupoEnFila(char[][] sala, int fila, int numeroPersonas) {
		int columnaInicio = 0;
		int columnaFinal = 0;
		for (int columna = 0; columna < (sala[0].length-numeroPersonas+1); columna++) {
			columnaInicio = columna;
			for (int columnaBuscada = columna; columnaBuscada < (columna+numeroPersonas); columnaBuscada++) {
				if (sala[fila][columnaBuscada] != LIBRE) {
					break;
				}
				if (columnaBuscada == columna+numeroPersonas-1) {
					columnaFinal = columnaBuscada;
				}
			}
			if (columnaFinal != 0) break;
		}
		
		int[] sitios = new int[2];
		
		if (columnaFinal != 0) {
			for (int columna = columnaInicio; columna <= columnaFinal; columna++) {
				sala[fila][columna] = RESERVADO;
				sitios[0] = columnaInicio;
				sitios[1] = columnaFinal;
			}
			System.out.println("Asientos reservados");
		} else {
			System.out.println("No se han encontrado sitios libres en esa fila para ese numero de personas");
		}
		
		return sitios;
	}
	
	static void confirmarReservas(char[][] sala) {
		for (int fila = 0; fila < sala.length; fila++) {
			for (int columna = 0; columna < sala[0].length; columna++) {
				if (sala[fila][columna] == RESERVADO) {
					sala[fila][columna] = OCUPADO;
				}
			}
		}
		System.out.println("Reservas confirmadas");
	}
	
	static void cancelarReservas(char[][] sala) {
		for (int fila = 0; fila < sala.length; fila++) {
			for (int columna = 0; columna < sala[0].length; columna++) {
				if (sala[fila][columna] == RESERVADO) {
					sala[fila][columna] = LIBRE;
				}
			}
		}
		System.out.println("Reservas canceladas");
	}
	
	static void mostrarEstadisticas(char[][] sala) {
		System.out.println("Libres: "+contarEstado(sala, LIBRE));
		System.out.println("Reservadas: "+contarEstado(sala, RESERVADO));
		System.out.println("Ocupadas: "+contarEstado(sala, OCUPADO));
	}
	

}
