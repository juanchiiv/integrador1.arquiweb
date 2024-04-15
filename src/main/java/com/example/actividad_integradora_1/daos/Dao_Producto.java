package daos;

import entidades.Cliente;
import entidades.Producto;
import factorys.MySQLDaoFactory;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao_Producto {
    private Connection conex;

    public Dao_Producto(Connection conn) {

        this.conex = conn;
    }

    public Producto getProductMasRecaudo() throws SQLException {
        Producto resultado = null;
        String sqlSentencia = "SELECT p.*, SUM(fp.cantidad * p.valor) AS recaudacion_total\n" +
                "FROM Producto p\n" +
                "JOIN Factura_Producto fp ON fp.idProducto = p.idProducto\n" +
                "GROUP BY p.idProducto\n" +
                "ORDER BY recaudacion_total DESC\n" +
                "LIMIT 1;";

        PreparedStatement ps = conex.prepareStatement(sqlSentencia);
        ResultSet resp = ps.executeQuery();
        while (resp.next()) {
            resultado = new Producto(resp.getInt(1), resp.getString(2), resp.getFloat(3));
        }

        return resultado;
    }

    public void insertProducto(CSVParser prod) throws SQLException {
        this.conex = MySQLDaoFactory.getConex();
        for (CSVRecord row : prod) {
            int idProd = Integer.parseInt(row.get("idProducto"));
            String nombre = row.get("nombre");
            Float valor = Float.valueOf(row.get("valor"));
            Producto nuevo = new Producto(idProd, nombre, valor);
            InsertarProductoIndi(nuevo);
        }
    }

    public void InsertarProductoIndi(Producto prod) throws SQLException {
        int id = prod.getIdProducto();
        String nombre = prod.getNombre();
        float anio = prod.getValor();
        String insert = "INSERT INTO Producto (idProducto,nombre,valor) VALUES(?,?,?)";
        PreparedStatement ps = conex.prepareStatement(insert);
        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setFloat(3, anio);
        ps.executeUpdate();
        ps.close();
        conex.commit();

    }
}
