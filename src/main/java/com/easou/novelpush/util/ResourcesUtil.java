package com.easou.novelpush.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Henn
 * Date: 12-8-20
 * Time: 下午2:43
 */
public final class ResourcesUtil {
    private ResourcesUtil() {
    }

    private static final String EXCEPTION_INFO = "Could not find resource : ";

    /**
     * Return the URL of the filename,if not found return null;
     *
     * @param  resource 查找的资源名
     * @return 资源的URL
     */
    public static URL getResourceURL(String resource) {
        ClassLoader classLoader = null;
        URL url = null;
        //Trying to find resource using context classloader
        classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            url = classLoader.getResource(resource);
            if (url != null) {
                return url;
            }
        }

        //Trying with the classloader that loaded this class to find resource
        classLoader = ResourcesUtil.class.getClassLoader();
        if (classLoader != null) {
            url = classLoader.getResource(resource);
            if (url != null) {
                return url;
            }
        }

        // Last ditch attempt: get the resource from the class path. It
        // may be the case that clazz was loaded by the Extentsion class
        // loader which the parent of the system class loader.
        return ClassLoader.getSystemResource(resource);

    }

    /**
     * Return the File of the filename
     *
     * @param resource
     * @return  File
     * @throws java.io.IOException
     */
    public static File getResourceAsFile(String resource) throws IOException {
        return new File(getResourceURL(resource).getFile());
    }

    /**
     *
     * Return the InputStream of the filename,if not found throw IOException;
     * @param resource
     * @return
     * @throws java.io.IOException
     */
    public static InputStream getResourceAsStream(final String resource) throws IOException {
        ClassLoader classLoader = null;
        InputStream is = null;
        //Trying to find resource using context classloader
        classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            is = classLoader.getResourceAsStream(resource);
            if (is != null) {
                return is;
            }
        }

        //Trying with the classloader that loaded this class to find resource
        classLoader = ResourcesUtil.class.getClassLoader();
        if (classLoader != null) {
            is = classLoader.getResourceAsStream(resource);
            if (is != null) {
                return is;
            }
        }

        // Last ditch attempt: get the resource from the class path. It
        // may be the case that clazz was loaded by the Extentsion class
        // loader which the parent of the system class loader.
        is = ClassLoader.getSystemResourceAsStream(resource);
        if (is == null) {
            throw new IOException(EXCEPTION_INFO + resource);
        }
        return is;
    }

    /**
     *
     * Return the Properties of the filename,if not found throw IOException;
     * @param resource
     * @return
     * @throws java.io.IOException
     */
    public static Properties getResourceAsProperties(String resource) throws IOException {
        Properties props = new Properties();
        InputStream is = null;
        try {
            is = getResourceAsStream(resource);
            props.load(is);
        } catch (Exception e) {
            throw new IOException(EXCEPTION_INFO + resource);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return props;
    }

}
