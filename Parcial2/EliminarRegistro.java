package Parcial2;

import Parcial2.modelo.Contacto;
import Parcial2.recursoConexion.ConexionBaseDatos;
import Parcial2.repositorio.repositorioContacto;
import Parcial2.repositorio.repositorioContactoImpl;

import java.sql.*;
import java.util.Scanner;

public class EliminarRegistro {
    public static void main(String[] args) {

        try(Connection conn = ConexionBaseDatos.getInstance()){
            repositorioContacto<Contacto> repo = new repositorioContactoImpl();

            repo.listar().forEach(c -> System.out.println(c.getId()+" | "+c.getNombre()+" | "+c.getApellido()+" | "+c.getEmail()+" | "+c.getDni()));
            System.out.println("---------------------------------------------");
            System.out.println("-------------Eliminar Contacto---------------");

            Contacto delete = new Contacto();
            Scanner delID = new Scanner(System.in);
            System.out.println("Ingrese el ID del contacto que desea eliminar: ");
            int d = delID.nextInt();
            delete.setId((long) d);

            repo.eliminar(delete.getId());
            System.out.println("El contacto ha sido eliminado.");
            System.out.println("---------------------------------------------");

            repo.listar().forEach(c -> System.out.println(c.getId()+" | "+c.getNombre()+" | "+c.getApellido()+" | "+c.getEmail()+" | "+c.getDni()));

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
        }
    }
}
