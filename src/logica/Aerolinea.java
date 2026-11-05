package logica;

import modelo.*;
import java.util.ArrayList;

public class Aerolinea {
    private static final double PORCENTAJE_REEMBOLSO = 0.90;

    private ArrayList<Vuelo> vuelos;
    private ArrayList<Pasajero> pasajeros;
    private ArrayList<Empleado> empleados;
    private ArrayList<Reserva> reservas;

    public Aerolinea(){
        this.vuelos= new ArrayList<>();
        this.pasajeros= new ArrayList<>();
        this.empleados= new ArrayList<>();
        this.reservas= new ArrayList<>();
    }

    public void registrarVuelo(String codigo, String origen, String destino, String fecha, int capacidadMaxima){
        if(!textoValido(codigo) || !textoValido(origen) || !textoValido(destino) || !textoValido(fecha)
                || capacidadMaxima<10 || capacidadMaxima>100){
            System.out.println("Complete todos los datos y use una capacidad entre 10 y 100.");
            return;
        }
        Vuelo nuevo= new Vuelo(codigo.trim(), origen.trim(), destino.trim(), fecha.trim(), capacidadMaxima);
        nuevo.generarAsientos();
        vuelos.add(nuevo);
        System.out.println("Vuelo "+nuevo.getCodigo()+" registrado con "+capacidadMaxima+" asientos.");
    }

    public Vuelo buscarVuelo(String codigo){
        for(Vuelo v: vuelos){
            if(v.getCodigo().equalsIgnoreCase(codigo)){
                return v;
            }
        }
        return null;
    }

    public void listarVuelos(){
        System.out.println("=== VUELOS REGISTRADOS ===");
        for(Vuelo v: vuelos){
            int ocupados= v.contarAsientosOcupados();
            System.out.println(v+" |Ocupados: "+ocupados+" |Disponibles: "+(v.getCapacidadMaxima()-ocupados));
            if(ocupados==v.getCapacidadMaxima()){
                System.out.println("   ** VUELO LLENO **");
            }
        }
    }

    public void registrarPasajero(String nombre, String cedula, int edad, String telefono, String pasaporte){
        if(!textoValido(nombre) || !textoValido(telefono) || !textoValido(pasaporte)
                || !cedulaValida(cedula) || edad<0 || edad>120){
            System.out.println("Revise nombre, cedula (10 digitos), edad, telefono y pasaporte.");
            return;
        }
        Pasajero nuevo= new Pasajero(nombre.trim(), cedula, edad, telefono.trim(), pasaporte.trim());
        pasajeros.add(nuevo);
        System.out.println("Pasajero "+nuevo.getNombre()+" registrado (descuento: "+nuevo.getPorcentajeDescuento()+"%).");
    }

    public void registrarEmpleado(String nombre, String cedula, int edad, String telefono, String cargo, double sueldo){
        if(!textoValido(nombre) || !textoValido(telefono) || !textoValido(cargo)
                || !cedulaValida(cedula) || edad<18 || edad>120){
            System.out.println("Revise nombre, cedula (10 digitos), edad (minimo 18), telefono y cargo.");
            return;
        }
        Empleado nuevo= new Empleado(nombre.trim(), cedula, edad, telefono.trim(), cargo.trim(), sueldo);
        empleados.add(nuevo);
        System.out.println("Empleado "+nuevo.getNombre()+" registrado.");
    }

    public Persona buscarPersona(String cedula){
        for(Pasajero p: pasajeros){
            if(p.getCedula().equals(cedula)){
                return p;
            }
        }
        for(Empleado e: empleados){
            if(e.getCedula().equals(cedula)){
                return e;
            }
        }
        return null;
    }

    public void listarPasajeros(){
        System.out.println("=== PASAJEROS REGISTRADOS ===");
        for(Pasajero p: pasajeros){
            System.out.println(p);
        }
    }

    public void listarEmpleados(){
        System.out.println("=== EMPLEADOS REGISTRADOS ===");
        for(Empleado e: empleados){
            System.out.println(e);
        }
    }

    public double calcularPrecioFinal(Asiento asiento, Pasajero pasajero){
        double precio= asiento.calcularPrecio();
        return precio - (precio * pasajero.getPorcentajeDescuento() / 100.0);
    }

    public Reserva reservarAsiento(String codigoVuelo, String cedulaPasajero, String numeroAsiento, String cedulaEmpleado){
        Vuelo vuelo= buscarVuelo(codigoVuelo);
        Persona persona1= buscarPersona(cedulaPasajero);
        Persona persona2= buscarPersona(cedulaEmpleado);

        if(vuelo==null || !(persona1 instanceof Pasajero) || !(persona2 instanceof Empleado)){
            System.out.println("El vuelo, el pasajero o el empleado no estan registrados.");
            return null;
        }
        Pasajero pasajero= (Pasajero) persona1;
        Empleado empleado= (Empleado) persona2;

        Asiento asiento= buscarAsiento(vuelo, numeroAsiento);
        if(asiento==null || !asiento.isEstaLibre()){
            System.out.println("El asiento no existe o ya esta ocupado.");
            return null;
        }

        double precioFinal= calcularPrecioFinal(asiento, pasajero);
        Reserva nueva= new Reserva("RES-"+String.format("%03d", reservas.size()+1), vuelo, pasajero, empleado, asiento, precioFinal);
        asiento.ocupar();
        reservas.add(nueva);
        System.out.println("Reserva "+nueva.getCodigo()+" creada. Valor a pagar: $"+String.format("%.2f", precioFinal));
        return nueva;
    }

