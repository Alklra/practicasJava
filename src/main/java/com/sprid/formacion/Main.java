package com.sprid.formacion;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        new InteraccionUsuario();

        // Interacción de consola
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, introduce algo de texto:");
        String input = scanner.nextLine();
        System.out.println("Ingresaste: " + input);
    }
}