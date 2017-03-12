package com.test.controller;

import com.test.model.Document;
import com.test.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    DocumentService documentService;

    @RequestMapping(value = "/add/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Document addDocument(@RequestBody Document document) {
        documentService.addDocument(document);
        return document;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Document> getDocumentList() {
        return documentService.getDocumentList();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Document updateDocument(@RequestBody Document document) {
        documentService.updateDocument(document);
        return document;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Document deleteDocument(@PathVariable(value = "id") String inputId) {
        return documentService.deleteDocument(Long.parseLong(inputId));
    }

    @RequestMapping(value = "/get/with/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Document> getDocumentListByName(@PathVariable(value = "name") String name) {
        return documentService.getDocumentByName(name);
    }

    @RequestMapping(value = "/get/with/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Document getDocumentById(@PathVariable(value = "id") String id) {
        return documentService.getDocumentById(Long.parseLong(id));
    }

}
