package logica;

import modelo.*;
import java.util.ArrayList;

public class Aerolinea {
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
}
