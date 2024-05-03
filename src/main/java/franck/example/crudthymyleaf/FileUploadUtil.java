package franck.example.crudthymyleaf;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.*;
import java.io.IOException;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String FileName, MultipartFile multipartFile)throws IOException{
        Path uploadPath =Paths.get(uploadDir);
    }
}
