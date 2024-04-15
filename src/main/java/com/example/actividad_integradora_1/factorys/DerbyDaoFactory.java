package factorys;

import daos.Dao_Cliente;
import daos.Dao_Factura;
import daos.Dao_FacturaProducto;
import daos.Dao_Producto;
import entidades.Cliente;
import entidades.Factura;
import entidades.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyDaoFactory extends FactoryGeneral {
    private static DerbyDaoFactory instance = null;
    public static final String driver = "jdbc:derby:jdbc_db;create=true";
    public static Connection conex;

    static {
        try {
            conex = DriverManager.getConnection(driver);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized DerbyDaoFactory getInstance() {
        if (instance == null) {
            instance = new DerbyDaoFactory();
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

    @Override
    public void dropTables() throws SQLException {
        String dropF_Pro = "DROP TABLE Factura_Producto";
        this.conex.prepareStatement(dropF_Pro).execute();
        this.conex.commit();
        String dropFactura = "DROP TABLE Factura";
        this.conex.prepareStatement(dropFactura).execute();
        this.conex.commit();
        String dropProducto = "DROP TABLE Producto";
        this.conex.prepareStatement(dropProducto);
        this.conex.commit();
        String dropCliente = "DROP TABLE Cliente";
        this.conex.prepareStatement(dropCliente);
        this.conex.commit();
        System.out.println("Se borro todas las tablas de Derby");
    }

    public void dropTables2() throws SQLException {
        try {
            String dropF_Pro = "DROP TABLE Factura_Producto";
            this.conex.prepareStatement(dropF_Pro).execute();
            this.conex.commit();
            System.out.println("Tabla Factura_Producto eliminada");
        } catch (SQLException e) {
            System.out.println("La tabla Factura_Producto no existe");
        }

        try {
            String dropFactura = "DROP TABLE Factura";
            this.conex.prepareStatement(dropFactura).execute();
            this.conex.commit();
            System.out.println("Tabla Factura eliminada");
        } catch (SQLException e) {
            System.out.println("La tabla Factura no existe");
        }

        // Repetir este proceso para otras tablas...
    }

    @Override
    public void createTables() throws SQLException {

        //crear primero Cliente luego Producto, luego Factura y finalmente Factura_Producto
        String tCliente = "CREATE TABLE Cliente("
                + "id_Cliente int  NOT NULL,"
                + "nombre varchar(500)  NOT NULL,"
                + "email varchar(150)  NOT NULL,"
                + "CONSTRAINT Cliente_pk PRIMARY KEY (id_Cliente)"
                + ")";
        conex.prepareStatement(tCliente).execute();
        conex.commit();
        String tProducto = "CREATE TABLE Producto(" +
                "    idProducto int  NOT NULL," +
                "    nombre varchar(45)  NOT NULL," +
                "    valor float  NOT NULL," +
                "    CONSTRAINT Producto_pk PRIMARY KEY (idProducto)" +
                ")";
        conex.prepareStatement(tProducto).execute();
        conex.commit();
        String tFactura = "CREATE TABLE Factura (" +
                "    idFactura int  NOT NULL," +
                "    idCliente int  NOT NULL," +
                "    CONSTRAINT Factura_pk PRIMARY KEY (idFactura)" +
                ")";
        conex.prepareStatement(tFactura).execute();
        conex.commit();
        String tFacturaProducto = "CREATE TABLE Factura_Producto(" +
                "    idFactura int  NOT NULL," +
                "    idProducto int  NOT NULL," +
                "    cantidad int  NOT NULL," +
                "    CONSTRAINT Factura_Producto_ak UNIQUE (idFactura, idProducto) NOT DEFERRABLE  INITIALLY IMMEDIATE,\n" +
                "    CONSTRAINT Factura_Producto_pk PRIMARY KEY (idFactura,idProducto)" +
                ")";
        conex.prepareStatement(tFacturaProducto).execute();
        conex.commit();
        //AHORA LA CREACION DE LAS CLAVES FORANEAS
        String aK_1 = "ALTER TABLE Factura ADD CONSTRAINT Factura_Cliente_FK" +
                "    FOREIGN KEY (idCliente)" +
                "    REFERENCES Cliente (id_Cliente)" +
                "    NOT DEFERRABLE" +
                "    INITIALLY IMMEDIATE";
        conex.prepareStatement(aK_1).execute();
        conex.commit();
        String aK_2 = "ALTER TABLE Factura_Producto ADD CONSTRAINT Factura_Producto_FK" +
                "    FOREIGN KEY (idFactura)" +
                "    REFERENCES Factura (idFactura) " +
                "    NOT DEFERRABLE " +
                "    INITIALLY IMMEDIATE";
        conex.prepareStatement(aK_2).execute();
        conex.commit();
        String aK_3 = "ALTER TABLE Factura_Producto ADD CONSTRAINT Factura_y_Producto_FK" +
                "    FOREIGN KEY (idProducto)" +
                "    REFERENCES Producto (idProducto)" +
                "    NOT DEFERRABLE " +
                "    INITIALLY IMMEDIATE";
        conex.prepareStatement(aK_3).execute();
        conex.commit();
        System.out.println("Se crearon todas las tablas de MySQL");

    }
}
