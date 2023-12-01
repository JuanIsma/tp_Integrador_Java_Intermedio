
package ProyectoFinal_Java_Incidente_Reportado;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data
 @NoArgsConstructor
 @AllArgsConstructor
// Clase que representa un incidente
public class Incidente {
    private int idInc;
    private String servicioInc;
    private String descripcionProblema;
    private String tipoProblema ;
    private int Id_cliente;
    private int Id_tecnico ;
    private LocalDate fechaInicio;
    private   int fechaResolucion;
 
    private String estadoInc; //resuelto/ reportado
  
    
    
     public Incidente altaIncidente() {
         ConexionDB conex2= new ConexionDB();
        Incidente inci=null;
       
        try{
         System.out.println("*****INGRESE LOS SIGUIENTES DATOS DEL INCIDENTE*****");
         System.out.println("***********RESPETANDO LAS INDICACIONES*************");
         System.out.println("Servicio: ");
         String servicio = In.readLine();
          
         System.out.println("Descripción del Problema: ");
         String descripP = In.readLine();
         System.out.println("Tipo de Problema: ");
         String tipoP = In.readLine();
         System.out.println("IDCliente: ");
         int cliente = In.readInt(); 
         
  
         System.out.println("Ingrese cantidad de Días para la Resolución:  ");
         fechaResolucion = In.readInt();
         //en alta de incidente el estado por defecto será REPORTADO
         String estado = "REPORTADO";
         System.out.println("Buscando Técnicos Disponibles...");
         conex2.mostrarTecnicosDisponibles();
         System.out.println("Enviando notificación al Técnico...");
         System.out.println("Técnico Seleccionado y Actualizar Estado: ");
            
          boolean valida= conex2.servicioClienteconTecnico(servicio);
             //Si el Servicio del Cliente coincide con el del técnico continuar
          if(valida){
         int idTec= conex2.actualizarEstadoTecnicoInactivo();
          
        // conex2.mostrarTecnicosDisponibles(); //comprueba
       inci = new Incidente(idInc,servicio, descripP, tipoP, cliente, idTec,null,  fechaResolucion, estado);
         conex2.mostrarTecnicosDisponibles(); //comprueba
           }
              } catch (NullPointerException e) {
        // Manejar la excepción NullPointerException
        System.out.println("Se ha producido una NullPointerException: " + e.getMessage());
        e.printStackTrace(); // Esto te mostrará la traza de la excepción para depurar
    }

         return inci;
     }

     
    
    

    }

