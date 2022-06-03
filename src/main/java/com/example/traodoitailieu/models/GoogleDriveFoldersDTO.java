package com.example.traodoitailieu.models;

import groovy.transform.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class GoogleDriveFoldersDTO implements Serializable {
    private String id;
    private String name;
    private String link;
}
