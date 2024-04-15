package entidades;

import java.sql.Connection;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String email;

    /**
     * @param idCliente
     * @param nombre
     * @param email
     * @brief Constructor de Cliente.
     */
    public Cliente(int idCliente, String nombre, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
    }

    /**
     * @return Retorna un int que es el ID.
     * @brief Metodo que devuelve el ID.
     * Se obtiene la clave unica del cliente.
     */
    public int getIdCliente() {
        return idCliente;
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
     * @param nombre
     * @brief Metodo que setea el nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param email
     * @brief Metodo que setea el email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return
     * @brief Metodo toString de Java.
     */
    @Override
    public String toString() {
        return "Cliente {" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
