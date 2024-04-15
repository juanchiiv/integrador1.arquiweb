package factorys;

import daos.Dao_Cliente;
import daos.Dao_Factura;
import daos.Dao_FacturaProducto;
import daos.Dao_Producto;
import entidades.Cliente;
import entidades.Factura;
import entidades.Producto;

import java.sql.SQLException;

public abstract class FactoryGeneral {
    public static final int DERBY_JDBC = 1;
    public static final int MYSQL_JDBC = 2;

    public abstract Dao_Factura getFacturaDAO();

    public abstract Dao_Producto getProductoDAO();

    public abstract Dao_Cliente getClienteDAO();

    public abstract Dao_FacturaProducto getFacturaProductoDao();

    public abstract void dropTables() throws SQLException;

    public abstract void createTables() throws SQLException;

    public static FactoryGeneral getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case DERBY_JDBC: {
                return DerbyDaoFactory.getInstance();
            }
            case MYSQL_JDBC:
                return MySQLDaoFactory.getInstance();
            default:
                return null;
        }
    }


}
