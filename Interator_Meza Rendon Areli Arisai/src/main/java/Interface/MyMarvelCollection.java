package Interface;
import java.util.ArrayList;
import java.util.Iterator;
public class MyMarvelCollection implements Iterable<Film>
{
    private ArrayList<Film> films;
    public MyMarvelCollection()
    {
        this.films = new ArrayList<>();
    }
    public void add(Film film)
    {
        films.add(film);
    }
    public int size()
    {
        return films.size();
    }
    @Override

    public Iterator<Film> iterator()
    {
        return new MyMarvelIterator(films);
    }
    private class MyMarvelIterator implements Iterator<Film>
    {
        private int current;
        private ArrayList<Film> films;
        public MyMarvelIterator(ArrayList<Film> films)
        {
            this.films = films;
            current = 0;
        }
        @Override
        public boolean hasNext() {
            return current < films.size();
        }
        @Override
        public Film next() {
            return films.get(current++);
        }
    }
}