package Model.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.awt.Point;
import Model.cities.City;
import Model.cities.CityType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapsParser {
    private String country;
    private String continent;
    private HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
    private int[][] CityNuM;

    public MapsParser(String continent, String country) {
        this.country = country;
        this.continent = continent;
        parseMapFile();
        createDestinations();
       // printcity();

    }

    public void parseMapFile() {
        String fileName = "Resources/continents/" + continent.toLowerCase() + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            parse(br);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void parse(BufferedReader br) throws IOException {
        String line;
        boolean foundCountry = false;
        /* while its not the end of the file */
        while ((line = br.readLine()) != null) {
            if (line.equalsIgnoreCase(country)) {
                foundCountry = true;
            } else if (foundCountry) {
                String[] cities = line.split("   ");
                for (String city : cities) {
                    extractRoutes(city);
                }
                /* Assuming theres only one occurrence of the country */
                break;
            }
        }
    }

    public void extractRoutes(String c) {
        String[] r1 = c.split("->");
        int city = Integer.parseInt(r1[0]);
        String[] routes = r1[1].split(",");
        ArrayList<Integer> arr = new ArrayList<>();
        for (String route : routes) {
            arr.add(Integer.valueOf(route));
        }
        map.put(city, arr);

    }

    public void printMap() {
        for (Integer key : map.keySet()) {
            System.out.print("Key: " + key + ", Values: ");
            ArrayList<Integer> values = map.get(key);
            for (Integer value : values) {
            //    System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public void createDestinations() {
        ArrayList<Integer> allKeys = new ArrayList<>();

        // *Ajout de toutes les villes sources */
        for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            ArrayList<Integer> values = entry.getValue();
            allKeys.add(key);
            for (int value : values) {
                allKeys.add(value);
            }
        }

        /*
         * On supprimer tous les doublons. Donc ici il y a toutes les villes
         * chaque valeur index est une ville
         */
        Set<Integer> uniqueKeysSet = new HashSet<>(allKeys);
        allKeys = new ArrayList<>(uniqueKeysSet);

        // 12 , 15, 16
        //

     //   System.out.println("The size of the ArrayList is: " + allKeys.size());

        // chaque valeur = point est la l'adresse/endroit ou se situe la ville de
        // allKeys(i)
        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0; i < allKeys.size(); i++) {
            City city = new City(CityType.values()[allKeys.get(i)]);
            points.add(city.getCoordinates());
        }

        int[][] tab = LongestDistance(points, 10, allKeys);

        int tab1[][] = new int[10][3];
        for (int i = 0; i < tab.length; i++) {
            tab1[i][0] = allKeys.get(tab[i][0]);
            tab1[i][1] = allKeys.get(tab[i][1]);
            tab1[i][2] = tab[i][2];
        }
        this.CityNuM = tab1;
      //  printCity1test();
        return;

    }

    public double distance(Point p1, Point p2) {
        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Finds the longest distances between points in the given ArrayList.
     * et renvoie 2D . [i][0],[i][1] correspond dex villes
     *
     * @param points ArrayList points.
     * @param ROW    Nombre des distances Ã trouver
     * @return A 2D array containing the indices of the points that form the longest
     *         distances.
     */
    public int[][] LongestDistance(ArrayList<Point> points, int ROW, ArrayList<Integer> allKeys) {
        int[][] result = new int[ROW][3];
        int resultindex = 0;
        double maxDistance = 0;

        for (int i = 0; i < points.size(); i++) {
            int jindex = 0;
            for (int j = 0; j < points.size(); j++) {
                if (i == j)
                    continue;
                double distance = distance(points.get(i), points.get(j));
                if (distance == 0)
                    continue;
                if (distance > maxDistance) {
                    maxDistance = distance;
                    jindex++;
                }
            }
            if (maxDistance > 0) {
                if (allKeys.get(i) == allKeys.get(jindex))
                    continue;

                if(!Doesdestinaionexist(i, jindex, result)){
                    continue;
                }
                result[resultindex][0] = i;
                result[resultindex][1] = jindex;
                // result[resultindex][2] = assignPoints(distance);
                resultindex++;
                maxDistance = 0;
            }
            if (resultindex >= ROW) {
                break;
            }
        }
        return result;
    }

    public boolean Doesdestinaionexist(int source, int destination, int[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][0] == source && tab[i][1] == destination) {
                    System.out.println("nor possible first first");
                    return false;
                }

                if (tab[i][0] == destination && tab[i][1] == source) {
                    System.out.println("nor possible from second");
                    return false;
                }
            }

        }
        return true;
    }

    // public int[][] LongestDistance(ArrayList<Point> points, int ROW) {
    // int[][] result = new int[ROW][3];
    // int resultIndex = 0;
    // double maxDistance = 0;

    // for (int i = 0; i < points.size() - 1; i++) {
    // for (int j = i+1; j < points.size(); j++) {
    // double distance = distance(points.get(i), points.get(j));
    // if(i==j)continue;
    // if (distance > maxDistance) {
    // maxDistance = distance;
    // result[resultIndex][0] = i;
    // result[resultIndex][1] = j;
    // result[resultIndex][2] = 0;
    // }
    // }
    // }
    // return result;
    // }

public static int assignPoints(double number) {
    if (number >= 0 && number < 5) {
        return 2; 
    } else if (number >= 5 && number < 10) {
        return 4; 
    } else if (number >= 10 && number <= 15) {
        return 8; 
    } else if(number>15 && number <= 20) {
        return 10;
    }
    else {
        return 12;
    }
       
} 




    // public void printcity() {
    //     for (int i = 0; i < CityNuM.length; i++) {
    //         System.out.println(CityNuM[i][0] + " " + CityNuM[i][1]);

    //     }
    // }

    public HashMap<Integer, ArrayList<Integer>> getMap() {
        return map;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setMap(HashMap<Integer, ArrayList<Integer>> map) {
        this.map = map;
    }

    public int[][] getCityNuM() {
        return CityNuM;
    }

    public void setCityNuM(int[][] cityNuM) {
        CityNuM = cityNuM;
    }

    public void printCity1test() {

        // int ville1= Destinations[i][0];
        // int ville2= Destinations[i][1];
        // City city1=new City(CityType.values()[ville1]);
        // City city2=new City(CityType.values()[ville2]);
        for (int i = 0; i < CityNuM.length; i++) {
            int ville1 = CityNuM[i][0];
            int ville2 = CityNuM[i][1];
            int point1 = CityNuM[i][2];
            System.out.println(CityType.values()[ville1]);
            System.out.println(CityType.values()[ville2]);
            System.out.println(point1);
            City city = new City(CityType.values()[ville1]);
            City city2 = new City(CityType.values()[ville2]);
            Point vill1c = city.getCoordinates();
            Point vill2c = city2.getCoordinates();

        }
    }

}
