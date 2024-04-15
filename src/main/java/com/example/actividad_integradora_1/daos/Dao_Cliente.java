package daos;

import dto.DtoCliente;
import entidades.Cliente;
import entidades.Producto;
import factorys.MySQLDaoFactory;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Dao_Cliente {
    private Connection conex;

    public Dao_Cliente(Connection conn) {

        this.conex = conn;
    }

    public void insertCliente(CSVParser clien) throws SQLException {
        this.conex = MySQLDaoFactory.getConex();
        for (CSVRecord row : clien) {
            int idCliente = Integer.parseInt(row.get("idCliente"));
            String nombre = row.get("nombre");
            String email = row.get("email");
            Cliente nuevo = new Cliente(idCliente, nombre, email);
            InsertarClienteIndi(nuevo);
        }

    }

    public void InsertarClienteIndi(Cliente client) throws SQLException {
        int id = client.getIdCliente();
        String nombre = client.getNombre();
        String email = client.getEmail();
        String insert = "INSERT INTO Cliente (idCliente,nombre,email) VALUES (?,?,?)";
        PreparedStatement ps = conex.prepareStatement(insert);
        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setString(3, email);
        ps.executeUpdate();
        ps.close();
        conex.commit();
    }

    public ArrayList<DtoCliente> getListClientesFacturacion() throws SQLException {
        ArrayList<DtoCliente> clientes = new ArrayList<DtoCliente>();
        String sqlSentencia = "SELECT c.idCliente, c.nombre,c.email, SUM(fp.cantidad) AS total_facturado " +
                "FROM Cliente c " +
                "LEFT JOIN Factura f ON c.idCliente = f.idCliente " +
                "LEFT JOIN Factura_Producto fp ON f.idFactura = fp.idFactura " +
                "GROUP BY c.idCliente, c.nombre " +
                "ORDER BY total_facturado DESC " +
                "LIMIT 10";
        PreparedStatement ps = conex.prepareStatement(sqlSentencia);
        ResultSet resp = ps.executeQuery();
        while (resp.next()) {
            int id = resp.getInt(1);
            String nombre = resp.getString(2);
            String email = resp.getString(3);
            int cant = resp.getInt(4);
            DtoCliente cli = new DtoCliente(id, nombre, email, cant);
            clientes.add(cli);

        }
        return clientes;
    }

    public static void imprimirClientes(ArrayList<DtoCliente> listClientes) {
        if (listClientes != null) {
            System.out.println("-----------------------------------------------");
            System.out.println("Los 10 clietes que mas facturaron son: ");
            System.out.println();
            Iterator<DtoCliente> it = listClientes.iterator();
            while (it.hasNext()) {
                DtoCliente c = it.next();
                System.out.println("Cliente---> Nombre: " + c.getNombre() + ", ID: " + c.getId()
                        + " Email: " + c.getEmail() + ", Cantidad:  " + c.getCantidad());

            }
        }
    }
}
