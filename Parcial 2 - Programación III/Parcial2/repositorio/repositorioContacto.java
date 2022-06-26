package Parcial2.repositorio;

import Parcial2.modelo.Contacto;

import java.util.List;

public interface repositorioContacto<T>{
    List<T> listar();

    T porId(int id);

    void guardar (T t); //representa el Insert y el update

    void eliminar(Long id);


}