package daoImpl;

import entidades.TipoCuenta; 
import dao.TipoCuentaDAO;
import dominio.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoCuentaDAOImpl implements TipoCuentaDAO {
	 @Override
	    public List<TipoCuenta> listar() {
	        List<TipoCuenta> tipos = new ArrayList<>();
	        Connection conn = Conexion.getSQLConexion();
	        String sql = "SELECT id_tipo_cuenta, desc_tipo_cuenta FROM tipo_cuenta";

	        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                TipoCuenta tipo = new TipoCuenta();
	                tipo.setIdTipoCuenta(rs.getInt("id_tipo_cuenta"));
	                tipo.setDescripcion(rs.getString("desc_tipo_cuenta"));
	                tipos.add(tipo);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return tipos;
	    }

	    @Override
	    public TipoCuenta obtenerTipoCuentaXId(int id) {
	        Connection conn = Conexion.getSQLConexion();
	        String sql = "SELECT id_tipo_cuenta, desc_tipo_cuenta FROM tipo_cuenta WHERE id_tipo_cuenta = ?";
	        TipoCuenta tipo = null;

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                tipo = new TipoCuenta();
	                tipo.setIdTipoCuenta(rs.getInt("id_tipo_cuenta"));
	                tipo.setDescripcion(rs.getString("desc_tipo_cuenta"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return tipo;
	    }
}
