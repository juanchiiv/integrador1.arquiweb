package entidades;

import java.sql.Connection;

public class Producto {
    private int idProducto;
    private String nombre;
    private float valor;

    /**
     * @param idProducto
     * @param nombre
     * @param valor
     * @brief Constructor de Producto.
     */
    public Producto(int idProducto, String nombre, float valor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
    }

    /**
     * @return Retorna el int idProducto.
     * @brief Metodo que devuelve el Id del Producto.
     * Al ejecutarse te retorna la clave unica del Producto.
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * @return Retorna un String que es el nombre.
     * @brief Metodo que devuelve el nombre.
     * Se obtiene el nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return Retorna un float que es el valor.
     * @brief Metodo que devuelve el valor.
     * Se obtiene el valor del producto.
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param nombre
     * @brief Metodo que setea el nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param valor
     * @brief Metodo que setea el valor del producto.
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * @return
     * @brief Metodo toString de Java.
     */
    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                '}';
    }
}
