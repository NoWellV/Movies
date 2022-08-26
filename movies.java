import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class movies {
    public static void main(String[] args) throws IOException{
        displayMovie(args);
    }
    static void displayMovie(String[] args) throws IOException {
        int count = 0;
        String time, title, rating, initial, temp;
        try {
            File csv = new File(args[0]);
            BufferedReader buffRead = new BufferedReader(new FileReader(csv));

            while(buffRead.readLine() != null){
                count++;
            }
            String[][] arr = new String[count][3];

            for(int i = 0; i < arr.length; i++){
                initial = buffRead.readLine();
                String[] line = initial.split(",");
                temp = line[1];
                if(i > 0 && temp == ){

                }else{
                    for(int k = 0; k < arr[i].length; k++){
                        arr[i][k] = line[i];
                    }
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
