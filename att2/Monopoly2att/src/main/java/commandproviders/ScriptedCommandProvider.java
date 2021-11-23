package commandproviders;
import utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ScriptedCommandProvider implements CommandProvider {

    private Scanner sc;

    public ScriptedCommandProvider(File file) throws FileNotFoundException {
        sc = new Scanner(file);
    }

    public ScriptedCommandProvider() {
        InputStream inputStream = FileUtils.getInputStreamFromResources("scripts" + FileUtils.sep + "script1.txt");

        if (inputStream != null) {
            sc = new Scanner(inputStream).useDelimiter("\\A");
        } else {
            sc = new Scanner("boss\ne4");
        }
    }

    @Override
    public String getNextLine() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return sc.nextLine();
    }


}
