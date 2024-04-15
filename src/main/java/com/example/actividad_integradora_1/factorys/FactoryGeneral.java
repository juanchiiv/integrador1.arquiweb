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

    /**
     * @brief Metodo abstracto getFacturaDAO.
     */
    public abstract Dao_Factura getFacturaDAO();

    /**
     * @brief Metodo abstracto getProductoDAO.
     */
    public abstract Dao_Producto getProductoDAO();

    /**
     * @brief Metodo abstracto getClienteDAO.
     */
    public abstract Dao_Cliente getClienteDAO();

    /**
     * @brief Metodo abstracto getFacturaProductoDao.
     */
    public abstract Dao_FacturaProducto getFacturaProductoDao();

    /**
     * @throws SQLException
     * @brief Metodo abstracto de eliminacion de tablas.
     */
    public abstract void dropTables() throws SQLException;

    /**
     * @throws SQLException
     * @brief Metodo abstracto de creacion de tablas.
     */
    public abstract void createTables() throws SQLException;

    /**
     * @param whichFactory
     * @return Retorna un Factory con la instancia.
     * @brief Metodo estatico getDAOFactory.
     */
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
