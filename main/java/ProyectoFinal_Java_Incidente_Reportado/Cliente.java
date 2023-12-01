package ProyectoFinal_Java_Incidente_Reportado;

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
public class Cliente {

    //private int idCliente;
    private String cuit;
    private String razonSocial;
    private String nomApe;
    private String dire;
    private String mail;
    private int celular;
    private int telFijo;
    private String servicio;

    public  Cliente darAltaCliente() {
        System.out.println("Ingrese Los datos requeridos del Cliente:");
        //String nomApe="";
        
    //-------------------------------------------------------------------------------------------------------  

        
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
        
        
    //-------------------------------------------------------------------------------------------------------         

        System.out.println("Ingrese Razón Social:");
        razonSocial = In.readLine();
        
    //------------------------------------------------------------------------------------------------------- 
 
        boolean esValidoNom = false;
        Pattern patternNom = Pattern.compile("^[a-zA-Z]+\\s[a-zA-Z]+$");   
        
        do {
            System.out.println("Ingrese Nombre y Apellido:");
            nomApe = In.readLine();
            Matcher matcher = patternNom.matcher(nomApe);
            if (matcher.matches()) {
                esValidoNom = true;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese Nombre y Apellido separados por un espacio y solo con letras.");
            }
          } while (!esValidoNom);
        
    //-------------------------------------------------------------------------------------------------------       
        System.out.println("Ingrese direccion:");
         dire = In.readLine();
    //-------------------------------------------------------------------------------------------------------         
        boolean esValidoEmail = false;
        Pattern patternEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

        do {
            System.out.println("Ingrese dirección de correo electrónico:");
            mail = In.readLine();
            Matcher matcher = patternEmail.matcher(mail);

            if (matcher.matches()) {
                esValidoEmail = true;
            } else {
                System.out.println("Dirección de correo electrónico inválida. Por favor, ingrésela en un formato válido.");
            }
        } while (!esValidoEmail);
    //-------------------------------------------------------------------------------------------------------     
    
       
        boolean esValidoCel = false;
        Pattern patternTelefono = Pattern.compile("^\\d{13}$");

        do {
            System.out.println("Ingrese número de teléfono (10 dígitos):");
            celular = In.readInt();
            Matcher matcher = patternTelefono.matcher(String.valueOf(celular));

            if (matcher.matches()) {
                esValidoCel = true;
            } else {
                System.out.println("Número de teléfono inválido. Debe tener 13 dígitos.");
            }
        } while (!esValidoCel);
    //-------------------------------------------------------------------------------------------------------         
          
        boolean esValidoTelFijo = false;
        Pattern patternTeleFijo = Pattern.compile("^\\d{13}$");

        do {
            System.out.println("Ingrese número de teléfono :");
            telFijo = In.readInt();
            Matcher matcher = patternTeleFijo .matcher(String.valueOf(telFijo));

            if (matcher.matches()) {
                esValidoTelFijo = true;
            } else {
                System.out.println("Número de teléfono inválido. Debe tener 13 dígitos.");
            }
        } while (!esValidoTelFijo);
    //-------------------------------------------------------------------------------------------------------      

        HashSet<String> opcionesValidas = new HashSet<>(Arrays.asList("SAP", "TANGO", "WINDOWS", "MACOS", "LINUX_UBUNTU"));
        do {
            System.out.println("Ingrese un SERVICIO(SAP,TANGO,WINDOWS,MACOS,LINUX_UBUNTU):");
            servicio = In.readLine();
        } while (!opcionesValidas.contains(servicio.toUpperCase()));
        
    //-------------------------------------------------------------------------------------------------------         

        Cliente cliente = new Cliente(cuit, razonSocial, nomApe, dire, mail, celular, telFijo, servicio);
        return cliente;

    }

}
