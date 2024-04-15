package daos;

import entidades.Cliente;
import entidades.Factura;
import entidades.Factura_Producto;
import factorys.MySQLDaoFactory;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dao_Factura {
    private Connection conex;

    public Dao_Factura(Connection conn) {
        this.conex = conn;
    }

    public void insertFactura(CSVParser fact) throws SQLException {
        this.conex = MySQLDaoFactory.getConex();
        for (CSVRecord row : fact) {
            int idFactura = Integer.parseInt(row.get("idFactura"));
            int idCliente = Integer.parseInt(row.get("idCliente"));
            Factura nuevo = new Factura(idFactura, idCliente);
            InsertarFacturaIndi(nuevo);
        }

    }

    public void InsertarFacturaIndi(Factura fact) throws SQLException {
        int idC = fact.getIdCliente();
        int idF = fact.getIdFactura();
        String insert = "INSERT INTO Factura (idFactura,idCliente) VALUES (?,?)";
        PreparedStatement ps = conex.prepareStatement(insert);
        ps.setInt(1, idF);
        ps.setInt(2, idC);
        ps.executeUpdate();
        ps.close();
        conex.commit();
    }
}
