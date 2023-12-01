
package ProyectoFinal_Java_Incidente_Reportado;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tecnico {

    private String cuitTecnico;
    private String nombre;
    private List<String> especialidades;
    private String medioNotificacionPreferido;
    private String disponible;

    public static Tecnico darAltaTecnico() {
        ConexionDB conex = new ConexionDB();
        String cuit = "",espec="" ,nom="" ,noti="",estado="";
 
        System.out.println("Ingrese Los datos requeridos del Técnico:");
        
     //------------------------------------------------------------------------------------------------------------------------------------------  
        
        String regex = "\\d{2}-\\d{8}-\\d"; // Formato 23-43242346-9
        Pattern pattern = Pattern.compile(regex);
        boolean cuitValido = false;
        do {
            System.out.println("Ingrese el CUIT del cliente (Formato xx-xxxxxxxx-x): ");
            cuit = In.readLine();
            Matcher matcher = pattern.matcher(cuit);
            if (matcher.matches()) {
                System.out.println("El CUIT ingresado es válido.");
                cuitValido = true;
            } else {
                System.out.println("El CUIT ingresado no es válido. Por favor, inténtelo de nuevo.");
            }
        } while (!cuitValido);

      //------------------------------------------------------------------------------------------------------------------------------------------   
        
        boolean esValido = false;
        Pattern patternNom = Pattern.compile("^[a-zA-Z]+\\s[a-zA-Z]+$");   
        
        do {
            System.out.println("Ingrese Nombre y Apellido:");
            nom = In.readLine();
            Matcher matcher = patternNom.matcher(nom);
            if (matcher.matches()) {
                esValido = true;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese Nombre y Apellido separados por un espacio y solo con letras.");
            }
          } while (!esValido);
        
      //------------------------------------------------------------------------------------------------------------------------------------------   
           
         
        boolean esValidoEsp = false;
        Pattern patternEspec = Pattern.compile("^(SAP|TANGO|WINDOWS|MACOS|LINUX_UBUNTU)(\\s*,\\s*(SAP|TANGO|WINDOWS|MACOS|LINUX_UBUNTU))*$");

        do {
            System.out.println("Ingrese Especialidad (SAP, TANGO, WINDOWS, MACOS, LINUX_UBUNTU):");
            espec = In.readLine().trim().toUpperCase(); // Convertir a mayúsculas para coincidir con la expresión regular
            Matcher matcher = patternEspec.matcher(espec);
            if (matcher.matches()) {
                esValidoEsp = true;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese una o varias de las especialidades separadas por comas.");
            }
        } while (!esValidoEsp);
        
       
        // Dividir la entrada por comas y convertirla a una lista
        List<String> listaEspecialidades = Arrays.asList(espec.split("\\s*,\\s*"));
        
        
      //------------------------------------------------------------------------------------------------------------------------------------------         
        
        boolean esValidoNoti = false;
        Pattern patternNoti = Pattern.compile("^(mail|whatsapp)$");

        do {
            System.out.println("Ingrese tipo de Notificación (mail o whatsapp):");
            noti = In.readLine().toLowerCase(); // Convertir a minúsculas para coincidir con la expresión regular
            Matcher matcher = patternNoti.matcher(noti);
            if (matcher.matches()) {
                esValidoNoti = true;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese 'mail' o 'whatsapp'.");
            }
        } while (!esValidoNoti);
        
      //------------------------------------------------------------------------------------------------------------------------------------------ 
      
        boolean esValidoEstado = false;
        Pattern patternEstado = Pattern.compile("^(Activo|Inactivo)$");

        do {
            System.out.println("Ingrese Estado (Activo o Inactivo):");
            estado = In.readLine().trim(); // No se requiere conversión ya que se espera coincidencia exacta
            Matcher matcher = patternEstado.matcher(estado);
            if (matcher.matches()) {
                esValidoEstado = true;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese 'Activo' o 'Inactivo'.");
            }
        } while (!esValidoEstado);
        
      //------------------------------------------------------------------------------------------------------------------------------------------  
        
        
        
        
        System.out.println( "Cuit: "+cuit+", NomApe: "+ nom+" ,Especialidades: "+ listaEspecialidades+" ,Notificacion: "+ noti+" ,Estado: "+ estado);
        Tecnico tec = new Tecnico(cuit, nom, listaEspecialidades, noti, estado);
        return tec;
    }

}
