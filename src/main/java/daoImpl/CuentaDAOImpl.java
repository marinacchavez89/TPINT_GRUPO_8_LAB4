package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import dao.CuentaDAO;
import dominio.Conexion;
import entidades.Cuenta;
import entidades.TipoCuenta;

public class CuentaDAOImpl implements CuentaDAO {
	
	public boolean agregarCuenta(Cuenta cuenta, int idCliente, TipoCuenta idTipoCuenta) {
		PreparedStatement statement;
		Connection conn = Conexion.getSQLConexion();
		boolean agregado = false;
		
		String sql = "INSERT INTO cuenta (nro_cuenta, id_cliente, fecha_creacion, id_tipo_cuenta, cbu, saldo, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setInt(1, cuenta.getNroCuenta());
			statement.setInt(2, cuenta.getIdCliente());
			statement.setDate(3, new java.sql.Date(cuenta.getFechaCreación().getTime()));
			statement.setInt(4, cuenta.getIdTipoCuenta().getIdTipoCuenta());
			statement.setString(5, cuenta.getCBU());
			statement.setFloat(6, cuenta.getSaldo());
			statement.setBoolean(7, cuenta.isEstado());
		

			if(statement.executeUpdate() > 0) {
				conn.commit();
				agregado = true;
		}
		
		}catch(SQLException e) {
			 e.printStackTrace();
		 }
		
		try {
			conn.rollback();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
		return agregado;
	}
	
	public boolean modificarCuenta(Cuenta cuenta) {
		PreparedStatement statement;
		Connection conn = Conexion.getSQLConexion();
		boolean modificado= false;		
		String sql = "UPDATE cuenta SET id_cliente = ?, fecha_creacion = ?, id_tipo_cuenta= ?, \r\n"
				+ "cbu = ?, saldo = ?, estado = ? WHERE nro_cuenta = ?";
		try {
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setInt(1, cuenta.getIdCliente());
			statement.setDate(2, new java.sql.Date(cuenta.getFechaCreación().getTime()));
			statement.setInt(3, cuenta.getIdTipoCuenta().getIdTipoCuenta());
			statement.setString(4, cuenta.getCBU());
			statement.setFloat(5, cuenta.getSaldo());
			statement.setBoolean(6, cuenta.isEstado());
			statement.setInt(7, cuenta.getNroCuenta());

            if (statement.executeUpdate() > 0) {
                conn.commit();
                modificado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return modificado;
	}
	
	
	public boolean eliminarCuenta(int nroCuenta) {
		PreparedStatement statement;
        Connection conn = Conexion.getSQLConexion();
        boolean eliminado = false;
		String sql = "DELETE FROM cuenta WHERE nro_cuenta = ?";
		try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, nroCuenta);

            if (statement.executeUpdate() > 0) {
                conn.commit();
                eliminado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return eliminado;
	}
	
	public List<Cuenta> listarCuentas(){
		List<Cuenta> cuentas = new ArrayList<>();
		
		PreparedStatement statement;
        ResultSet rs;
        Connection conn = Conexion.getSQLConexion();
        String sql = "SELECT c.nro_cuenta, c.id_cliente, c.fecha_creacion, c.id_tipo_cuenta, " +
                "tc.desc_tipo_cuenta, c.cbu, c.saldo, c.estado " +
                "FROM cuenta c " +
                "INNER JOIN tipo_cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta";


        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
            	Cuenta cuenta = new Cuenta();
            	cuenta.setNroCuenta(rs.getInt("nro_cuenta"));
                cuenta.setIdCliente(rs.getInt("id_cliente"));
                cuenta.setFechaCreación(rs.getDate("fecha_creacion"));
                cuenta.setCBU(rs.getString("cbu"));
                cuenta.setSaldo(rs.getFloat("saldo"));
                cuenta.setEstado(rs.getBoolean("estado"));
                
                TipoCuenta tipocuenta = new TipoCuenta();
                tipocuenta.setIdTipoCuenta(rs.getInt("id_tipo_cuenta"));
                tipocuenta.setDescripcion(rs.getString("desc_tipo_cuenta")); 
                cuenta.setTipoCuenta(tipocuenta);
                cuentas.add(cuenta);
            	}
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
		}
	}
