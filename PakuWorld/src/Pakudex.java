import java.util.*;

public class Pakudex
{
    //The Pakudex class will contain all the Pakuri that you will encounter as Pakuri objects
    //The Pakudex will have a set size determined by user input at the beginning of the program’s run;
    //the number of species contained in the Pakudex will never grow beyond this point


    //local variables for managing data
    private final int capacity;
    private ArrayList<Pakuri> pakuris = new ArrayList<>();

    //In some methods below, I am using Iterator, as opposed to using a for loop to run through the ArrayList
    // and using get() to retrieve the Pakuri species, to compare strings

    //Initializes Pakudex objects to contain exactly at capacity objects when completely full
    //  Default Constructor - size is 20
    public Pakudex()
    {
        capacity = 20;
    }

    //Overload Constructor - user can specify size of Pakudex
    public Pakudex(int c)
    {
        capacity = c;
    }

    //Returns the number of critters currently being stored in the Pakudex
    public int getSize()
    {
        return pakuris.size();
    }

    //Returns the number of critters that the Pakudex has the capacity to hold at most
    public int getCapacity()
    {
        return capacity;
    }

    //Returns a String array containing the species of the critters as ordered in the Pakudex.
    //If there are no species added yet, this method should return null
    public String[] getSpeciesArray()
    {
        if(pakuris.size() != 0)
        {
            String[] speciesArray = new String[getCapacity()];  //variable for return
            int count = 0;  //variable for adding to index of the return array

            for (Pakuri p : pakuris) {
                speciesArray[count] = p.getSpecies();   //add to return array
                count++;
            }

            return speciesArray;
        }
        else
        {
            return null;
        }

    }

    //Returns an int array containing the attack, defense, and speed statistics of species
    //at indices 0, 1, and 2 respectively; if species is not in the Pakudex, returns null
    public int[] getStats(String species)
    {
        //todo - do not create new object without getting from the DB
        Pakuri pakuri = new Pakuri(species);
        int[] statsArray = new int[3];

        int attack = pakuri.getAttack();
        int defense = pakuri.getDefense();
        int speed = pakuri.getSpeed();

        statsArray[0] = attack;
        statsArray[1] = defense;
        statsArray[2] = speed;

        return statsArray;
    }

    //Sorts the Pakuri objects in this Pakudex according to Java standard lexicographical ordering of species name
    public void sortPakuri()
    {
        Collections.sort(pakuris);
    }

    //Decides if a species can be added to the Pakudex
    public boolean addPakuri(String species)
    {
        Pakuri pakuri = new Pakuri(species);
        //default result is to not add a new species to array
        boolean response = false;

        //if it is the first entry, add it to pakuris ArrayList
        if(pakuris.size() == 0)
        {
            pakuris.add(pakuri);
            response = true;

        }
        //for all other entries made
        else
        {
            //need a variable to check all entries for a match before adding an entry
            int noMatch = 0;
            Iterator<Pakuri> iterator = pakuris.iterator();

            while (iterator.hasNext())
            {
                //create a current object of the Pakuri obj in pakuris ArrayList
                Pakuri p = iterator.next();
                //use Comparable method to find if there is a match in pakuris
                int matched = p.compareTo(pakuri);

                if(matched != 0)
                {
                    noMatch++;  //add up how many are not the name passed to this method
                }
                //if there is a match, do nothing for now
            }

            //if the number of non-matched names is the size of the current pakuris ArrayList
            if(noMatch == pakuris.size())
            {
                pakuris.add(pakuri);
                response = true;
            }
            //otherwise there was a match, do not add to ArrayList, and response is still false
        }
        return response;
    }

    //Attempts to evolve species within the Pakudex; if successful, return true, and false otherwise
    public boolean evolveSpecies(String species)
    {
        if(pakuris.size() != 0)
        {
            //create a database of pakuri species and their position in the collection
            Dictionary<String, Integer> thePakudex = new Hashtable<>();
            Integer count = 0;

            //write entries to the database
            for (Pakuri p : pakuris)
            {
                String name = p.getSpecies();
                thePakudex.put(name, count);
                count++;
            }

            try
            {
                //attempt to obtain the index of the species entered by user
                int position  = thePakudex.get(species);
                pakuris.get(position);
                return true;
            }
            catch(Exception e)
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}
