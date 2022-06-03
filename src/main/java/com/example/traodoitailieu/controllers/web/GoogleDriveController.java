package com.example.traodoitailieu.controllers.web;

import com.example.traodoitailieu.config.GoogleDriveConfig;
import com.example.traodoitailieu.entities.Category;
import com.example.traodoitailieu.entities.Document;
import com.example.traodoitailieu.entities.UserDocument;
import com.example.traodoitailieu.models.GoogleDriveFileDTO;
import com.example.traodoitailieu.models.GoogleDriveFoldersDTO;
import com.example.traodoitailieu.services.impl.*;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GoogleDriveController {

    @Autowired
    private GoogleDriveConfig googleDriveConfig;

    @Autowired
    DocumentService documentService;

    @Autowired
    GoogleDriveFileService googleDriveFileService;

    @Autowired
    GoogleDriveFolderService googleDriveFolderService;

    @Autowired
    UserDocumentService userDocumentService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/listFileDriver")
    public String pageIndex(Model model) throws IOException, GeneralSecurityException {
        Drive drive = googleDriveConfig.getInstance();
        return "web/document/driver/driver-common";
    }

    @GetMapping("/myDriver")
    public String pageIndex1(Model model) throws IOException, GeneralSecurityException {
        List<GoogleDriveFileDTO> listFile = googleDriveFileService.getAllFile123("root");
        List<GoogleDriveFoldersDTO> listFolder = googleDriveFolderService.getAllFolderLink("root");
        model.addAttribute("DTO_FILE", listFile);
        model.addAttribute("DTO_FOLDER", listFolder);
        return "web/document/driver/my-driver";
    }

    @PostMapping("/list")
    public String list(@RequestParam("id") String parentId, Model model) throws IOException, GeneralSecurityException {
        List<GoogleDriveFoldersDTO> listFolder = googleDriveFolderService.getAllFolderLink(parentId);
        List<GoogleDriveFileDTO> listFile = googleDriveFileService.getAllFile123(parentId);
        model.addAttribute("DTO_FOLDER", listFolder);
        model.addAttribute("DTO_FILE", listFile);
        return "web/document/driver/my-driver";
    }

    @GetMapping("/uploadFileDriver")
    public String UploadDriver(){
        return "web/document/driver/upload-file-driver";
    }

    @PostMapping(value = "/upload/file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE} )
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload,  @RequestParam("shared") String shared) {
        googleDriveFileService.uploadFile(fileUpload, "", Boolean.parseBoolean(shared));
        return "redirect:/myDriver";
    }

    @GetMapping("/uploadFileGDriver")
    public String uploadFileGDriver(@RequestParam("id") String id, HttpServletRequest request, Model model) throws Exception {
        model.addAttribute("file_id", id);
        String fileName = "";
        List<GoogleDriveFileDTO> listFile = googleDriveFileService.getAllFile();
        GoogleDriveFileDTO dto = new GoogleDriveFileDTO();
        for (GoogleDriveFileDTO file: listFile) {
            if(file.getId().equals(id)){
                fileName += file.getName();
                break;
            }
        }
        model.addAttribute("fileName", fileName);
        model.addAttribute("obj", new Document());
        int checkCookie = 0;
        ArrayList<Category> list = categoryService.getAll();
        model.addAttribute("list_category", list);
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/document/upl-gdri";
        }else{
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if(cookie.getName().equals(cookieName)){
                    check = true;
                    break;
                }
            }
            if(check == true){
                checkCookie = 1;
                model.addAttribute("checkCookie", checkCookie);
                return "web/document/upl-gdri";
            }
        }
        return "web/document/upl-gdri";
    }

    @PostMapping("/uploadFileKho")
    public String uploadFileKho(@RequestParam("id") String id, Document document, HttpServletRequest request, HttpSession httpSession) throws Exception {
        String as = (String)httpSession.getAttribute("category_id");
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        int user_id = 0;
        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(cookieName)) {
                    user_id = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }
        List<GoogleDriveFileDTO> listFile = googleDriveFileService.getAllFile();
        GoogleDriveFileDTO dto = new GoogleDriveFileDTO();
        for (GoogleDriveFileDTO file: listFile) {
            if(file.getId().equals(id)){
                documentService.saveFileLink(file, document.getDocument_name(), document.getDescription());
                ArrayList<Document> k = documentService.getFiles();
                UserDocument a = new UserDocument();
                a.setUser_id(user_id);
                a.setDocument_id(k.get(k.size()-1).getDocument_id());
                a.setCategory_id(Integer.parseInt(as));
                userDocumentService.saveUserDocument(a);
                break;
            }
        }
        return "redirect:/listDocument/category?id=" + as;
    }

    @GetMapping("/delete/file/{id}")
    public String deleteFile(@PathVariable String id) throws Exception {
        googleDriveFileService.deleteFile(id);
        return "redirect:/listFileOnDV";
    }

    @GetMapping("/download/file/{id}")
    public void downloadFile(@PathVariable String id, HttpServletResponse response) throws IOException, GeneralSecurityException {
        googleDriveFileService.downloadFile(id, response.getOutputStream());
    }
}
