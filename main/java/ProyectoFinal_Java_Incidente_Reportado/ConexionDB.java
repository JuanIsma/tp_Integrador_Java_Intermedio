package ProyectoFinal_Java_Incidente_Reportado;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConexionDB {

    // Parámetros de conexión a la base de datos
    private String url = "jdbc:mysql://localhost:3308/soporte";
    private String usuario = "root";
    private String contraseña = "";
    
    
    
    //--------------------CONEXIÓN-------------------------------------------------------------------------------------------  

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException ex) {
            System.out.println("Error en la conexion de la BD " + ex);
        }
        return null;
    }
    

    //---------------------ALTA TECNICO-------------------------------------------------------------------------------------------    

    public void altaTecnicoDB(Tecnico tec1) {

        String consulta = "INSERT INTO tecnico( `cuit-tec`, `nom-ape`, especialidad, `medio-comu`, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
            PreparedStatement sqlUp = connection.prepareStatement(consulta)) { 
            sqlUp.setString(1, tec1.getCuitTecnico());
            sqlUp.setString(2, tec1.getNombre());
            
           // Convertir la lista de especialidades a un string separado por comas
           String especialidadesConcatenadas = String.join(",", tec1.getEspecialidades());
            sqlUp.setString(3, especialidadesConcatenadas);
            sqlUp.setString(4, tec1.getMedioNotificacionPreferido());
            sqlUp.setString(5, tec1.getDisponible());
            sqlUp.executeUpdate();
            System.out.println("La DB/TABLA TECNICO se actualizó con éxito");
        } catch (SQLException obj) {
            System.out.println("Error en el insert de la tabla Tecnico" + obj);
            obj.printStackTrace();
        }
    }

    
    
    //-----------------------DAR ALTA CLIENTE-----------------------------------------------------------------------------------------    

    public void altaClienteDB(Cliente cliente) {

        String consulta = "INSERT INTO cliente( cuit, razonSocial, `nom-apel`, direc, mail, cel,`tel-fijo`,servicio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
            PreparedStatement sqlUp = connection.prepareStatement(consulta)) {
            sqlUp.setString(1, cliente.getCuit());
            sqlUp.setString(2, cliente.getRazonSocial());
            sqlUp.setString(3, cliente.getNomApe());
            sqlUp.setString(4, cliente.getDire());
            sqlUp.setString(5, cliente.getMail());
            sqlUp.setInt(6, cliente.getCelular());
            sqlUp.setInt(7, cliente.getTelFijo());
            sqlUp.setString(8, cliente.getServicio());
            sqlUp.executeUpdate();
            System.out.println("La DB/TABLA CLIENTE se actualizó con éxito");
        } catch (SQLException obj) {
            System.out.println("Error en el insert de la tabla Tecnico" + obj);
            obj.printStackTrace();
        }
    }

    
    //---------------------------ELIMINAR CLIENTE -------------------------------------------------------------------------------------
 
    public void eliminarCliente(int idCliente) {
        String consulta = "DELETE FROM cliente WHERE `id-cliente` = ?";

        try (Connection conexion = getConnection(); 
            PreparedStatement sqlDelete = conexion.prepareStatement(consulta)) {
            sqlDelete.setInt(1, idCliente);
            int filasEliminadas = sqlDelete.executeUpdate();
            
            

            if (filasEliminadas > 0) {
                System.out.println("El cliente con ID " + idCliente + " ha sido eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún cliente con ID " + idCliente + " para eliminar.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al intentar eliminar el cliente con ID " + idCliente + ": " + ex.getMessage());
        }
    }
    
    

    //---------------------------MOSTRAR CLIENTE-------------------------------------------------------------------------------------    
   
    public void mostrar_BD_cliente() {

        Cliente cli1 = null;
        try {
            // Cargar el controlador JDBC
            // Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            Connection conexion = getConnection();
            System.out.println("Conexión exitosa a la BD_Cliente");
            // Consulta SELECT
            String consulta = "SELECT * from cliente";
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(consulta);
            // Iterar a través de los resultados
            while (resultado.next()) {
                // Acceder a los datos por columnas
                int id = resultado.getInt("id-cliente");
                String cuit = resultado.getString("cuit");
                String razon = resultado.getString("razonSocial");
                String nombreape = resultado.getString("nom-apel");
                String dire = resultado.getString("direc");
                String mail = resultado.getString("mail");
                int cel = resultado.getInt("cel");
                int telfijo = resultado.getInt("tel-fijo");
                String servicio = resultado.getString("servicio");
                //CARGO CLIENTES 
                cli1 = new Cliente(cuit, razon, nombreape, dire, mail, cel, telfijo, servicio);
                // Realizar acciones con los datos obtenidos
                System.out.println("ID: " + id + ", CUIT:" + cuit + ", RAZON:" + razon + ", NOMBRE_APELLIDO:" + nombreape
                        + ", DIRECIÓN:" + dire + ", MAIL:" + mail + " ,CEL:" + cel + " , TELFIJO:" + telfijo + " , SERVICIO:" + servicio);

            }
            // Cerrar el ResultSet y el Statement
            resultado.close();
            statement.close();
            // Cerrar la conexión (código posterior)
        } catch (SQLException e) {
            System.out.println("Error en el insert de la tabla CLIENTE " + e);
            e.printStackTrace();
        }
    }
    
    
        //---------------------------ELIMINAR TÉCNICO -------------------------------------------------------------------------------------
 
    public void eliminarTecnico(int idTecnico) {
        String consulta = "DELETE FROM tecnico WHERE `id-tecnico` = ?";

        try (Connection conexion = getConnection(); 
           PreparedStatement sqlDelete = conexion.prepareStatement(consulta)) {
            sqlDelete.setInt(1, idTecnico);
            int filasEliminadas = sqlDelete.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("El Técnico con ID " + idTecnico + " ha sido eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún técnico con ID " + idTecnico + " para eliminar.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al intentar eliminar el técnico con ID " + idTecnico + ": " + ex.getMessage());
        }
    }
    
    
    

    //-------------------------MOSTRAR TECNICO---------------------------------------------------------------------------------------   
    
    public void mostrar_BD_tecnico() {
        Tecnico t = null;

        try {

            // Establecer la conexión
            Connection conexion = getConnection();
            System.out.println("Conexión exitosa a la BD_Tecnico");
            // Consulta SELECT
            String consulta = "SELECT * from tecnico";
            try (Statement statement = conexion.createStatement();
                ResultSet resultado2 = statement.executeQuery(consulta)) {
                // Iterar a través de los resultados
                while (resultado2.next()) {
                    // Acceder a los datos por columnas
                    int id = resultado2.getInt("id-tecnico");
                    String cuit = resultado2.getString("cuit-tec");
                    String nombre = resultado2.getString("nom-ape");
                    String espec = resultado2.getString("especialidad");                    
                  // Dividir la entrada por comas y convertirla a una lista
                  List<String> listaEspecialidades = Arrays.asList(espec.split("\\s*,\\s*"));                                
                    String medioc = resultado2.getString("medio-comu");
                    String estado = resultado2.getString("estado");
                    //CARGO CLIENTES
                    t = new Tecnico( cuit, nombre, listaEspecialidades, medioc, estado);

                    // Realizar acciones con los datos obtenidos
                    System.out.println("ID: " + id +  ", Cuit:"+ cuit + ", NomApe: " + nombre + ", Especialidad: " + espec + ", MedioC: " + medioc + ", Estado: " + estado);
                }
                // Cerrar el ResultSet y el Statement
                // Cerrar la conexión (código posterior)
                resultado2.close();
                statement.close();
            }
        } catch (SQLException e) {

            System.out.println("Error en el insert de la tabla TÉCNICO " + e);
        }

    }
    
    
        //---------------------------MOSTRAR INCIDENTES REPORTADOS-------------------------------------------------------------------------------------    
   
    public void mostrar_BD_Incidente() {

        Incidente incident= null;
        try {
            // Cargar el controlador JDBC
            // Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            Connection conexion = getConnection();
            System.out.println("Conexión exitosa a la BD_Incidente");
            // Consulta SELECT
            String consulta = "SELECT * from incidente";
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(consulta);
            // Iterar a través de los resultados
            while (resultado.next()) {
                // Acceder a los datos por columnas
                int id = resultado.getInt("idIncidente");
                String servicio = resultado.getString("servicio");
                String descripProblema = resultado.getString("descripcionProblema");
                String tipoProblema = resultado.getString("tipoProblema");
                int idCliente = resultado.getInt("idCliente");
                int idTecnico = resultado.getInt("idTecnico");
                Date fechaInicial = resultado.getDate("fechaInicial");
                LocalDate fechaInicialLocal = fechaInicial.toLocalDate();

                java.sql.Date fechaResolucion = resultado.getDate("fechaResolucion");
                LocalDate fechaResolucionLocal = fechaResolucion.toLocalDate();
                String estado = resultado.getString("estadoInc");
                //CARGO CLIENTES 
                // Realizar acciones con los datos obtenidos
                System.out.println("ID: " + id + ", SERVICIO:" + servicio + ", DescripProblema:" + descripProblema + ", tipoProblema:" + tipoProblema
                        + ", idCliente:" + idCliente + ", idTecnico:" + idTecnico + " ,fechaInicial:" + fechaInicial + " , fechaResolucion:" + fechaResolucion + " , ESTADO:" + estado);

            }
            // Cerrar el ResultSet y el Statement
            resultado.close();
            statement.close();
            // Cerrar la conexión (código posterior)
        } catch (SQLException e) {
            System.out.println("Error en el insert de la tabla INCIDENTE" + e);
            e.printStackTrace();
        }
    }
    
    
    //****************************************************************
    
     public boolean servicioClienteconTecnico(String serv  ) {

        boolean valida=false;
        try {
            // Cargar el controlador JDBC
            // Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            Connection conexion = getConnection();
            System.out.println("Conexión exitosa a la BD_Cliente");
            // Consulta SELECT
            String consulta = "SELECT * from cliente";
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(consulta);
            // Iterar a través de los resultados
            while (resultado.next()) {
                // Acceder a los datos por columnas

                String servicio = resultado.getString("servicio");

                 if(servicio.equalsIgnoreCase(serv)){
                     valida=true;
                 }

            }
            // Cerrar el ResultSet y el Statement
            resultado.close();
            statement.close();
            // Cerrar la conexión (código posterior)
        } catch (SQLException e) {
            System.out.println("Error en el insert de la tabla CLIENTE " + e);
            e.printStackTrace();
        }
        return valida;
    }

    
     
     
     
    
    //--------------------------VALIDAR CUIT TÉCNICO --------------------------------------------------------------------------------------
 
    public boolean validarCuitTecnico(String cuit) {

        String consulta = String.format("select * from tecnico WHERE `cuit-tec` = %s ", cuit);
        //  cuit = %d AND razon_social = '%s'", cuitCliente, razonSocial);
        ResultSet sql;
        boolean resultado = false;

        try {
            Connection conexion = getConnection();
            Statement statement = conexion.createStatement();
            sql = statement.executeQuery(consulta);
            System.out.println("\n Servicios del Cliente:");
            while (sql.next()) {
                System.out.println("ID:" + sql.getInt(1) + " , Nom-Ape:" + sql.getString(2) + " , Especialidad:" + sql.getString(3));
                resultado = true; // Al menos un cliente coincide, establecemos resultado a true
            }
            if (!resultado) {
                System.out.println("El cliente no existe, INGRESE OTRO CUIT");
            }
            conexion.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error en la búsqueda de Cliente" + e);
            e.printStackTrace();
        }
        return resultado;
    }

    
    
    //---------------------------VALIDAR CUIT CLIENTE-------------------------------------------------------------------------------------   

    public boolean validarCuitCliente() {
        System.out.println("3-Ingrese datos del Cliente:");
        //Atributos del Cliente
        String cuit ="";
        
          String regex = "\\d{2}-\\d{8}-\\d"; // Formato 23-43242346-9
        Pattern pattern = Pattern.compile(regex);
        
        boolean cuitValido = false;
        do {
            System.out.println("Ingrese el CUIT del cliente: ");
             cuit = In.readLine();
            Matcher matcher = pattern.matcher(cuit);
            
            if (matcher.matches()) {
                System.out.println("El CUIT ingresado es válido.");
                cuitValido = true;
            } else {
                System.out.println("El CUIT ingresado no es válido. Por favor, inténtelo de nuevo.");
            }
        } while (!cuitValido);
       
  
        
        System.out.println("Ingrese la razón Social: ");
        String razonSocial = In.readLine();  // globalx
        String consulta = String.format("select * from cliente WHERE cuit = %s AND razonSocial = '%s'", cuit, razonSocial);
        //  cuit = %d AND razon_social = '%s'", cuitCliente, razonSocial);
        ResultSet sql;
        boolean resultado = false;
        try {
            Connection conexion = getConnection();
            Statement statement = conexion.createStatement();
            sql = statement.executeQuery(consulta);
            System.out.println("\n Servicios del Cliente:");
            while (sql.next()) {
                System.out.println("ID:" + sql.getInt(1) + " , Cuit:" + sql.getString(2) + " , NomApe:" + sql.getString(4) + " , Servicio_Contratado:" + sql.getString(9));
                resultado = true; // Al menos un cliente coincide, establecemos resultado a true
            }
            if (!resultado) {
                System.out.println("El cliente no existe, INGRESE OTRO CUIT");
            }
            conexion.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error en la búsqueda de Cliente" + e);
            e.printStackTrace();
        }
        return resultado;
    }

    
    
    
    
    
    //--------------------------TECNICOS DISPONIBLES------------------------------------------------------------------------------------   
 
    public void mostrarTecnicosDisponibles() {

        try (Connection conexion = getConnection()) {
            // Consulta SQL para obtener los técnicos disponibles
            String consulta = "SELECT * FROM tecnico WHERE estado = 'Activo'";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            System.out.println("LISTA DE TÉCNICOS DISPONIBLES:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id-tecnico");
                String nombre = resultSet.getString("nom-ape");
                String especialidad = resultSet.getString("especialidad");

                System.out.println("ID: " + id + "; Nombre: " + nombre + "; Especialidad: " + especialidad);

            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los técnicos disponibles: " + e.getMessage());
        }
    }
    
    

    //------------------------ACTUALIZAR Estado del Técnico---------------------------------------------------------------------------------------   
   
    public int actualizarEstadoTecnicoInactivo() {
        String nuevoEstado = "";
        int idTecnico = 0;
        try (Connection conexion = getConnection()) {
   
            System.out.println("IDTecnico: ");
            idTecnico = In.readInt();
            System.out.println("NuevoEstado: ");

            do {
                nuevoEstado = In.readLine();
            } while (!nuevoEstado.equalsIgnoreCase("Inactivo"));
            // Actualizar el estado del técnico en la base de datos
            String updateQuery = "UPDATE tecnico SET estado = ? WHERE id-tecnico = ?";
            PreparedStatement pstmt = conexion.prepareStatement(updateQuery);
            pstmt.setString(1, nuevoEstado);
            pstmt.setInt(2, idTecnico);
            int filasActualizadas = pstmt.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Estado del técnico actualizado exitosamente");
            } else {
                System.out.println("No se pudo actualizar el estado del técnico");
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado del técnico: " + e.getMessage());
        }
        return idTecnico;
    }

    
 
    //---------------------------ACTUALIZAR estado del Técnico-------------------------------------------------------------------------------------    
 
    public void actualizarEstadoTecnicoActivo(int id, String disp, String espec) {

        try (Connection conexion = getConnection();) {
            System.out.println("Conexión exitosa a la base de datos");
            // Consulta UPDATE para modificar solo el estado de un técnico específico (suponiendo que "id" identifica al técnico a actualizar)
            String consultaUpdate = "UPDATE tecnico SET estado = ? WHERE id-tecnico = ? and especialidad = ?";
            try (PreparedStatement statement = conexion.prepareStatement(consultaUpdate)) {
                // Establecer el nuevo estado en la sentencia SQL
                statement.setString(1, disp);
                statement.setInt(2, id); // Usar el ID para identificar al técnico a actualizar
                statement.setString(3, espec);
                // Ejecutar la consulta de actualización
                statement.executeUpdate();
                System.out.println("La BD/TABLA TÉCNICO se actualizo con exito");

                conexion.close();
                statement.close();
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar en la tabla TECNICO" + e);
            e.printStackTrace();
        }

    }

    //--------------------------INSERTAR un Incidente----------------------------------------------------------------------------------------------   
 
    public void insertIncidente(Incidente inc1) {
        String sql = "INSERT INTO Incidente (servicio,descripcionProblema,tipoProblema,idCliente,idTecnico,fechaInicial,fechaResolucion,estadoInc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Obtener la fecha estimada de resolución (por ejemplo, 5 días después de hoy)
            LocalDate fechaResolucionEstimada = LocalDate.now().plusDays(inc1.getFechaResolucion());
            // Convertir LocalDate a java.sql.Date para la inserción en la base de datos
            java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaResolucionEstimada);               
            LocalDate fechaInicial = LocalDate.now();
            java.sql.Date fechaInicioSQL = java.sql.Date.valueOf(fechaInicial);
            
            preparedStatement.setString(1, inc1.getServicioInc());
            preparedStatement.setString(2, inc1.getDescripcionProblema());
            preparedStatement.setString(3, inc1.getTipoProblema());
            preparedStatement.setInt(4, inc1.getId_cliente());
            preparedStatement.setInt(5, inc1.getId_tecnico());
            preparedStatement.setDate(6, fechaInicioSQL);
            preparedStatement.setDate(7, fechaSQL);
            preparedStatement.setString(8, inc1.getEstadoInc());
            preparedStatement.executeUpdate();
            System.out.println("Incidente ingresado correctamente...\n");
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idIncidente = generatedKeys.getInt(1);

                // Mostrar los datos del incidente insertado
                System.out.println("Datos del incidente insertado:");
                System.out.println("ID Incidente: " + idIncidente);
                System.out.println("Servicio: " + inc1.getServicioInc());
                System.out.println("Descripción Problema: " + inc1.getDescripcionProblema());
                System.out.println("Tipo Problema: " + inc1.getTipoProblema());
                System.out.println("ID Cliente: " + inc1.getId_cliente());
                System.out.println("ID Técnico: " + inc1.getId_tecnico());
                System.out.println("Fecha Resolución: " + fechaSQL);
                System.out.println("Estado Incidente: " + inc1.getEstadoInc());
            }
            connection.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Error en el insert de la tabla INCIDENTE " + ex);

        }
    }
    

    //-------------------------------------------------------------------------------------------------------------------------------------------
    //Método para Calcular la fecha estimada de resolución
    public static LocalDate obtenerFechaResolucion(int dias) {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        // Sumar los días proporcionados para obtener la fecha estimada de resolución
        LocalDate fechaResolucionEstimada = fechaActual.plusDays(dias);

        return fechaResolucionEstimada;
    }
    
    
    

    //-------------------------ACTUALIZAR Estado Incidente---------------------------------------------------------------------------------------   
   
    public void actualizarEstadoIncidente(int id) {

        try (Connection conexion = getConnection();) {
            System.out.println("Conexión exitosa a la base de datos");
            // Consulta UPDATE para modificar solo el estado de un técnico específico (suponiendo que "id" identifica al técnico a actualizar)
            String consultaUpdate = "UPDATE incidente SET estadoInc = ? WHERE idIncidente = ?";
            try (PreparedStatement statement = conexion.prepareStatement(consultaUpdate)) {
                // Establecer el nuevo estado en la sentencia SQL
                statement.setString(1, "RESUELTO");
                statement.setInt(2, id); // Usar el ID para identificar al técnico a actualizar

                // Ejecutar la consulta de actualización
                statement.executeUpdate();
                System.out.println("La BD/TABLA INCIDENTE se actualizo con exito");

                int filasActualizadas = statement.executeUpdate();
                // Consulta para obtener los datos del incidente actualizado
                if (filasActualizadas > 0) {
                    String consultaSelect = "SELECT * FROM incidente WHERE idIncidente = ?";
                    try (PreparedStatement selectStatement = conexion.prepareStatement(consultaSelect)) {
                        selectStatement.setInt(1, id);
                        ResultSet resultSet = selectStatement.executeQuery();

                        // Mostrar los datos del incidente actualizado
                        while (resultSet.next()) {
                            System.out.println("ID Incidente: " + resultSet.getInt("idIncidente"));
                            System.out.println("Servicio: " + resultSet.getString("servicio"));
                            System.out.println("Descripción Problema: " + resultSet.getString("descripcionProblema"));
                            // ... (continuar con el resto de los campos)
                            System.out.println("Estado Incidente: " + resultSet.getString("estadoInc"));
                        }
                    }
                }

                conexion.close();
                statement.close();
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar en la tabla INCIDENTE" + e);
            e.printStackTrace();
        }

    }

 



//----------------------- QUIÉN FUE EL TÉCNICO CON MÁS INCIDENTES RESUELTOS EN LOS ÚLTIMOS N DÍAS -------------------------------------
   
    public String tecnicoMasIncidentesResueltos() {
        String consulta = "SELECT idTecnico, COUNT(*) AS total_incidentes "
                + "FROM incidente "
                + "WHERE estadoInc = ? AND fechaResolucion >= ? "
                + "GROUP BY idTecnico "
                + "ORDER BY total_incidentes DESC "
                + "LIMIT 1";

        try (Connection connection = getConnection(); PreparedStatement sqlUp = connection.prepareStatement(consulta)) {
            sqlUp.setString(1, "RESUELTO");

            System.out.println("Ingresar N días:");
            int ultimosNday = In.readInt();
            LocalDate fechaInicial = LocalDate.now();
            LocalDate fechaFin = fechaInicial.minusDays(ultimosNday);

            sqlUp.setDate(2, Date.valueOf(fechaFin));

            try (ResultSet resultSet = sqlUp.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("idTecnico");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return "No hay";

    }
    
    
    
    
    //------------------ METODO QUE ME DEVUELVE EL TECNICO DE LA ESPECILIDAD ESPECIFICA CON MAS INCIDENTES RESUELTOS -------------------------------------
    
    
    
    
    public String tecnicoMasIncidentesResueltosConEspecialidad(int ultimosNday, String especialidad) {

        String consulta = "SELECT idTecnico, COUNT(*) AS total_incidentes "
                + "FROM incidente "
                + "WHERE estadoInc = ? AND fechaResolucion >= ? AND servicio = ? "
                + "GROUP BY idTecnico "
                + "ORDER BY total_incidentes DESC "
                + "LIMIT 1";

        try (Connection connection = getConnection(); PreparedStatement sqlUp = connection.prepareStatement(consulta)) {
            sqlUp.setString(1, "RESUELTO");

            LocalDate fechaInicial = LocalDate.now();
            LocalDate fechaFin = fechaInicial.minusDays(ultimosNday);

            sqlUp.setDate(2, Date.valueOf(fechaFin));
            sqlUp.setString(3, especialidad);

            try (ResultSet resultSet = sqlUp.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("idTecnico");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return "No hay";
    }


 
    
    
    //--------------------------------------------------------------------------------------------------------------------------- 
    
    
    
    public void mostrarDatosTecnicoPorId(String idTecnico) {

        String consulta = "SELECT * FROM tecnico WHERE `id-tecnico` = ?";

        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, idTecnico);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nombre = resultSet.getString("nom-ape");
                    String especialidad = resultSet.getString("especialidad");

                    System.out.println("ID Técnico: " + idTecnico);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Especialidad: " + especialidad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    
    
    
   //------------------------ METODO QUE ME DEVUELVE EL NOMBRE DEL TECNICO QUE RESOLVIÓ MAS RAPIDO EL PROBLEMA ----------------------------
    
    public String tecnicoMasRapido() {
        String query = "SELECT t.`nom-ape`, AVG(DATEDIFF(i.fechaResolucion, i.fechaInicial)) AS promedio_resolucion "
                + "FROM Incidente i "
                + "JOIN tecnico t ON i.idTecnico = t.`id-tecnico` "
                + "WHERE i.`estadoInc` = 'Resuelto' "
                + "GROUP BY t.`nom-ape` "
                + "ORDER BY promedio_resolucion ASC "
                + "LIMIT 1";

        try (Connection connection = getConnection(); Statement statement = connection.createStatement(); 
             ResultSet resultSet = statement.executeQuery(query)) {
            
            if (resultSet.next()) {
                return resultSet.getString("nom-ape");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
 





//---------------------------------------------------------------FIN---------------------------------------------------------------------