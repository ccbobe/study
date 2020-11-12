package com.ccbobe;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 根据properties中配置的路径把jar和配置文件加载到classpath中。<br>
 * 此工具类加载类时使用的是SystemClassLoader，如有需要对加载类进行校验，请另外实现自己的加载器 *
 */
@Slf4j
@Component
public class ExtClasspathLoader {
    private static final Logger LOG = LoggerFactory.getLogger(ExtClasspathLoader.class);

    private static final String JAR_SUFFIX = ".class";
    private static final String ZIP_SUFFIX = ".java";

    /**
     * URLClassLoader的addURL方法
     */
    private static Method addURL = initAddMethod();

    /**
     * Application Classloader
     */
    private static URLClassLoader classloader = (URLClassLoader) ClassLoader.getSystemClassLoader();

    /**
     * 初始化addUrl 方法.
     *
     * @return 可访问addUrl方法的Method对象
     */
    private static Method initAddMethod() {
        try {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            add.setAccessible(true);
            return add;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过filepath加载文件到classpath。
     *
     * @param file 文件路径
     */
    private static void addURL(File file) throws Exception {
        addURL.invoke(classloader, file.toURI().toURL());
        System.out.println(file.toURI().getRawPath());
    }

    /**
     * load Resource by Dir
     *
     * @param file dir
     */
    public static void loadResource(File file) throws Exception {
        // 资源文件只加载路径
        LOG.info("load Resource of dir : " + file.getAbsolutePath());
        if (file.isDirectory()) {
            addURL(file);
            File[] subFiles = file.listFiles();
            if (subFiles != null) {
                for (File tmp : subFiles) {
                    loadResource(tmp);
                }
            }
        }
    }

    /**
     * load Classpath by Dir
     *
     * @param file Dir
     */
    public void loadClasspath(File file) throws Exception {
        LOG.info("load Classpath of dir : " + file.getAbsolutePath());
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    loadClasspath(subFile);
                }
            }
        } else {
            if (file.getAbsolutePath().endsWith(JAR_SUFFIX) || file.getAbsolutePath().endsWith(ZIP_SUFFIX)) {
                addURL(file);
            }
        }
    }

    public static URLClassLoader getClassloader() {
        return classloader;
    }


    /**
     * 扫描包路径下的所有class文件
     *
     * @param pkgName 包名
     * @param pkgPath 包对应的绝对地址
     * @param classes 保存包路径下class的集合
     */
    public static void findClassesByFile(String pkgName, String pkgPath, Set<Class<?>> classes) {
        File dir = new File(pkgPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }


        // 过滤获取目录，or class文件
        File[] dirfiles = dir.listFiles(pathname -> pathname.isDirectory() || pathname.getName().endsWith("class"));


        if (dirfiles == null || dirfiles.length == 0) {
            return;
        }


        String className;
        Class clz;
        for (File f : dirfiles) {
            if (f.isDirectory()) {
                findClassesByFile(pkgName + "." + f.getName(),
                        pkgPath + "/" + f.getName(),
                        classes);
                continue;
            }


            // 获取类名，干掉 ".class" 后缀
            className = f.getName();
            className = className.substring(0, className.length() - 6);

            // 加载类
            clz = loadClass(pkgName + "." + className);
            if (clz != null) {
                classes.add(clz);
            }
        }
    }


    /**
     * 扫描包路径下的所有class文件
     *
     * @param pkgName 包名
     * @param jar     jar文件
     * @param classes 保存包路径下class的集合
     */
    public static void findClassesByJar(String pkgName, JarFile jar, Set<Class<?>> classes) {
        String pkgDir = pkgName.replace(".", "/");


        Enumeration<JarEntry> entry = jar.entries();

        JarEntry jarEntry;
        String name, className;
        Class<?> claze;
        while (entry.hasMoreElements()) {
            jarEntry = entry.nextElement();

            name = jarEntry.getName();
            if (name.charAt(0) == '/') {
                name = name.substring(1);
            }


            if (jarEntry.isDirectory() || !name.startsWith(pkgDir) || !name.endsWith(".class")) {
                // 非指定包路径， 非class文件
                continue;
            }


            // 去掉后面的".class", 将路径转为package格式
            className = name.substring(0, name.length() - 6);
            claze = loadClass(className.replace("/", "."));
            if (claze != null) {
                classes.add(claze);
            }
        }
    }


    private static Class<?> loadClass(String fullClzName) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(fullClzName);
        } catch (ClassNotFoundException e) {
            log.error("load class error! clz: {}, e:{}", fullClzName, e);
        }
        return null;
    }
}
