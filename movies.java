/*
* Phillip Valdez
* CSC 223
* */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class movies {
    public static void main(String[] args) throws IOException{
        displayMovie(args);
    }
    static void displayMovie(String[] args) throws IOException {
        int counter, count, max_length, whitespace;
        try{
            count = 0;
            max_length = 0;

            File csv = new File(args[0]);
            RandomAccessFile read = new RandomAccessFile(csv, "r");
            while(read.readLine() != null){
                count++;
            }
            read.seek(0);
            String[][] arr = new String[count][3];
            String[] split;
            // Split each line into 2D array
            for (String[] value : arr) {
                String line = read.readLine();
                split = line.split(",");
                System.arraycopy(split, 0, value, 0, value.length);
            }

            // Match duplicates
            for(int i = 0; i < arr.length; i++){
                String temp = arr[i][1];
                if(temp.length() > max_length){
                    if(temp.length() > 44){
                        max_length = 45;
                    }else{
                        max_length = temp.length();
                    }
                }
                counter = 0;
                for(int k = i; k < arr.length; k++){
                    try {
                        if (arr[k + 1][1].equals(temp)) {
                            arr[i][0] += (" " + arr[k + 1][0]);
                            Arrays.fill(arr[k + 1], "0");
                            counter++;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        break;
                    }
                }
                i += counter;
            }

            // Minimize data structure, where '0' will be deleted
            List<List<String>> list = new ArrayList<>(); // Create arraylist
            for(String[] a: arr){
                list.add(Arrays.asList(a));
            }
            list.removeIf(x -> x.contains("0"));

            // Convert back to 2D array
            String[][] final_arr = new String[list.size()][];
            for(int i = 0; i < list.size(); i++){
                List<String> row = list.get(i);
                final_arr[i] = row.toArray(new String[row.size()]);
            }
            // Display contents
            for(int i = 0; i < final_arr.length; i++){
                if(final_arr[i][1].length() > 44){
                    final_arr[i][1] = final_arr[i][1].substring(0, 44);
                    System.out.print(final_arr[i][1]);
                }else{
                    System.out.print(final_arr[i][1]);
                }
                whitespace = max_length - final_arr[i][1].length();
                for(int k = 0; k < whitespace; k++){
                    System.out.print(" ");
                }
                System.out.printf("| %5s| %s%n", final_arr[i][2], final_arr[i][0]);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
