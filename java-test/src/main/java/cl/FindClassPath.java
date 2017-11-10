package cl;

/**
 * @author gaoyuxin.gyx@alibaba-inc.com on 08/11/2017.
 */

import java.io.File;
import java.net.URI;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * 从一个类找到它的加载的位置
 */
public class FindClassPath {

    public static void main(String[] args) throws Exception {
        ProtectionDomain protectionDomain = FindClassPath.class.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        URI location = (codeSource == null? null : codeSource.getLocation().toURI());
        String path = (location == null ? null : location.getSchemeSpecificPart());
        if (path == null) {
            throw new IllegalStateException("Unable to determine code source archive");
        }
        File root = new File(path);
        if (!root.exists()) {
            throw new IllegalStateException(
                "Unable to determine code source archive from " + root);
        }
        System.out.println(root.getPath());
    }

}
