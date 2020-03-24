package me.TechsCode;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import java.io.*;

public class GenerateMetaFilesTask extends DefaultTask {

    @TaskAction
    public void generateMetaFiles(){
        System.out.println(Color.GREEN_BRIGHT+"Generating Plugin.yml & Bungee.yml");

        File build = getProject().getBuildDir();
        File pluginYml = new File(build.getAbsolutePath()+"/src/resources/plugin.yml");
        File bungeeYml = new File(build.getAbsolutePath()+"/src/resources/bungee.yml");

        try {
            pluginYml.createNewFile();
            bungeeYml.createNewFile();

            MetaExtension meta = getProject().getExtensions().getByType(MetaExtension.class);

            PrintWriter writer = new PrintWriter(pluginYml, "UTF-8");
            writer.println("name: "+getProject().getName());
            writer.println("version: "+meta.version);
            writer.println("main: me.TechsCode."+getProject().getName()+".base.loader.SpigotLoader");
            writer.println("api-version: 1.13");
            if(meta.loadAfter != null) writer.println("softdepend: "+meta.loadAfter);
            if(meta.loadBefore != null) writer.println("loadbefore: "+meta.loadBefore);
            if(meta.load != null) writer.println("load: "+meta.load);
            writer.close();

            writer = new PrintWriter(bungeeYml, "UTF-8");
            writer.println("name: "+getProject().getName());
            writer.println("version: "+meta.version);
            writer.println("main: me.TechsCode."+getProject().getName()+".base.loader.BungeeLoader");
            writer.println("author: Tech");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
