package com.example.traodoitailieu.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="document")
public class Document {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int document_id;

    @Column(name = "document_name")
    String document_name;

    @Column(name = "description")
    String description;

    @Column(name = "document_type")
    String document_type;

    @Lob
    @Column(name = "data")
    byte[] data;

    @Column(name = "link")
    String link;

    private String docName;

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}
