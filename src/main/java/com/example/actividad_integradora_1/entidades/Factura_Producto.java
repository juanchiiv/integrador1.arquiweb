package entidades;

public class Factura_Producto {
    private int idFactura;
    private int idProducto;
    private int cantidad;

    /**
     * @param idFactura
     * @param idProducto
     * @param cantidad
     * @brief Constructor de Factura_Producto.
     */
    public Factura_Producto(int idFactura, int idProducto, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    /**
     * @return Retorna el int idFactura.
     * @brief Metodo que devuelve el Id de la Factura.
     * Al ejecutarse te retorna la clave unica de la Factura.
     */
    public int getIdFactura() {
        return idFactura;
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
     * @return Retorna el int cantidad.
     * @brief Metodo que devuelve la cantidad.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad
     * @brief Metodo que setea la cantidad.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
