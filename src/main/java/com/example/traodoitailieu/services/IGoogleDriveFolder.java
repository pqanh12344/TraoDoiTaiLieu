package com.example.traodoitailieu.services;

import com.example.traodoitailieu.models.GoogleDriveFoldersDTO;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface IGoogleDriveFolder {
    List<GoogleDriveFoldersDTO> getAllFolder() throws IOException, GeneralSecurityException;
}
