package modelo;

public abstract class Persona {
    private String nombre;
    private String cedula;
    private int edad;
    private String telefono;
    
    public Persona(String nombre, String cedula, int edad, String telefono){
        this.nombre=nombre;
        this.cedula=cedula;
        this.edad=edad;
        this.telefono=telefono;
        
    }
    
    public String getNombre(){
        return nombre;
    }
    public String getCedula(){
        return cedula;
    }
    public int getEdad(){
        return edad;
    }
    public String getTelefono(){
        return telefono;
    }
    public void setEdad(int edad){
        this.edad=edad;
    }
    public void setTelefono(String telefono){
        this.telefono=telefono;
    }
    
    @Override
    public String toString(){
        return "Nombre: "+nombre+" |Cedula: "+cedula+" |Edad: "+edad+" |Telefono: "+telefono;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Persona otraPersona){
        return this.cedula.equals(otraPersona.cedula);
    } 
        return false;
    }
}
