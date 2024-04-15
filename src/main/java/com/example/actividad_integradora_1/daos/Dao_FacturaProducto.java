package daos;

import entidades.Cliente;
import entidades.Factura_Producto;
import entidades.Producto;
import factorys.MySQLDaoFactory;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dao_FacturaProducto {
    private Connection conex;

    public Dao_FacturaProducto(Connection conn) {

        this.conex = conn;
    }

    public void insertFacturaProducto(CSVParser datFp) throws SQLException {
        this.conex = MySQLDaoFactory.getConex();
        for (CSVRecord row : datFp) {
            int idFac = Integer.parseInt(row.get("idFactura"));
            int idProd = Integer.parseInt(row.get("idProducto"));
            int cant = Integer.parseInt(row.get("cantidad"));
            Factura_Producto nuevo = new Factura_Producto(idFac, idProd, cant);
            insertFacturaProductoIndi(nuevo);
        }
    }

    public void insertFacturaProductoIndi(Factura_Producto fp) throws SQLException {
        int idF = fp.getIdFactura();
        int idP = fp.getIdProducto();
        int cant = fp.getCantidad();
        String insert = "INSERT INTO Factura_Producto (idFactura,idProducto,cantidad) VALUES(?,?,?)";
        PreparedStatement ps = conex.prepareStatement(insert);
        ps.setInt(1, idF);
        ps.setInt(2, idP);
        ps.setInt(3, cant);
        ps.executeUpdate();
        ps.close();
        conex.commit();
    }
}
