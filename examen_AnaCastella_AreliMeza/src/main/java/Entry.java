public class Entry
{
    public static void main(String[] args)
    {
        // Ejemplo con Humanos
        Human hombre = new Human("Juan", 50);
        Human mujer = new Human("Karina", 45);
        Human[] hijosHumanos = new Human[3];
        hijosHumanos[0] = new Human("Hugo", 20);
        hijosHumanos[1] = new Human("Paco", 18);
        hijosHumanos[2] = new Human("Luis", 15);
        Family<Human> familiaHumana = new Family<>(hombre, mujer, hijosHumanos);
        System.out.println(familiaHumana);

        // Ejemplo con Perros
        Dog perroMacho = new Dog("Jimmy", 8);
        Dog perroHembra = new Dog("Julie", 6);
        Dog[] cachorros = new Dog[2];
        cachorros[0] = new Dog("Toto", 1);
        cachorros[1] = new Dog("Nemo", 2);
        Family<Dog> familiaPerro = new Family<>(perroMacho, perroHembra, cachorros);
        System.out.println(familiaPerro);
    }
}