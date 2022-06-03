package com.example.traodoitailieu.services.impl;

import com.example.traodoitailieu.models.GoogleDriveFileDTO;
import com.example.traodoitailieu.services.IGoogleDriveFile;
import com.example.traodoitailieu.utils.ConvertByteToMB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.drive.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleDriveFileService implements IGoogleDriveFile {

    private static final long serialVersionUID = 5162710183389028792L;

    @Autowired
    GoogleFileManager googleFileManager;

    @Override
    public List<GoogleDriveFileDTO> getAllFile() throws IOException, GeneralSecurityException {
        List<GoogleDriveFileDTO> responseList = null;
        List<File> files = googleFileManager.listEverything();
        GoogleDriveFileDTO dto = null;
        if (files != null) {
            responseList = new ArrayList<>();
            for (File f : files) {
                dto = new GoogleDriveFileDTO();
                if (f.getSize() != null) {
                    dto.setId(f.getId());
                    dto.setName(f.getName());
                    dto.setThumbnailLink(f.getThumbnailLink());
                    //dto.setSize(ConvertByteToMB.getSize(f.getSize()));
                    dto.setLink("https://drive.google.com/file/d/" + f.getId() + "/view?usp=sharing");
                    dto.setShared(f.getShared());
                    responseList.add(dto);
                }
            }
        }
        return responseList;
    }

    @Override
    public void deleteFile(String id) throws Exception {
        googleFileManager.deleteFile(id);
    }

    @Override
    public void uploadFile(MultipartFile file, String filePath, boolean isPublic) {
        String type = "";
        String role = "";
        if (isPublic) {
            type = "anyone";
            role = "reader";
        } else {
            type = "private";
            role = "private";
        }
        googleFileManager.uploadFile(file, filePath, type, role);
    }

    @Override
    public void downloadFile(String id, OutputStream outputStream) throws IOException, GeneralSecurityException {
        googleFileManager.downloadFile(id, outputStream);
    }

    public List<GoogleDriveFileDTO> getAllFile123(String parentId) throws IOException, GeneralSecurityException {
        List<GoogleDriveFileDTO> responseList = null;
        List<File> files = googleFileManager.listEverything123(parentId);
        GoogleDriveFileDTO dto = null;
        if (files != null) {
            responseList = new ArrayList<>();
            for (File f : files) {
                dto = new GoogleDriveFileDTO();
                if (f.getSize() != null) {
                    dto.setId(f.getId());
                    dto.setName(f.getName());
                    dto.setThumbnailLink(f.getThumbnailLink());
                    dto.setSize(ConvertByteToMB.getSize(f.getSize()));
                    dto.setLink("https://drive.google.com/file/d/" + f.getId() + "/view?usp=sharing");
                    dto.setShared(f.getShared());
                    responseList.add(dto);
                }
            }
        }
        return responseList;
    }
}
