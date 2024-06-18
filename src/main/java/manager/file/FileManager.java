package manager.file;

import manager.file.enums.ContentType;

import java.net.URL;

public interface FileManager {
    URL uploadFile(ContentType contentTpe, String filename);

    URL getFile(String fileName);
}
