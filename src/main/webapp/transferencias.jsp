<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transferencias</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.css" rel="stylesheet">
<link href="css/estilos.css" rel="stylesheet">

</head>
<body>
<jsp:include page="navbar.jsp"/>

 <div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="form-box p-4 shadow rounded">
      <h2 class="text-center mb-4">Transferencia</h2>
      <form>
        <div class="mb-3">
          <label for="cuentaReceptora" class="form-label">CBU destino:</label>
          <select id="cuentaReceptora" class="form-select">
            <option>CBU 1234567890</option>
            <option>CBU 0987654321</option>
          </select>
        </div>

        <div class="mb-3">
          <label for="monto" class="form-label">Monto:</label>
          <input type="number" class="form-control" id="monto" step="0.01" min="1" required>
        </div>

        <div class="mb-3">
          <label for="cuentaOrigen" class="form-label">Cuenta origen:</label>
          <select id="cuentaOrigen" class="form-select">
            <option>Cuenta 001 - $50,000</option>
            <option>Cuenta 002 - $12,500</option>
          </select>
        </div>

        <div class="d-grid mt-5 mb-5">
          <button type="submit" class="btn btn-primary">Transferir</button>
        </div>
        
        <div class="row mt-3">
            <div class="col">
                <a href="inicioCliente.jsp" class="btn btn-primary">Volver</a>
            </div>
        </div>
        
      </form>
    </div>
  </div>
  
<jsp:include page="footer.jsp" />
</body>
</html>