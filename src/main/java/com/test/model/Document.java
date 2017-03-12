package com.test.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "document_id")
    private long id;
    private int code;
    private Date date;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DocumentItem> documentItems = new ArrayList<>();

    public List<DocumentItem> getDocumentItems() {
        return documentItems;
    }

    public void setDocumentItems(List<DocumentItem> documentItems) {
        this.documentItems = documentItems;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
