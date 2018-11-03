package com.forsrc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.regex.Matcher;

public class JarUtils {

    public static File buildJar(Class<?> cls) throws IOException {
        return buildJar(cls, cls.getName(), true);
    }

    public static File buildJar(Class<?> cls, String jarName, boolean delete) throws IOException {
        System.out.println("build jar; " + jarName + " -> " + cls.getName());
        String className = cls.getName();
        String packageName = className.indexOf(".") < 0 ? "" : className.substring(0, className.lastIndexOf("."));
        packageName = packageName.replaceAll("\\.", Matcher.quoteReplacement("/"));
        URL root = cls.getResource("");
        final File jar = File.createTempFile(jarName + "-", ".jar", new File(System.getProperty("java.io.tmpdir")));

        if (delete) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    jar.delete();
                    System.out.println("delete jar: " + jar);
                }
            });
        }
        File path = new File(root.getFile());
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().putValue("Manifest-Version", "1.0");
        manifest.getMainAttributes().putValue("Created-By", "RemoteHadoopUtil");
        manifest.getMainAttributes().putValue("Main-Class", className);
        try (JarOutputStream out = new JarOutputStream(new FileOutputStream(jar), manifest)) {
            write(out, path, packageName);
        }
        return jar;
    }

    public static void write(JarOutputStream out, File file, String root) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                write(out, f, root + "/" + f.getName());
            }
            return;
        }
        out.putNextEntry(new JarEntry(root));
        byte[] buffer = new byte[1024];
        try (FileInputStream in = new FileInputStream(file)) {
            int n = 0;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File jar = buildJar(JarUtils.class);
        System.out.println(jar + " -> " + jar.length());
    }
}