    public void cancelarReserva(String codigo){
        Reserva reserva= buscarReserva(codigo);
        if(reserva==null || !reserva.isEstaActiva()){
            System.out.println("La reserva no existe o ya esta cancelada.");
            return;
        }
        reserva.cancelar();
        reserva.getAsientoAsignado().liberar();
        double reembolso= reserva.getValorPagado() * PORCENTAJE_REEMBOLSO;
        System.out.println("Reserva "+reserva.getCodigo()+" cancelada. Reembolso: $"+String.format("%.2f", reembolso)
                +" |Penalizacion: $"+String.format("%.2f", reserva.getValorPagado()-reembolso));
    }

    public Reserva buscarReserva(String codigo){
        for(Reserva r: reservas){
            if(r.getCodigo().equalsIgnoreCase(codigo)){
                return r;
            }
        }
        return null;
    }

    public void generarMapaAsientos(String codigoVuelo){
        Vuelo vuelo= buscarVuelo(codigoVuelo);
        if(vuelo==null){
            System.out.println("No existe un vuelo con el codigo "+codigoVuelo+".");
            return;
        }
        System.out.println("=== MAPA DE ASIENTOS - VUELO "+vuelo.getCodigo()+" ===");
        System.out.println("Numero = libre   X = ocupado");
        int contador= 0;
        for(Asiento a: vuelo.getAsientos()){
            System.out.print(a.isEstaLibre() ? String.format("[%3s] ", a.getNumero()) : "[  X] ");
            contador++;
            if(contador%10==0){
                System.out.println();
            }
        }
        int ocupados= vuelo.contarAsientosOcupados();
        System.out.println();
        System.out.println("Ocupados: "+ocupados+" |Disponibles: "+(vuelo.getCapacidadMaxima()-ocupados));
    }

    public void mostrarReservasPorPasajero(String cedula){
        System.out.println("=== RESERVAS DEL PASAJERO "+cedula+" ===");
        for(Reserva r: reservas){
            if(r.getPasajero().getCedula().equals(cedula)){
                mostrarReserva(r);
            }
        }
    }

    public void mostrarReservasPorVuelo(String codigoVuelo){
        System.out.println("=== RESERVAS DEL VUELO "+codigoVuelo+" ===");
        for(Reserva r: reservas){
            if(r.getVueloAsignado().getCodigo().equalsIgnoreCase(codigoVuelo)){
                mostrarReserva(r);
            }
        }
    }

    public double calcularRecaudacionTotal(){
        double total= 0;
        for(Reserva r: reservas){
            total+= ingresoDeReserva(r);
        }
        return total;
    }

    public double calcularRecaudacionPorVuelo(String codigoVuelo){
        double total= 0;
        for(Reserva r: reservas){
            if(r.getVueloAsignado().getCodigo().equalsIgnoreCase(codigoVuelo)){
                total+= ingresoDeReserva(r);
            }
        }
        return total;
    }

    private double ingresoDeReserva(Reserva r){
        if(r.isEstaActiva()){
            return r.getValorPagado();
        }
        return r.getValorPagado() * (1 - PORCENTAJE_REEMBOLSO);
    }

    private Asiento buscarAsiento(Vuelo vuelo, String numero){
        for(Asiento a: vuelo.getAsientos()){
            if(a.getNumero().equals(numero)){
                return a;
            }
        }
        return null;
    }

    private void mostrarReserva(Reserva r){
        System.out.println(r.getCodigo()+" |Vuelo: "+r.getVueloAsignado().getCodigo()
                +" |Asiento: "+r.getAsientoAsignado().getNumero()
                +" |Pasajero: "+r.getPasajero().getNombre()
                +" |Valor pagado: $"+String.format("%.2f", r.getValorPagado())
                +" |Estado: "+(r.isEstaActiva() ? "ACTIVA" : "CANCELADA"));
    }

    private boolean textoValido(String texto){
        return texto!=null && !texto.trim().isEmpty();
    }

    private boolean cedulaValida(String cedula){
        if(cedula==null || cedula.length()!=10){
            return false;
        }
        for(int i=0; i<cedula.length(); i++){
            if(!Character.isDigit(cedula.charAt(i))){
                return false;
            }
        }
        return true;
    }
}     this.reservas= new ArrayList<>();
    }
}
