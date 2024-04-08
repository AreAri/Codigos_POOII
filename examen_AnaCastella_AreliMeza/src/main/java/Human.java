public class Human extends Datos
{

    public Human(String nombre, int edad)
    {
        super(nombre, edad);
    }

    @Override
    public String toString()
    {
        return "Nombre: " + getNombre() + ", Edad: " + getEdad();
    }
}