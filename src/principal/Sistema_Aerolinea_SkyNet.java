
package principal;

import logica.Aerolinea;
import java.util.Scanner;

public class Sistema_Aerolinea_SkyNet {

private static final Scanner teclado= new Scanner(System.in);

public static void main(String[] args) {
Aerolinea aerolinea= new Aerolinea();
int opcion;

System.out.println("=====================================");
System.out.println("   BIENVENIDO A LA AEROLINEA SKYNET   ");
System.out.println("=====================================");

do{
mostrarMenu();
opcion= leerEntero("Elija una opcion: ");
System.out.println();

switch(opcion){
case 1:
registrarVuelo(aerolinea);
break;
case 2:
aerolinea.listarVuelos();
break;
case 3:
registrarPasajero(aerolinea);
break;
case 4:
registrarEmpleado(aerolinea);
break;
case 5:
aerolinea.listarPasajeros();
break;
case 6:
aerolinea.listarEmpleados();
break;
case 7:
reservarAsiento(aerolinea);
break;
case 8:
aerolinea.cancelarReserva(leerTexto("Codigo de la reserva (ej. RES-001): "));
break;
case 9:
aerolinea.generarMapaAsientos(leerTexto("Codigo del vuelo: "));
break;
case 10:
aerolinea.mostrarReservasPorPasajero(leerTexto("Cedula del pasajero: "));
break;
case 11:
aerolinea.mostrarReservasPorVuelo(leerTexto("Codigo del vuelo: "));
break;
case 12:
System.out.println("Recaudacion total: $"+String.format("%.2f", aerolinea.calcularRecaudacionTotal()));
break;
case 13:
String codigo= leerTexto("Codigo del vuelo: ");
if(aerolinea.buscarVuelo(codigo)==null){
System.out.println("No existe un vuelo con el codigo "+codigo+".");
} else{
System.out.println("Recaudacion del vuelo "+codigo+": $"+String.format("%.2f", aerolinea.calcularRecaudacionPorVuelo(codigo)));
}
break;
case 0:
System.out.println("Gracias por usar SkyNet. Hasta pronto.");
break;
default:
System.out.println("Opcion invalida. Elija un numero del menu.");
}
if (opcion != 0) {
    presioneEnterParaContinuar();
}
System.out.println();
} while(opcion!=0);

teclado.close();
}


private static void mostrarMenu(){
System.out.println("------------- MENU PRINCIPAL -------------");
System.out.println(" 1. Registrar vuelo");
System.out.println(" 2. Listar vuelos");
System.out.println(" 3. Registrar pasajero");
System.out.println(" 4. Registrar empleado");
System.out.println(" 5. Listar pasajeros");
System.out.println(" 6. Listar empleados");
System.out.println(" 7. Reservar asiento");
System.out.println(" 8. Cancelar reserva");
System.out.println(" 9. Ver mapa de asientos de un vuelo");
System.out.println("10. Ver reservas de un pasajero");
System.out.println("11. Ver reservas de un vuelo");
System.out.println("12. Calcular recaudacion total");
System.out.println("13. Calcular recaudacion de un vuelo");
System.out.println(" 0. Salir");
System.out.println("------------------------------------------");
}

private static void registrarVuelo(Aerolinea aerolinea){
String codigo= leerTexto("Codigo del vuelo: ");
String origen= leerTexto("Origen: ");
String destino= leerTexto("Destino: ");
String fecha= leerTexto("Fecha (dd/mm/aaaa): ");
int capacidad= leerEntero("Capacidad maxima (10 a 100): ");
aerolinea.registrarVuelo(codigo, origen, destino, fecha, capacidad);
}

private static void registrarPasajero(Aerolinea aerolinea){
String nombre= leerTexto("Nombre: ");
String cedula= leerTexto("Cedula (10 digitos): ");
int edad= leerEntero("Edad: ");
String telefono= leerTexto("Telefono: ");
String pasaporte= leerTexto("Pasaporte: ");
aerolinea.registrarPasajero(nombre, cedula, edad, telefono, pasaporte);
}

private static void registrarEmpleado(Aerolinea aerolinea){
String nombre= leerTexto("Nombre: ");
String cedula= leerTexto("Cedula (10 digitos): ");
int edad= leerEntero("Edad (minimo 18): ");
String telefono= leerTexto("Telefono: ");
String cargo= leerTexto("Cargo: ");
double sueldo= leerDecimal("Sueldo: ");
aerolinea.registrarEmpleado(nombre, cedula, edad, telefono, cargo, sueldo);
}

private static void reservarAsiento(Aerolinea aerolinea){
String codigoVuelo= leerTexto("Codigo del vuelo: ");
if(aerolinea.buscarVuelo(codigoVuelo)!=null){
aerolinea.generarMapaAsientos(codigoVuelo);
}
String cedulaPasajero= leerTexto("Cedula del pasajero: ");
String numeroAsiento= leerTexto("Numero de asiento: ");
String cedulaEmpleado= leerTexto("Cedula del empleado que atiende: ");
aerolinea.reservarAsiento(codigoVuelo, cedulaPasajero, numeroAsiento, cedulaEmpleado);
}

private static String leerTexto(String mensaje){
System.out.print(mensaje);
return teclado.nextLine().trim();
}

private static int leerEntero(String mensaje){
while(true){
System.out.print(mensaje);
try{
return Integer.parseInt(teclado.nextLine().trim());
} catch(NumberFormatException e){
System.out.println("Ingrese un numero entero valido.");
}
}
}

private static double leerDecimal(String mensaje){
while(true){
System.out.print(mensaje);
try{
return Double.parseDouble(teclado.nextLine().trim().replace(',', '.'));
} catch(NumberFormatException e){
System.out.println("Ingrese un numero valido (ej. 850.50).");
}
}
}

private static void presioneEnterParaContinuar() {
    System.out.println("Presione ENTER para continuar...");
    teclado.nextLine();
}

}
