import java.util.Comparator;

public class Pakuri implements Comparable<Pakuri>
{
    //private fields of class Pakuri
    private String species;
    private int attack, defense, speed;

    //constructor
    public Pakuri(String species)
    {
        this.species = species;
        this.attack = (species.length() * 7) + 9;
        this.speed = (species.length() * 5) + 17;
        this.defense = (species.length() * 6) + 13;
    }

    //field species getter
    public String getSpecies()
    {
        return species;
    }

    //field species setter
    public void setSpecies(String s)
    {
        this.species = s;
    }

    //field attack getter
    public int getAttack()
    {
        return this.attack;
    }

    //field attack setter
    public void setAttack(int a)
    {
        this.attack = a;
    }

    //field defense getter
    public int getDefense()
    {
        return this.defense;
    }

//    //field defense setter
//    public void setDefense(int d)
//    {
//        this.defense = d;
//    }

    //field speed getter
    public int getSpeed()
    {
        return this.speed;
    }

//    //field speed setter
//    public void setSpeed(int s)
//    {
//        this.speed = s;
//    }

    //methods for Pakuri class objects
    public void evolve()
    {
        this.attack = getAttack() * 2;
        this.defense = getDefense() * 4;
        this.speed = getSpeed() * 3;
    }

    @Override
    public int compareTo(Pakuri pakuri)
    {
        return this.species.compareTo(pakuri.species);
    }

//    @Override
//    public String toString()
//    {
//        return "Pakuri: " +
//                "Species:" + species +
//                "Attack:" + attack +
//                "Defense:" + defense +
//                "Speed:" + speed + "\n";
//    }

}
