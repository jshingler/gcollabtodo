import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

public final class ResourceUtils {

	private static final ResourceUtils INSTANCE = new ResourceUtils();
    private ResourceBundle bundle;
    private ClassLoader defaultClassLoader;

    private ResourceUtils()
    {
        defaultClassLoader = (ResourceUtils.class).getClassLoader();
    }

    public static void setBundlePath(String path)
    {
        INSTANCE.bundle = ResourceBundle.getBundle(path);
    }

    public static String getString(String key)
    {
		try {
			return INSTANCE.bundle.getString(key);
		} catch (MissingResourceException e) {
//			 Not an error because we will use the default
//			System.err.println("Missing resource for key:" + key);
//			e.printStackTrace();
		}
		return null;
	}

    public static ImageIcon getIcon(String key)
    {
        String path = getString(key);
        return null != path ? readImageIcon(path) : null;
    }

    private static URL getURL(String path)
    {
        return getURL(path, INSTANCE.defaultClassLoader);
    }

    private static URL getURL(String path, ClassLoader classLoader)
    {
        URL url = classLoader.getResource(path);
        if(null == url)
            System.err.println(path + " not found.");
        return url;
    }

    public static ImageIcon readImageIcon(String path)
    {
        URL url = getURL(path);
        return null != url ? new ImageIcon(url) : null;
    }

    

}
