package com.example.traodoitailieu.services.impl;

import com.example.traodoitailieu.config.GoogleDriveConfig;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import com.google.api.services.drive.model.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.List;

@Component
public class GoogleFileManager {

    @Autowired
    private GoogleDriveConfig googleDriveConfig;

    public List<File> listEverything() throws IOException, GeneralSecurityException {
        FileList result = googleDriveConfig.getInstance1().files().list()
                .setPageSize(1000)
                .setFields("nextPageToken, files(id, name, size, thumbnailLink, shared)")
                .execute();
        return result.getFiles();
    }

    public void downloadFile(String id, OutputStream outputStream) throws IOException, GeneralSecurityException {
        if (id != null) {
            googleDriveConfig.getInstance1().files()
                    .get(id).executeMediaAndDownloadTo(outputStream);
        }
    }

    public void deleteFile(String fileId) throws Exception {
        googleDriveConfig.getInstance1().files().delete(fileId).execute();
    }

    public File findFile(String fileId) throws Exception {
        return googleDriveConfig.getInstance().files().get(fileId).execute();
    }

    private Permission setPermission(String type, String role){
        Permission permission = new Permission();
        permission.setType(type);
        permission.setRole(role);
        return permission;
    }

    public String uploadFile(MultipartFile file, String folderName, String type, String role) {
        try {
            if (null != file) {
                File fileMetadata = new File();
                fileMetadata.setName(file.getOriginalFilename());
                com.google.api.services.drive.model.File uploadFile = googleDriveConfig.getInstance1().files()
                        .create(fileMetadata, new InputStreamContent(
                                file.getContentType(),
                                new ByteArrayInputStream(file.getBytes()))
                        )
                        .setFields("id").execute();
                if (!type.equals("private") && !role.equals("private")){
                    googleDriveConfig.getInstance1().permissions().create(uploadFile.getId(), setPermission(type, role)).execute();
                }
                return uploadFile.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<File> listFolderContent(String parentId) throws IOException, GeneralSecurityException {
        if (parentId == null) {
            parentId = "root";
        }
        String query = "'" + parentId + "' in parents";
        FileList result = googleDriveConfig.getInstance().files().list()
                .setQ(query)
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute();
        return result.getFiles();
    }

    public List<File> listFolderContent1(String parentId) throws IOException, GeneralSecurityException {
        if (parentId == null) {
            parentId = "root";
        }
        String query = "'" + parentId + "' in parents";
        FileList result = googleDriveConfig.getInstance1().files().list().setQ(query)
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute();
        return result.getFiles();
    }

    public List<File> listEverything123(String parentId) throws IOException, GeneralSecurityException {
        if (parentId == null) {
            parentId = "root";
        }
        String query = "'" + parentId + "' in parents";
        FileList result = googleDriveConfig.getInstance1().files().list().setQ(query)
                .setPageSize(1000)
                .setFields("nextPageToken, files(id, name, size, thumbnailLink, shared)")
                .execute();
        return result.getFiles();
    }
}
