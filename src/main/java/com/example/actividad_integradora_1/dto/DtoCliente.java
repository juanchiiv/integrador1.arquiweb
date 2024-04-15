package dto;

import entidades.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class DtoCliente {
    private int id;
    private String nombre;
    private String email;
    private int cantidad;

    public DtoCliente(int id, String nombre, String email, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getCantidad() {
        return cantidad;
    }

}