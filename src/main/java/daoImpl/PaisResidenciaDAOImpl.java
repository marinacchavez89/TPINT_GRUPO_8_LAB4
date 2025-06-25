package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import dao.PaisResidenciaDAO;
import dominio.Conexion;
import entidades.PaisResidencia;

public class PaisResidenciaDAOImpl implements PaisResidenciaDAO {

	@Override
    public List<PaisResidencia> obtenerTodos() {
        List<PaisResidencia> paises = new ArrayList<>();

        PreparedStatement statement;
        ResultSet rs;
        Connection conn = Conexion.getSQLConexion();
        
        String sql = "SELECT id_pais_residencia, desc_pais_residencia FROM pais_residencia";

        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                PaisResidencia paisResidencia = new PaisResidencia();
                paisResidencia.setIdPaisResidencia(rs.getInt("id_pais_residencia"));
                paisResidencia.setDescripcion(rs.getString("desc_pais_residencia"));

                paises.add(paisResidencia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paises;
    }
}

