package Parcial2.repositorio;

import Parcial2.recursoConexion.ConexionBaseDatos;
import Parcial2.modelo.Contacto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class repositorioContactoImpl implements repositorioContacto<Contacto> {

    //Metodo privado para establecer la conexion a la base de datos
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }

    @Override
    public List<Contacto> listar() {
        List<Contacto> contactos = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM contactos")) {
            while (rs.next()) {
                Contacto c = crearContacto(rs);
                contactos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactos;
    }

    private Contacto crearContacto(ResultSet rs) throws SQLException {
        Contacto c = new Contacto();
        c.setId(rs.getLong("id"));
        c.setNombre(rs.getString("nombre"));
        c.setApellido(rs.getString("apellido"));
        c.setEmail(rs.getString("email"));
        c.setDni(rs.getInt("dni"));
        return c;
    }


    @Override

    public Contacto porId(int id) {
        Contacto contacto = null;
        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM contactos WHERE id=?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                contacto = crearContacto(rs);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacto;
    }


    @Override
    public void guardar(Contacto contacto) {
        String sql;
        if (contacto.getId() != null && contacto.getId() > 0) {
            sql = "UPDATE contactos SET nombre=?, apellido=?, email=?, dni=?  WHERE id=?";
        } else {
            sql = "INSERT INTO contactos(nombre, apellido, email, dni) VALUES (?,?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, contacto.getNombre());
            stmt.setString(2, contacto.getApellido());
            stmt.setString(3, contacto.getEmail());
            stmt.setInt(4, contacto.getDni());

            if (contacto.getId() != null && contacto.getId() > 0) {
                stmt.setLong(5, contacto.getId());

            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void eliminar(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM contactos WHERE id=?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}