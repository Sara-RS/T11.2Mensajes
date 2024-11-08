package com.mycompany.t11mensajes;

import java.io.*;
import java.util.*;


public class T11Mensajes {
    private static final String archivo = "mensajes.dat";
    private static List<Mensaje> mensajes = new ArrayList<>();
    private static ListIterator<Mensaje> iterador;

    public static void main(String[] args) {
        cargarMensajes();
        Scanner leer = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n**************");
            System.out.println("* Recordatorio *");
            System.out.println("**************");
            System.out.println("1 Crear mensaje nuevo");
            System.out.println("2 Ver siguiente mensaje");
            System.out.println("3 Ver anterior mensaje");
            System.out.println("4 Eliminar mensaje actual");
            System.out.println("5 Salir");
            opcion= leer.nextInt();
            leer.nextLine();
            switch (opcion) {
                case 1 -> crearMensaje(leer);
                case 2 -> verSiguiente();
                case 3 -> verAnterior();
                case 4 -> eliminarMensaje();
                case 5 -> {
                    guardarMensajes();
                    System.out.println("Saliendo del programa");
                }
                default -> System.out.println(opcion+" no es una opcion valida");
            }
        } while (opcion!= 5);
    }
    private static void cargarMensajes() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
            mensajes= (List<Mensaje>) ois.readObject();
        } catch (IOException e) {
            System.out.println("El archivo mensajes.dat no se ha encontrado.");
        }catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el archivo.");
        }
        iterador = mensajes.listIterator();
    }
    private static void guardarMensajes() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(mensajes);
            System.out.println("Mensajes guardados con exito.");
        } catch (IOException e) {
            System.out.println("Error al guardar mensajes: " + e.getMessage());
        }
    }
    private static void crearMensaje(Scanner scanner) {
        System.out.print("Ingrese el texto del mensaje: ");
        String texto= scanner.nextLine();
        Mensaje mensaje= new Mensaje(texto);
        mensajes.add(mensaje);
        iterador = mensajes.listIterator(mensajes.size()); 
        System.out.println("Mensaje creado: /n" + mensaje);
    }
    private static void verSiguiente() {
        if (iterador.hasNext()) {
            System.out.println("Siguiente mensaje: " + iterador.next());
        } else {
            System.out.println("No hay más mensajes.");
        }
    }
    private static void verAnterior() {
        if (iterador.hasPrevious()) {
            System.out.println("Mensaje anterior: " + iterador.previous());
        } else {
            System.out.println("No hay mensajes anteriores.");
        }
    }
    private static void eliminarMensaje() {
        if (!mensajes.isEmpty() && iterador.hasPrevious()) {
            iterador.previous();
            iterador.remove();
            System.out.println("Mensaje eliminado.");
        } else if (!mensajes.isEmpty() && iterador.hasNext()) {
            iterador.next();
            iterador.remove();
            System.out.println("Mensaje eliminado.");
        } else {
            System.out.println("No hay mensajes para eliminar.");
        }
    }
}