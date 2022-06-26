package Parcial2;

import Parcial2.modelo.Contacto;
import Parcial2.repositorio.repositorioContactoImpl;
import Parcial2.repositorio.repositorioContacto;
import java.sql.*;
import java.util.Scanner;


public class ActualizarRegistros {
    public static void main(String[] args) throws SQLException {
        repositorioContacto<Contacto> repo = new repositorioContactoImpl();
        System.out.println("----- Contactos -----");
        repo.listar().forEach(c -> System.out.println(c.getId()+" | "+c.getNombre()+" | "+c.getApellido()+" | "+c.getEmail()+" | "+c.getDni()));
        System.out.println("---------------------");
        System.out.println("----- Actualizar Contacto -----");

        Contacto contacto = new Contacto();
        Scanner idAct = new Scanner(System.in);
        System.out.println("Ingrese el id del contacto que desea actualizar: ");
        String idActualizar = idAct.nextLine();
        contacto.setId(Long.valueOf(idActualizar));

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el nuevo Nombre: ");
        String nombre = scan.nextLine();

        System.out.println("Ingrese el nuevo Apellido: ");
        String apellido = scan.nextLine();

        System.out.println("Ingrese el nuevo Email: ");
        String email = scan.nextLine();

        System.out.println("Ingrese el nuevo DNI: ");
        int dni = scan.nextInt();
        scan.close();

        contacto.setNombre(nombre);
        contacto.setApellido(apellido);
        contacto.setEmail(email);
        contacto.setDni(dni);
        repo.guardar(contacto);
        System.out.println("El contacto se ha actualizado.");
        repo.listar().forEach(c -> System.out.println(c.getId()+" | "+c.getNombre()+" | "+c.getApellido()+" | "+c.getEmail()+" | "+c.getDni()));

    }
}