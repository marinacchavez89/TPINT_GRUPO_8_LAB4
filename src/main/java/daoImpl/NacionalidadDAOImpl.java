package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.NacionalidadDAO;
import dominio.Conexion;
import entidades.Nacionalidad;

public class NacionalidadDAOImpl implements NacionalidadDAO {

	@Override
	public List<Nacionalidad> obtenerTodas() {
		List<Nacionalidad> nacionalidad = new ArrayList<>();

        PreparedStatement statement;
        ResultSet rs;
        Connection conn = Conexion.getSQLConexion();
        
        String sql = "SELECT id_nacionalidad, desc_nacionalidad FROM nacionalidad";

        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
            	Nacionalidad nac = new Nacionalidad();
            	nac.setIdNacionalidad(rs.getInt("id_nacionalidad"));
            	nac.setDescripcion(rs.getString("desc_nacionalidad"));

            	nacionalidad.add(nac);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nacionalidad;
	}

}
