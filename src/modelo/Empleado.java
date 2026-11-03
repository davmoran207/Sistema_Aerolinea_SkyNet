package modelo;

public class Empleado extends Persona{
    private String cargo;
    private double sueldo;
    
    public Empleado(String nombre, String cedula, int edad, String telefono, String cargo,double sueldo){
        super(nombre,cedula,edad,telefono);
        this.cargo=cargo;
        this.sueldo=sueldo;       
    }
    
    public String getCargo(){
        return cargo;
    }
    public double getSueldo(){
        return sueldo;
    }
    public void setCargo(String cargo){
        this.cargo=cargo;
    }
    public void setSueldo(double sueldo){
        this.sueldo=sueldo;
    }
    
    @Override
    public String toString(){
        return super.toString()+" |Cargo: "+cargo+" |Sueldo: "+sueldo;
    }
    
}
