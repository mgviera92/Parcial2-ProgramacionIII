package Parcial2;

import Parcial2.modelo.Contacto;
import Parcial2.repositorio.repositorioContacto;
import Parcial2.repositorio.repositorioContactoImpl;

import java.sql.*;
import java.util.Scanner;

public class AgregarRegistro {
    public static void main(String[] args) {
        repositorioContacto<Contacto> repo = new repositorioContactoImpl();
        System.out.println("-------Contactos-------");
        repo.listar().forEach(c -> System.out.println(c.getId()+" | "+c.getNombre()+" | "+c.getApellido()+" | "+c.getEmail()+" | "+c.getDni()));
        System.out.println("------------------------");
        System.out.println("----- Agregar Contacto -----");
        Contacto contacto = new Contacto();

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el nombre: ");
        String nombre = scan.nextLine();

        System.out.println("Ingrese el apellido: ");
        String apellido = scan.nextLine();

        System.out.println("Ingrese el Email: ");
        String email = scan.nextLine();

        System.out.println("Ingrese el DNI: ");
        int dni = scan.nextInt();
        scan.close();

        contacto.setNombre(nombre);
        contacto.setApellido(apellido);
        contacto.setEmail(email);
        contacto.setDni(dni);
        repo.guardar(contacto);
        System.out.println("El contacto ha sido guardado con éxito");
        repo.listar().forEach(c -> System.out.println(c.getId()+" | "+c.getNombre()+" | "+c.getApellido()+" | "+c.getEmail()+" | "+c.getDni()));




    }
}