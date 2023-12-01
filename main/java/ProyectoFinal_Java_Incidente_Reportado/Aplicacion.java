package ProyectoFinal_Java_Incidente_Reportado;

//import static ProyectoFinal_Java_Incidente_Reportado.Cliente.darAltaCliente;
import static ProyectoFinal_Java_Incidente_Reportado.Tecnico.darAltaTecnico;

public class Aplicacion {

    public static void main(String[] args) throws ClassNotFoundException {

        int opc, segunda_var, varTec;
        Cliente cliente = new Cliente();
        ConexionDB incideBD = new ConexionDB();
        ConexionDB conex = new ConexionDB();    
        conex.mostrar_BD_cliente(); //mostrar datos de clientes
        ConexionDB conex2 = new ConexionDB();   
        conex2.mostrar_BD_tecnico(); //mostrar datos de tecnicos
        Incidente in = new Incidente();

        //------------------------------------------- 
        //MENU DE OPCIONES:
        do {
            System.out.println("\n---MENU OPCIONES---");
            System.out.println("1- Operaciones con BD Cliente:");
            System.out.println("2- Operaciones con BD Técnico:");
            System.out.println("3- Mostrar Servicios del Cliente:");
            System.out.println("4- Reportar Incidente y Asignar técnico:");
            System.out.println("5-Confirmar el problema Resuelto: ");
            System.out.println("6- Técnico con más incidentes resueltos en los últimos N días:");
            System.out.println("7- Técnico con más incidentes resueltos en los últimos N días por Especialidad:");
            System.out.println("8-Tecnico que resolvio mas rapido los Incidentes:");
            System.out.println("9- SALIR.");

            System.out.println("Elija una opcion: ");
            opc = In.readInt();
            switch (opc) {
                case 1:

                    System.out.println("1-Para dar de Alta un Cliente. \n 2-Para dar de baja un Cliente.");
                    segunda_var = In.readInt();
                    //Otro switch
                    switch (segunda_var) {
                        case 1:
                            //------------- Dar alta un Cliente a la BD ----------------------------------------------------------------------
                            incideBD.altaClienteDB(cliente.darAltaCliente());
                            conex.mostrar_BD_cliente();
                            break;
                        case 2:
                            //------------- Borrar un Cliente de la BD----------------------------------------------------------------------
                            System.out.println("Para Borrar,ingresar el Id del Cliente:");
                            int idCliente = In.readInt();
                            conex.eliminarCliente(idCliente);
                            conex.mostrar_BD_cliente();
                            break;
                    }
                    break;
                case 2:
                    //------------- DAR ALTA UN TECNICO------------------------------------------------------------------------------
                    System.out.println("1-Para dar de Alta un Tecnico. \n 2-Para dar de baja un Tecnico.");
                    varTec = In.readInt();
                    //Otro switch
                    switch (varTec) {
                        case 1:
                            conex2.altaTecnicoDB(darAltaTecnico());
                            break;
                        case 2:
                            //------------- Borrar un Tecnico de la BD----------------------------------------------------------------------
                            System.out.println("Para Borrar,ingresar el Id del Tecnico:");
                            int idTecnico = In.readInt();
                            conex2.eliminarTecnico(idTecnico);
                            conex2.mostrar_BD_tecnico();
                            break;
                    }

                    break;
                case 3:
                    //------------ MOSTRAR LOS SERVICIO DEL CLIENTE Y SUS DATOS ----------------------------------------------------     
                    conex.validarCuitCliente();
                    break;
                case 4:
                    //------------ INGRESA EL INCIDENTE REPORTADO ------------------------------------------------------------------  
                    incideBD.insertIncidente(in.altaIncidente());
                    break;
                case 5:
                    //------------ CONFIRMACIÓN DEL INCIDENTE RESUELTO POR EL TÉCNICO
                    // SIMULANDO QUE EL TÉCNICO MARCA EL INCIDENTE COMO RESUELTO ------------------------------------  
                     conex2.mostrar_BD_Incidente();
                    System.out.println("Ingresar el ID del Incidente: ");
                    int IdIncidente = In.readInt();
                    incideBD.actualizarEstadoIncidente(IdIncidente);
                    break;

                case 6:
                    System.out.println("--- Tecnico con mas Incidentes resueltos: ");
                  incideBD.mostrarDatosTecnicoPorId(  incideBD.tecnicoMasIncidentesResueltos()) ;
                    
                    break;

                case 7:
                    System.out.println("Ingresar N días:");
                    int ultimosNday = In.readInt();
                    System.out.println("Ingresar Especialidad:");
                    String especialidad = In.readLine();
                    System.out.println("--- Tecnico con mas Incidentes resueltos por Especialidad: " );
                    
                    incideBD.mostrarDatosTecnicoPorId(incideBD.tecnicoMasIncidentesResueltosConEspecialidad(ultimosNday, especialidad));
                                  
                  //  incideBD.tecnicoMasIncidentesResueltosConEspecialidad(ultimosNday, especialidad));
                    break;
                case 8:
                    System.out.println(incideBD.tecnicoMasRapido()); 
                    break;
                    
                case 9:
                    //------------  SALIR DE LA APLICACIÓN --------------------------------------------------------------------------  
                    System.out.println("Gracias por utilizar el programa!!!");
                    break;
                default:
                    System.out.println("Ingreso una opcion incorrecta. Intente nuevamente");
            }

        } while (opc != 6);

    }

}
