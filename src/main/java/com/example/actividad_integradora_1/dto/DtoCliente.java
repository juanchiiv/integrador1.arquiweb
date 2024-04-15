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

    /**
     * @param id
     * @param nombre
     * @param email
     * @param cantidad
     * @brief Constructor de DtoCliente.
     */
    public DtoCliente(int id, String nombre, String email, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.cantidad = cantidad;
    }

    /**
     * @return Retorna un int que es el ID.
     * @brief Metodo que devuelve el ID.
     * Se obtiene la clave unica del cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * @return Retorna un String que es el nombre.
     * @brief Metodo que devuelve el nombre.
     * Se obtiene el nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return Retorna un String que es el email.
     * @brief Metodo que devuelte el email.
     * Se obtiene el email del cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Retorna un int que es la cantidad de facturas que emitio el cliente.
     * @brief Metodo que devuelve la cantidad.
     * Se obtiene la cantidad de facturas del cliente.
     */
    public int getCantidad() {
        return cantidad;
    }
}