import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {


    public static void main(String[] args) {
        double[][] distance = new double[40][40];
        distance = distanceColculation();
        String[] names = {"Halim Bakkal ", "Dilara Akinci ", "Feray Parlak ", "Deniz Remzi ", "Candon Boztepe ", "Özlem Sadak ", "Sevil Demirkan ", "Meryem Özsoy ", "Yonca Demirci ", "Birsen Tüfekçi ", "Tugay Çolak ", "Durul Birkan ", "Banu Kiraç ", "Ajda Çevik ", "Ece Koçer ", "Banu Savas ", "Aydin Sezen ", "Ender Enver ", "Dilay Gülek ", "Hizir Namli ", "Arslan Halefoglu ", "Damla Mardin ", "Dilara Gülek ", "Buğra Dede ", "Doruk Yildizeli ", "Öykü Gözübüyük ", "Esma Dervis ", "Yonca Topbas ", "Adnan Tüfekçi ", "Doga Isik ", "Bahar Nazmi ", "Şenay Baba ", "Himmet Dereli ", "Seda Akçatepe ", "Tuba Atay ", "Esra Sensoy ", "Ceyda Kunt ", "Aylin Cengiz ", "Özgür Çatli ", "Sanaz Çakir ", "Direnç Aydogan "};
        String[][] array = new String[10][4]; // this line is our bus
        List<Integer> myList = new ArrayList<>(); // keeps distances between passengers
        myList = placingPassengers(distance);
        double[] array2 = new double[40]; // sums the distances between the passengers we randomly place
        array2[0] = 0;
        for (int i = 1; i < 4; i++) {
            array2[i] = distance[myList.get(i)][myList.get(i - 1)];
        }
        //The code block above allows us to find the distances of the people sitting in the first line according to the rule in the project.
        //The code below allows us to sum the distance between the remaining passengers and other passengers within the scope of the rule in the project.
        for (int i = 4; i < 40; i++) {
            if (i % 4 == 0) {
                array2[i] = distance[myList.get(i)][myList.get(i - 4)] + distance[myList.get(i)][myList.get(i - 3)];
            }
            if (i % 4 == 1 || i % 4 == 2) {
                array2[i] = distance[myList.get(i)][myList.get(i - 4)] + distance[myList.get(i)][myList.get(i - 3)]
                        + distance[myList.get(i)][myList.get(i - 1)] + distance[myList.get(i)][myList.get(i - 5)];
            }

            if (i % 4 == 3) {
                array2[i] = distance[myList.get(i)][myList.get(i - 4)] + distance[myList.get(i)][myList.get(i - 5)] + distance[myList.get(i)][myList.get(i - 1)];
            }
        }

        // the following code seat passengers
        int c = 0;
        int a = 0;
        int b = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                array[i][j] = names[myList.get(a++)];
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(b++ + "." + array[i][j] + " distance :  " + array2[c++] + "||");
            }
            System.out.println("*****");
        }
        //below are the lines of code where i found the total distance
        double f = 0;
        for (int i = 0; i < 40; i++) {
            f += array2[i];
        }
        System.out.println("Total Distance : " + f);


    }

    /*
    the following code sequence allows us to randomly place the passengers in the seats
    */
    public static List<Integer> placingPassengers(double[][] distance) {
        List<Integer> bus = new ArrayList<>(40);
        int random = new Random().nextInt(40);
        bus.add(0, random);
        for (int i = 1; i < 4; i++) {
            double tempMax = 9999;
            int tempPassenger = 0;
            for (int j = 0; j < 40; j++) {
                if (distance[bus.get(i - 1)][j] != 0) {
                    if (distance[bus.get(i - 1)][j] < tempMax && !bus.contains(j)) {
                        tempMax = distance[bus.get(i - 1)][j];
                        tempPassenger = j;

                    }

                }

            }

            bus.add(i, tempPassenger);
        }
        for (int i = 4; i < 40; i++) {
            int column = i % 4;
            double tempMax = 9999;
            int tempPassenger = 0;
            if (column == 0) {
                for (int j = 0; j < 40; j++) {
                    if (distance[bus.get(i - 4)][j] != 0 || distance[bus.get(i - 3)][j] != 0) {
                        if (distance[bus.get(i - 4)][j] + distance[bus.get(i - 3)][j] < tempMax && !bus.contains(j)) {
                            tempMax = distance[bus.get(i - 4)][j] + distance[bus.get(i - 3)][j];
                            tempPassenger = j;
                        }

                    }

                }
                bus.add(i, tempPassenger);
            }
            if (column == 1) {
                for (int j = 0; j < 40; j++) {
                    if (distance[bus.get(i - 4)][j] != 0 || distance[bus.get(i - 3)][j] != 0 || distance[bus.get(i - 5)][j] != 0 || distance[bus.get(i - 1)][j] != 0) {
                        if (distance[bus.get(i - 4)][j] + distance[bus.get(i - 3)][j] + distance[bus.get(i - 5)][j] + distance[bus.get(i - 1)][j] < tempMax && !bus.contains(j)) {
                            tempMax = distance[bus.get(i - 4)][j] + distance[bus.get(i - 3)][j] + distance[bus.get(i - 5)][j] + distance[bus.get(i - 1)][j];
                            tempPassenger = j;
                        }

                    }

                }
                bus.add(i, tempPassenger);
            }
            if (column == 2) {
                for (int j = 0; j < 40; j++) {
                    if (distance[bus.get(i - 4)][j] != 0 || distance[bus.get(i - 3)][j] != 0 || distance[bus.get(i - 5)][j] != 0 || distance[bus.get(i - 1)][j] != 0) {
                        if (distance[bus.get(i - 4)][j] + distance[bus.get(i - 3)][j] + distance[bus.get(i - 5)][j] + distance[bus.get(i - 1)][j] < tempMax && !bus.contains(j)) {
                            tempMax = distance[bus.get(i - 4)][j] + distance[bus.get(i - 3)][j] + distance[bus.get(i - 5)][j] + distance[bus.get(i - 1)][j];
                            tempPassenger = j;
                        }

                    }

                }
                bus.add(i, tempPassenger);
            }
            if (column == 3) {
                for (int j = 0; j < 40; j++) {
                    if (distance[bus.get(i - 4)][j] != 0 || distance[bus.get(i - 5)][j] != 0 || distance[bus.get(i - 1)][j] != 0) {
                        if (distance[bus.get(i - 4)][j] + distance[bus.get(i - 3)][j] + distance[bus.get(i - 1)][j] < tempMax && !bus.contains(j)) {
                            tempMax = distance[bus.get(i - 4)][j] + distance[bus.get(i - 5)][j] + distance[bus.get(i - 1)][j];
                            tempPassenger = j;
                        }

                    }

                }
                bus.add(i, tempPassenger);
            }
        }


        return bus;
    }

    /*
    the following method contains the code sequence that allows us to find the distance matrix
     */
    public static double[][] distanceColculation() {
        double a[][] = new double[40][40];
        for (int i = 0; i < 40; i++) {
            for (int j = i; j < 40; j++) {
                if (i - j == 0) {
                    a[i][j] = 0;
                } else {
                    double start = 0;
                    double end = 10;
                    double random = new Random().nextDouble();
                    double result = start + (random * (end - start));
                    a[i][j] = result;
                    a[j][i] = result;
                }
            }
        }
        return a;

    }
}


