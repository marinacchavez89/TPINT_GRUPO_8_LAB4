package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import dao.CuentaDAO;
import dominio.Conexion;
import entidades.Cuenta;
import entidades.TipoCuenta;
import dominio.Conexion;


public class CuentaDAOImpl implements CuentaDAO {
	
	public boolean agregarCuenta(Cuenta cuenta) {
		PreparedStatement statement;
		Connection conn = Conexion.getSQLConexion();
		boolean agregado = false;
		
		String sql = "INSERT INTO cuenta (id_cliente, fecha_creacion, id_tipo_cuenta, cbu, saldo, estado) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setInt(1, cuenta.getIdCliente());
			statement.setDate(2, new java.sql.Date(cuenta.getFechaCreación().getTime()));
			statement.setInt(3, cuenta.getIdTipoCuenta().getIdTipoCuenta());
			statement.setString(4, cuenta.getCBU());
			statement.setFloat(5, cuenta.getSaldo());
			statement.setBoolean(6, cuenta.isEstado());
		
			if(statement.executeUpdate() > 0) {
				conn.commit();
				agregado = true;
		}
		
		} catch (SQLException e) {
		    e.printStackTrace();
		    try {
		        conn.rollback();
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		}
		
		return agregado;
	}
	
	public boolean modificarCuenta(Cuenta cuenta) {
		PreparedStatement statement;
		Connection conn = Conexion.getSQLConexion();
		boolean modificado= false;		
		String sql = "UPDATE cuenta SET id_cliente = ?, fecha_creacion = ?, id_tipo_cuenta= ?, \r\n"
				+ "cbu = ?, saldo = ? WHERE nro_cuenta = ?";
		try {
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setInt(1, cuenta.getIdCliente());
			statement.setDate(2, new java.sql.Date(cuenta.getFechaCreación().getTime()));
			statement.setInt(3, cuenta.getIdTipoCuenta().getIdTipoCuenta());
			statement.setString(4, cuenta.getCBU());
			statement.setFloat(5, cuenta.getSaldo());
			//statement.setBoolean(6, cuenta.isEstado());
			statement.setInt(6, cuenta.getNroCuenta());

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
        String sql = "UPDATE cuenta SET estado = FALSE WHERE nro_cuenta = ?";
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
                "INNER JOIN tipo_cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta ";

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
	
	public int obtenerProximoNumeroCuenta() {
	    int proximo = 1;
	    String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA ='banco_db_' AND TABLE_NAME = 'cuenta'";
	    try (Connection conn = Conexion.getSQLConexion();
	         PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        if (rs.next()) {
	            proximo = rs.getInt("AUTO_INCREMENT");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return proximo;
	}
	
	public int contarCuentasActivasPorCliente(int idCliente) {
	    Connection conn = Conexion.getSQLConexion();
	    PreparedStatement stmt;
	    ResultSet rs;
	    int cantidad = 0;

	    String sql = "SELECT COUNT(*) FROM cuenta WHERE id_cliente = ? AND estado = true";

	    try {
	        stmt = (PreparedStatement) conn.prepareStatement(sql);
	        stmt.setInt(1, idCliente);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            cantidad = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return cantidad;
	}
	
	
	public List<Cuenta> obtenerXIdCliente (int idCliente) {
		List<Cuenta> cuentasXCliente = new ArrayList<>();
		
		PreparedStatement statement;
        ResultSet rs;
        Connection conn = Conexion.getSQLConexion();
        String sql = "select c.nro_cuenta, c.fecha_creacion, c.id_tipo_cuenta, tc.desc_tipo_cuenta, c.cbu, c.saldo from cuenta c inner join tipo_cuenta tc on c.id_tipo_cuenta = tc.id_tipo_cuenta where c.estado= 1 and c.id_cliente= ?" ;

        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, idCliente);            
            rs = statement.executeQuery();

            while (rs.next()) {
            	Cuenta cuenta = new Cuenta();
            	cuenta.setNroCuenta(rs.getInt("nro_cuenta"));
                cuenta.setFechaCreación(rs.getDate("fecha_creacion"));
                cuenta.setCBU(rs.getString("cbu"));
                cuenta.setSaldo(rs.getFloat("saldo"));
                
                TipoCuenta tipocuenta = new TipoCuenta();
                tipocuenta.setIdTipoCuenta(rs.getInt("id_tipo_cuenta"));
                tipocuenta.setDescripcion(rs.getString("desc_tipo_cuenta")); 
                cuenta.setTipoCuenta(tipocuenta);
                cuentasXCliente.add(cuenta);
            	}
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentasXCliente;
	}

	@Override
	public Cuenta obtenerPorNroCuenta(int nroCuenta) {
		Cuenta cuenta = null;
	    String sql = "SELECT * FROM cuenta WHERE nro_cuenta = ?";
	    try (Connection conn = Conexion.getSQLConexion();
	         PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
	        stmt.setInt(1, nroCuenta);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            cuenta = new Cuenta();
	            cuenta.setNroCuenta(rs.getInt("nro_cuenta"));
	            cuenta.setIdCliente(rs.getInt("id_cliente"));
	            cuenta.setEstado(rs.getBoolean("estado"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cuenta;
	}

	@Override
	public boolean actualizarEstadoCuenta(Cuenta cuenta) {
		 String sql = "UPDATE cuenta SET estado = ? WHERE nro_cuenta = ?";
		    try (Connection conn = Conexion.getSQLConexion();
		         PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
		        stmt.setBoolean(1, cuenta.isEstado());
		        stmt.setInt(2, cuenta.getNroCuenta());
		        if (stmt.executeUpdate() > 0) {
		            conn.commit();
		            return true;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        try {
		            Conexion.getSQLConexion().rollback();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		    return false;
	}

	@Override
	public List<Cuenta> listarCuentasFiltradas(Boolean estado, int idCliente) {
		List<Cuenta> cuentas = new ArrayList<>();
	    PreparedStatement statement;
	    ResultSet rs;
	    Connection conn = Conexion.getSQLConexion();

	    StringBuilder sql = new StringBuilder("SELECT c.nro_cuenta, c.id_cliente, c.fecha_creacion, c.id_tipo_cuenta, " +
	            "tc.desc_tipo_cuenta, c.cbu, c.saldo, c.estado " +
	            "FROM cuenta c " +
	            "INNER JOIN tipo_cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta WHERE 1=1 ");

	    if (estado != null) {
	        sql.append(" AND c.estado = ").append(estado ? "1" : "0");
	    }

	    if (idCliente > 0) {
	        sql.append(" AND c.id_cliente = ").append(idCliente);
	    }

	    try {
	        statement = (PreparedStatement) conn.prepareStatement(sql.toString());
	        rs = statement.executeQuery();

	        while (rs.next()) {
	            Cuenta cuenta = new Cuenta();
	            cuenta.setNroCuenta(rs.getInt("nro_cuenta"));
	            cuenta.setIdCliente(rs.getInt("id_cliente"));
	            cuenta.setFechaCreación(rs.getDate("fecha_creacion"));
	            cuenta.setCBU(rs.getString("cbu"));
	            cuenta.setSaldo(rs.getFloat("saldo"));
	            cuenta.setEstado(rs.getBoolean("estado"));

	            TipoCuenta tipo = new TipoCuenta();
	            tipo.setIdTipoCuenta(rs.getInt("id_tipo_cuenta"));
	            tipo.setDescripcion(rs.getString("desc_tipo_cuenta"));
	            cuenta.setTipoCuenta(tipo);

	            cuentas.add(cuenta);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return cuentas;
	}
	
	@Override
	public boolean actualizarSaldo(Cuenta cuenta) {
	    boolean exito = false;
	    PreparedStatement stmt = null;
	    Connection conn = null;

	    try {
	        conn = Conexion.getSQLConexion();
	        String query = "UPDATE cuenta SET saldo = ? WHERE nro_cuenta = ?";
	        stmt = (PreparedStatement) conn.prepareStatement(query);
	        stmt.setFloat(1, cuenta.getSaldo());
	        stmt.setInt(2, cuenta.getNroCuenta());

	        int filas = stmt.executeUpdate();
	        exito = (filas > 0);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return exito;
	}
	
	public Cuenta obtenerPorCBU(String cbu) {
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    Cuenta cuenta = null;
	    
	    try {
	        conn = Conexion.getSQLConexion();
	        stmt = conn.prepareStatement("SELECT * FROM cuenta WHERE CBU = ?");
	        stmt.setString(1, cbu);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            cuenta = new Cuenta();
	            cuenta.setNroCuenta(rs.getInt("nro_cuenta"));
	            cuenta.setCBU(rs.getString("cbu"));
	            cuenta.setSaldo(rs.getFloat("saldo"));
	            cuenta.setEstado(rs.getBoolean("estado"));
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }

	    return cuenta;
	}

}