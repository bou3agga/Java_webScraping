import java.io.*;
import java.util.Scanner;
import org.jsoup.Jsoup;

public class AmazonScraping {
    public static String  ReviewsSectionFinder (Scanner reviews){
        boolean bool= false;
        String str ="";
        while (reviews.hasNextLine()){
            String line = reviews.nextLine();
            if(line.contains("reviewsMedley")){
                bool=true;
            }
            if(bool)
                str+=line+"\n";
            if(line.contains("</div></div></div></div></div><script type=\"text/javascript\">"))
            {break;}
        }
        return str;
    }

    public static String  ReviewsFinder (Scanner reviews){
        boolean bool= false;
        String str ="";
        int i=0;

        while (reviews.hasNextLine()){
            String line = reviews.nextLine();

            if(bool && line.contains("===")==false)
                str+=line.replaceAll("\\<.*?\\>", "")+"\n";



            if(line.contains("<span")){
                bool=true;
            }
            else {
                bool=false;
            }
               i=i+1;

        }
        System.out.println(i);
        return str;
    }

    public static void main(String[] args) {

        File myFile = new File("/home/cj/Downloads/amazon.html");
        //File myFile = new File("/home/cj/Downloads/pred.html");
        Scanner file;

        {
            try {
                file = new Scanner(myFile);
                String ch=ReviewsSectionFinder(file);
                Scanner scanner = new Scanner(ch);
                String ch2 =ReviewsFinder(scanner);
                System.out.println(ch2);
                PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
                writer.print(ch2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
