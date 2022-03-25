import java.util.*;

public class PakuriProgram
{
    public static void main(String[] args)
    {
        //Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        //create a collection object of type Pakuri for the Pakudex object to access
        ArrayList<Pakuri> pakuris = new ArrayList<>();

        //items required for user interaction below
        boolean isPakudexDefault;

        boolean runProgram = true;

        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");

        while(runProgram)
        {
            //begin game
            System.out.print("Enter max capacity of the Pakudex: ");

            //initialize a capacity size
            int c = 0;

            //take user input of any type
            String capacity = scanner.next();

            //if the user enters no data, then default Pakudex is needed
            if(capacity.equals(" "))
            {
                isPakudexDefault = true;
            }
            else
            {
                try
                {
                    c = Integer.parseInt(capacity);
                } catch (Exception e)
                {
                    System.out.println("Please enter a valid size.");
                    continue;
                }

                //once the input is confirmed to be an integer, validate a value
                c = Integer.parseInt(capacity);
                if (c >= 1)
                {
                    isPakudexDefault = false;
                } else
                {
                    System.out.println("Please enter a valid size.");
                    continue;
                }
            }

            //based on if the input was empty or an integer, create the Pakudex object
            Pakudex pakudex = (isPakudexDefault) ? new Pakudex() : new Pakudex(c);
            //inform user of created pakudex
            System.out.println("The Pakudex can hold " + pakudex.getCapacity() + " species of Pakuri.");

            //Allow user to interact with their Pakudex object
            do
            {
                //show main menu
                String header = "\nPakudex Main Menu\n";
                String buffer = "-----------------\n";
                String pakurui = "Pakuri\n";
                String next = "\nWhat would you like to do? ";
                System.out.print(header + buffer
                        + "1. List " + pakurui
                        + "2. Show " + pakurui
                        + "3. Add " + pakurui
                        + "4. Evolve " + pakurui
                        + "5. Sort " + pakurui
                        + "6. Exit " + next);

                //collect input from user
                String userSelect = scanner.next();

                try
                {
                    int userIn = Integer.parseInt(userSelect);
                }
                catch(Exception e)
                {
                    System.out.println("Unrecognized menu selection!");
                    continue;
                }

                //operate on the menu option selected
                int userIn = Integer.parseInt(userSelect);

                //list all pakuri in pakudex
                if(userIn == 1)
                {
                    //call for the pakuri array
                    String[] speciesArray = pakudex.getSpeciesArray();

                    //if the pakudex is empty
                    if(speciesArray == null)
                    {
                        System.out.println("\nNo Pakuri in Pakudex yet!");
                    }
                    else
                    {
                        System.out.println("\nPakuri in Pakudex:");
                        //for each item in the array, list the Pakuri's name
                        for(int i = 0; i < pakuris.size(); i++)
                        {
                            System.out.println( i+1 + ". " + speciesArray[i]);
                        }
                    }
                }
                //show current stats of desired species in pakudex
                else if (userIn == 2)
                {
                    //Pakudex.addPakuris() will actually add the requested species
                    //to the array in Pakudex class, I cannot use addPakuri to verify input.
                    //todo - when there are no pakuri in pakuris, showing does not tell user there is no such species

                    //Variables to assist with comparing objects and validate user input.
                    Integer lookup = 0;
                    Dictionary<String, Integer> thePakudex = new Hashtable<>();

                    System.out.print("\nEnter the name of the species to display: ");
                    String pakuriSpecies = scanner.next();

                    //get pakuri element, compare species, to ensure it exists
                    Iterator<Pakuri> iterator = pakuris.iterator();

                    //in case there are no entries in pakuris
                    if(pakuris.size() != 0)
                    {
                        //build the database (Dictionary of current Pakuri in pakuris)
                        Integer count = 0;
                        while (iterator.hasNext())
                        {
                            Pakuri p = iterator.next();
                            String name = p.getSpecies();
                            thePakudex.put(name, count);
                            count++;
                        }

                        //from thePakudex, find if there is a match
                        try
                        {
                            lookup = thePakudex.get(pakuriSpecies);
                        }
                        catch (Exception e)
                        {
                            System.out.println("Error: No such Pakuri!");
                            continue;
                        }

                        lookup = thePakudex.get(pakuriSpecies);
                        if(lookup != null)
                        {
                            //obtain stats
                            int[] pakuriStats = pakudex.getStats(pakuriSpecies);
                            //list the stats
                            int atk = pakuriStats[0];
                            int def = pakuriStats[1];
                            int spd = pakuriStats[2];
                            System.out.println("Species: " + pakuriSpecies
                                    + "\nAttack: " + atk
                                    + "\nDefense: " + def
                                    + "\nSpeed: " + spd);
                        }
                        else
                        {
                            System.out.println("Error: No such Pakuri!");
                        }
                    }
                }
                //add new species to pakuris ArrayList
                else if (userIn == 3)
                {
                    if(pakuris.size() == pakudex.getCapacity())
                    {
                        System.out.println("\nError: Pakudex is full!");
                        continue;
                    }

                    System.out.print("\nEnter the name of the species to add: ");
                    String species = scanner.next();
                    boolean result = pakudex.addPakuri(species);

                    if(result)
                    {
                        //create a Pakuri object for tracking the current Pakuri data
                        Pakuri pakuri = new Pakuri(species);
                        pakuris.add(pakuri);
                        System.out.println("Pakuri species " + species + " successfully added!");
                    }
                    else
                    {
                        //the pakudex already contained that pakuri
                        System.out.println("\nError: Pakudex already contains this species!");
                    }

                }
                //evolve a species in pakuris ArrayList
                else if( userIn == 4)
                {
                    System.out.print("\nEnter the name of the species to evolve: ");
                    String pakuriSpecies = scanner.next();

                    //evolveSpecies method will confirm if the species entered is in the pakudex
                    boolean canEvolve = pakudex.evolveSpecies(pakuriSpecies);

                    if (canEvolve)
                    {
                        //create a database of pakuri species and their position in the collection
                        Dictionary<String, Integer> thePakudex = new Hashtable<>();
                        Integer count = 0;

                        //write to the database (actually a dictionary)
                        for (Pakuri p : pakuris)
                        {
                            String name = p.getSpecies();
                            thePakudex.put(name, count);
                            count++;
                        }

                        //obtain the index of the species entered by user in the database
                        int positon = thePakudex.get(pakuriSpecies);

                        //call the pakuris ArrayList to obtain the stored object
                        Pakuri evolvePakuri = pakuris.get(positon);

                        //change the object
                        evolvePakuri.evolve();

                        //override the object
                        pakuris.set(positon, evolvePakuri);

                        System.out.println(pakuriSpecies + " has evolved!");
                    }
                    else
                    {
                        System.out.println("Error: No such Pakuri!");
                    }
                }
                //
                else if(userIn == 5)
                {
                    pakudex.sortPakuri();
                    System.out.println("Pakuri have been sorted!");
                }
                else if(userIn == 6)
                {
                    //Quit
                    System.out.println("Thanks for using Pakudex! Bye!");
                    runProgram = false;
                }
                else
                {
                    System.out.println("Unrecognized menu selection!");
                }

            } while(runProgram);
        }
    }   //end main
}   //end PakuriProgram
