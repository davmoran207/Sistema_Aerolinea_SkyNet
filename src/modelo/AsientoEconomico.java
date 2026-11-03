package modelo;


public class AsientoEconomico extends Asiento {
    public AsientoEconomico( String numero, boolean estaLibre, double precioBase){
        super(numero,estaLibre,precioBase);
    }
    
    @Override
    public double calcularPrecio(){
        return getPrecioBase();
    }
}
