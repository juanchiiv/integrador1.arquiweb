package entidades;

import java.sql.Connection;

public class Factura {
    private int idFactura;
    private int idCliente;

    /**
     * @param idFactura
     * @param idCli
     * @brief Constructor de Factura.
     */
    public Factura(int idFactura, int idCli) {
        this.idFactura = idFactura;
        this.idCliente = idCli;
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
     * @return Retorna el int idCliente.
     * @brief Metodo que devuelve el Id del Cliente.
     * Al ejecutarse te retorna la clave unica del Cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }
}
