public class Family<T extends Datos>
{

    private T padre;
    private T madre;
    private T[] hijos;

    public Family(T padre, T madre, T[] hijos)
    {
        this.padre = padre;
        this.madre = madre;
        this.hijos = hijos;
    }

    public T getPadre()
    {
        return padre;
    }

    public T getMadre()
    {
        return madre;
    }

    public T[] getHijos()
    {
        return hijos;
    }

    public T getChild(int i)
    {
        return hijos[i];
    }

    @Override
    public String toString()
    {
        Family<Datos> Arrays;
        return String.format("Padre: %s\nMadre: %s\nHijos:\n%s",
                padre, madre, Arrays.toString(hijos));
    }

    public static <T extends Datos> Family<T> crearFamilia(T padre, T madre, T[] hijos)
    {
        return new Family<>(padre, madre, hijos);
    }
}