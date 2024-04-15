package entidades;

import java.sql.Connection;

public class Factura {
    private int idFactura;
    private int idCliente;

    public Factura(int idFactura, int idCli) {
        this.idFactura = idFactura;
        this.idCliente=idCli;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }
}
