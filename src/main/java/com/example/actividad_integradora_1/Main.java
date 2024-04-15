import daos.Dao_Cliente;
import daos.Dao_Factura;
import daos.Dao_FacturaProducto;
import daos.Dao_Producto;
import dto.DtoCliente;
import entidades.Cliente;
import entidades.Producto;
import factorys.DerbyDaoFactory;
import factorys.FactoryGeneral;
import factorys.MySQLDaoFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {


    public static void main(String[] args) throws SQLException, IOException {
        //consigna 1)
        FactoryGeneral mysql = FactoryGeneral.getDAOFactory(2);
        MySQLDaoFactory gestorSQL = (MySQLDaoFactory) FactoryGeneral.getDAOFactory(2);

        //Descomentar esto para borrar las tablas.
        gestorSQL.dropTables();
        //borra todas las tablas exitosamente

        //Comentar esto una vez creada las tablas.
        gestorSQL.createTables();

        //consigna 2)
        //Se crean los Daos que permiten subir los CSV a la base de datos, una vez creadas y cargadas las tablas, comentarlo para evitar errores.
        Dao_Cliente tablaCliente = mysql.getClienteDAO();
        Dao_Producto tablaProducto = mysql.getProductoDAO();
        Dao_Factura tablaFactura = mysql.getFacturaDAO();
        Dao_FacturaProducto tablaFacPro = mysql.getFacturaProductoDao();

        CSVParser datClientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/resources/csv/clientes.csv"));

        CSVParser datProducto = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/resources/csv/productos.csv"));

        CSVParser datFactura = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/resources/csv/facturas.csv"));

        CSVParser datFacturaProduc = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/resources/csv/facturas-productos.csv"));

        tablaCliente.insertCliente(datClientes);
        tablaProducto.insertProducto(datProducto);
        tablaFactura.insertFactura(datFactura);
        tablaFacPro.insertFacturaProducto(datFacturaProduc);

        //consigna 3)=
        Dao_Producto daoProduc = gestorSQL.getProductoDAO();
        Producto producMasRecaudo = daoProduc.getProductMasRecaudo();
        System.out.println("----------------------------------");
        System.out.println("El producto que mas recaudo es: ");
        System.out.println(daoProduc.getProductMasRecaudo());
        System.out.println();

        //consigna 4)=
        Dao_Cliente clienteDto = gestorSQL.getClienteDAO();
        ArrayList<DtoCliente> listClientes = clienteDto.getListClientesFacturacion();
        clienteDto.imprimirClientes(listClientes);
    }
}
