package gd.rf.acro.givemehats;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigUtils {

    public static Map<String,String> config = new HashMap<>();


    public static Map<String,String> loadConfigs()
    {
        File file = new File(FabricLoader.getInstance().getConfigDir().toString() + "/GiveMeHats/config.acfg");
        try {
            List<String> lines = FileUtils.readLines(file,"utf-8");
            lines.forEach(line->
            {
                if(line.charAt(0)!='#')
                {
                    String noSpace = line.replace(" ","");
                    String[] entry = noSpace.split("=");
                    config.put(entry[0],entry[1]);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static void generateConfigs(List<String> input)
    {
        File file = new File(FabricLoader.getInstance().getConfigDirectory().getPath() + "/GiveMeHats/config.acfg");

        try {
            FileUtils.writeLines(file,input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String,String> checkConfigs()
    {
        if(new File(FabricLoader.getInstance().getConfigDirectory().getPath() + "/GiveMeHats/config.acfg").exists())
        {
            return loadConfigs();
        }
        generateConfigs(makeDefaults());
        return loadConfigs();
    }

    private static List<String> makeDefaults()
    {
        List<String> defaults = new ArrayList<>();
        defaults.add("#to disable a hat set its weight to 0");
        defaults.add("bowlerhat=1");
        defaults.add("tophat=1");
        defaults.add("topesthat=1");
        defaults.add("bunnyears=1");
        defaults.add("spacehelmet=1");
        defaults.add("catears=1");
        defaults.add("cowboyhat=1");
        defaults.add("crown=1");
        defaults.add("deerstalkerhat=1");
        defaults.add("electricmouseears=1");
        defaults.add("fez=1");
        defaults.add("floatinghat=1");
        defaults.add("foxears=1");
        defaults.add("irishhat=1");
        defaults.add("russianhat=1");
        defaults.add("sailorhat=1");
        defaults.add("santahat=1");
        defaults.add("slimehat=1");
        defaults.add("striderhat=1");
        defaults.add("taterhat=1");
        defaults.add("woolrushat=1");
        defaults.add("witchhat=1");
        defaults.add("jojohat=1");
        defaults.add("wolfears=1");
        defaults.add("golembucket=1");
        defaults.add("#this should probably not go lower than 10, it makes it so you not always guaranteed a hat");
        defaults.add("air=10");
        defaults.add("#you can disable hats by changing these to anything apart from 1");
        defaults.add("enable_bowlerhat=1");
        defaults.add("enable_tophat=1");
        defaults.add("enable_topesthat=1");
        defaults.add("enable_bunnyears=1");
        defaults.add("enable_bunnyspacehelmet=1");
        defaults.add("enable_catears=1");
        defaults.add("enable_cowboyhat=1");
        defaults.add("enable_crown=1");
        defaults.add("enable_deerstalkerhat=1");
        defaults.add("enable_electricmouseears=1");
        defaults.add("enable_fez=1");
        defaults.add("enable_floatinghat=1");
        defaults.add("enable_foxears=1");
        defaults.add("enable_irishhat=1");
        defaults.add("enable_russianhat=1");
        defaults.add("enable_sailorhat=1");
        defaults.add("enable_santahat=1");
        defaults.add("enable_slimehat=1");
        defaults.add("enable_striderhat=1");
        defaults.add("enable_taterhat=1");
        defaults.add("enable_woolrushat=1");
        defaults.add("enable_witchhat=1");
        defaults.add("enable_jojohat=1");
        defaults.add("enable_wolfears=1");
        defaults.add("enable_golembucket=1");
        return defaults;
    }

}
