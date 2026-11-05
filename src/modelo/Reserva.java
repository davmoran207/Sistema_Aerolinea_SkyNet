package modelo;


public class Reserva {
    private String codigo;
    private Vuelo vueloAsignado;
    private Pasajero pasajero;
    private Empleado empleadoAtencion;
    private Asiento asientoAsignado;
    private double valorPagado;
    private boolean estaActiva;
    
    public Reserva(String codigo, Vuelo vueloAsignado, Pasajero pasajero, Empleado empleadoAtencion, Asiento asientoAsignado, double valorPagado){
        this.codigo=codigo;
        this.vueloAsignado=vueloAsignado;
        this.pasajero=pasajero;
        this.empleadoAtencion=empleadoAtencion;
        this.asientoAsignado=asientoAsignado;
        this.valorPagado=valorPagado;
        this.estaActiva=true;
        
    } 
    
    public String getCodigo(){
        return codigo;
    }
    public Vuelo getVueloAsignado(){
        return vueloAsignado;
    }
    public Pasajero getPasajero(){
        return pasajero;
    }
    public Empleado getEmpleadoAtencion(){
        return empleadoAtencion;
    }
    public Asiento getAsientoAsignado(){
        return asientoAsignado;
    }
    public double getValorPagado(){
        return valorPagado;
    }
    public boolean isEstaActiva(){
       return estaActiva;
    }
    public void cancelar(){
        estaActiva=false;
    }
    
    @Override
    public String toString(){
        return " |Vuelo: "+vueloAsignado+" |Codigo: "+codigo+" |Asiento: "+asientoAsignado+" |Pasajero: "+pasajero;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Reserva otraReserva){
        return this.codigo.equals(otraReserva.codigo);
    } 
        return false;
    }

}
