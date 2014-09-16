package io.github.ledge.utils;

import com.google.common.collect.Lists;
import org.lwjgl.LWJGLUtil;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.List;

public class LwjglUtils {

    // TODO: make it export to the game dir (have an appdata folder or so)
    private static final String NATIVES_EXPORT_DIR = "./natives";

    private static final String NATIVES = "natives/";
    private static final String WINDOWS = NATIVES + "windows/";
    private static final String LINUX = NATIVES + "linux/";
    private static final String MAC_OSX = NATIVES + "mac/";

    public static void intializeLwjgl() {
        identifyPlatform();
        addLibPath(new File(NATIVES_EXPORT_DIR).toPath());
    }

    private static void identifyPlatform() {
        switch (LWJGLUtil.getPlatform()) {
            case LWJGLUtil.PLATFORM_WINDOWS: {
                if (is64Bit()) {
                    unpack(WINDOWS + "OpenAL64.dll");
                    unpack(WINDOWS + "lwjgl64.dll");
                    unpack(WINDOWS + "jinput-dx8_64.dll");
                    unpack(WINDOWS + "jinput-raw_64.dll");
                } else {
                    unpack(WINDOWS + "OpenAL32.dll");
                    unpack(WINDOWS + "lwjgl.dll");
                    unpack(WINDOWS + "jinput-dx8.dll");
                    unpack(WINDOWS + "jinput-raw.dll");
                }
                unpack(WINDOWS + "jinput-wintab.dll");
                break;
            }
            case LWJGLUtil.PLATFORM_LINUX: {
                if (is64Bit()) {
                    unpack(LINUX + "libopenal64.so");
                    unpack(LINUX + "liblwjgl64.so");
                    unpack(LINUX + "libjinput-linux64.so");
                } else {
                    unpack(LINUX + "libopenal32.so");
                    unpack(LINUX + "liblwjgl32.so");
                    unpack(LINUX + "libjinput-linux.so");
                }
                break;
            }
            case LWJGLUtil.PLATFORM_MACOSX: {
                unpack(MAC_OSX + "liblwjgl.jnilib");
                unpack(MAC_OSX + "libopenal.dylib");
                unpack(MAC_OSX + "libjinput-osx.jnilib");
                break;
            }
            default:
                throw new RuntimeException("Failed to identify the platform!");
        }
    }

    private static boolean is64Bit() {
        String osArch = System.getProperty("os.arch");
        return "amd64".equals(osArch) || "x86_64".equals(osArch);
    }

    private static void unpack(String resource) {
        try {
            File exportDir = new File(NATIVES_EXPORT_DIR);
            exportDir.mkdirs();
            exportDir.deleteOnExit();
            File libFile = new File(NATIVES_EXPORT_DIR, resource.substring(resource.lastIndexOf('/'), resource.length()));

            if (!libFile.exists()) {
                libFile.deleteOnExit();

                InputStream inputStream = ClassLoader.getSystemResourceAsStream(resource);
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(libFile));

                int len;
                byte[] buffer = new byte[8192];

                while ((len = inputStream.read(buffer)) > -1) {
                    outputStream.write(buffer, 0, len);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();
            }
        } catch (Exception e) {
            System.err.println("Failed to copy the natives to the right directory");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void addLibPath(Path path) {
        String envProp = System.getProperty("java.library.path");

        if (envProp == null || envProp.isEmpty()) {
            System.setProperty("java.library.path", path.toAbsolutePath().toString());
        } else {
            System.setProperty("java.library.path", path.toAbsolutePath().toString() + File.separator + envProp);
        }

        try {
            Field usrPaths = ClassLoader.class.getDeclaredField("usr_paths");
            usrPaths.setAccessible(true);

            List<String> paths = Lists.newArrayList((String[]) usrPaths.get(null));

            if (paths.contains(path.toAbsolutePath().toString()))
                return;

            paths.add(path.toAbsolutePath().toString());

            usrPaths.set(null, paths.toArray(new String[paths.size()]));
        } catch (Exception e) {
            System.err.println("Failed to set the native libraries");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
