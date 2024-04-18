import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thook on 10/7/15.
 */
public class HamletParser {

    private String hamletData;

    public HamletParser(){
        this.hamletData = loadFile();
    }

    private String loadFile(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("hamlet.txt").getFile());
        StringBuilder result = new StringBuilder("");

        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return result.toString();
    }

    public String getHamletData(){
        return hamletData;
    }

    public void changeHamletToLeon() {
        Pattern patternHam = Pattern.compile("Hamlet");
        String replacement = "Leon";
        hamletData = changeText(patternHam, replacement);
        patternHam = Pattern.compile("HAMLET");
        String replacement2 = "LEON";
        hamletData = changeText(patternHam, replacement2);
    }

    public void changeHoratioToTariq() {
        Pattern patternHor = Pattern.compile("Horatio");
        String replacement = "Tariq";
        hamletData = changeText(patternHor, replacement);
        patternHor = Pattern.compile("HORATIO");
        String replacement2 = "TARIQ";
        hamletData = changeText(patternHor, replacement2);
    }

    public String changeText(Pattern pattern, String replacement){
        StringBuffer sb = new StringBuffer();
        Matcher matcher = pattern.matcher(hamletData);
        while(matcher.find()){
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public Boolean findHoratio() {
        Pattern patternHam = Pattern.compile("Horatio", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternHam.matcher(hamletData);

        return matcher.find();
    }

    public Boolean findHamlet() {
        Pattern patternHam = Pattern.compile("Hamlet", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternHam.matcher(hamletData);

        return matcher.find();
    }

}
