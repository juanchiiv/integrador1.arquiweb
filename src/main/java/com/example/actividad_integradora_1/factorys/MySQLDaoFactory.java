package factorys;

import daos.Dao_Cliente;
import daos.Dao_Factura;
import daos.Dao_FacturaProducto;
import daos.Dao_Producto;
import entidades.Cliente;
import entidades.Factura;
import entidades.Producto;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDaoFactory extends FactoryGeneral {
    private static MySQLDaoFactory instance = null;
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url_driver = "jdbc:mysql://localhost:3306/base_arquitectura";
    public static Connection conex;

    static {
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();

            conex = DriverManager.getConnection(url_driver, "root", "");
            conex.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConex() {
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();

            conex = DriverManager.getConnection(url_driver, "root", "");
            conex.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return conex;
    }

    public static synchronized MySQLDaoFactory getInstance() {
        if (instance == null) {
            instance = new MySQLDaoFactory();
        }
        return instance;
    }

    public void closeConnection() {
        try {
            conex.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTables() throws SQLException {
        String dropF_Pro = "DROP TABLE IF EXISTS Factura_Producto";
        this.conex.prepareStatement(dropF_Pro).execute();
        this.conex.commit();

        String dropFactura = "DROP TABLE IF EXISTS Factura";
        this.conex.prepareStatement(dropFactura).execute();
        this.conex.commit();

        String dropProducto = "DROP TABLE IF EXISTS producto";
        this.conex.prepareStatement(dropProducto).execute();
        this.conex.commit();

        String dropCliente = "DROP TABLE IF EXISTS cliente";
        this.conex.prepareStatement(dropCliente).execute();
        this.conex.commit();
        System.out.println("Se borro todas las tablas de MySQL");
    }

    public void createTables() throws SQLException {
        //crear primero Cliente luego Producto, luego Factura y finalmente Factura_Producto
        String tCliente = "CREATE TABLE Cliente( " + "idCliente int  NOT NULL, " + "nombre varchar(500)  NOT NULL, " + "email varchar(150)  NOT NULL, " + "PRIMARY KEY (idCliente)" + ")";
        conex.prepareStatement(tCliente).execute();
        conex.commit();

        String tProducto = "CREATE TABLE Producto( " + "idProducto int  NOT NULL, " + "nombre varchar(45)  NOT NULL, " + "valor float  NOT NULL, " + "PRIMARY KEY (idProducto)" + ")";
        conex.prepareStatement(tProducto).execute();
        conex.commit();

        String tFactura = "CREATE TABLE Factura( " + "idFactura int  NOT NULL, " + "idCliente int  NOT NULL, " + "PRIMARY KEY (idFactura), " + "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)" + ")";
        conex.prepareStatement(tFactura).execute();
        conex.commit();

        String tFacturaProducto = "CREATE TABLE Factura_Producto(" + "    idFactura int  NOT NULL," + "    idProducto int  NOT NULL," + "    cantidad int  NOT NULL," + "    PRIMARY KEY (idFactura,idProducto)," + "    FOREIGN KEY (idFactura) REFERENCES Factura(idFactura)," + "    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)" + ")";
        conex.prepareStatement(tFacturaProducto).execute();
        conex.commit();
        System.out.println("Se crearon todas las tablas de MySQL");

    }

    @Override
    public Dao_Factura getFacturaDAO() {

        return new Dao_Factura(conex);
    }

    @Override
    public Dao_Producto getProductoDAO() {

        return new Dao_Producto(conex);
    }

    @Override
    public Dao_Cliente getClienteDAO() {

        return new Dao_Cliente(conex);
    }

    @Override
    public Dao_FacturaProducto getFacturaProductoDao() {
        return new Dao_FacturaProducto(conex);
    }

}
