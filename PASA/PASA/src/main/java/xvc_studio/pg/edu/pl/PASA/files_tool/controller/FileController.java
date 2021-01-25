package xvc_studio.pg.edu.pl.PASA.files_tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xvc_studio.pg.edu.pl.PASA.files_tool.storage.StorageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import xvc_studio.pg.edu.pl.PASA.file_uploader.storage.StorageService;


@RestController
@RequestMapping("/files")
public class FileController {

    private final StorageService storageService;

    @Autowired
    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public void downloadFile(@PathVariable String filename, HttpServletResponse response) {

       storageService.download(filename, response);
    }


    @GetMapping("{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> handleFileUpload(@RequestParam("icon") MultipartFile file, @RequestParam String filename) throws IOException {

        storageService.store(file, filename);

        return ResponseEntity.accepted().build();
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(ClassNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}