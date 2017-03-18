package com.test.controller;

import com.test.model.DocumentItem;
import com.test.service.DocumentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/document-item")
public class DocumentItemController {
    @Autowired
    DocumentItemService documentItemService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DocumentItem addDocumentItem(@RequestBody DocumentItem documentItem) {
        documentItemService.addDocumentItem(documentItem);
        return documentItem;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<DocumentItem> getDocumentList() {
        return documentItemService.getDocumentItemList();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DocumentItem updateDocumentItem(@RequestBody DocumentItem documentItem) {
        documentItemService.updateDocumentItem(documentItem);
        return documentItem;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public DocumentItem deleteDocumentItem(@PathVariable(value = "id") String inputId) {
        return documentItemService.deleteDocumentItem(Long.parseLong(inputId));
    }

    @RequestMapping(value = "/get/with/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<DocumentItem> getDocumentItemListByName(@PathVariable(value = "name") String name) {
        return documentItemService.getDocumentItemByName(name);
    }

    @RequestMapping(value = "/get/with/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DocumentItem getDocumentById(@PathVariable(value = "id") String id) {
        return documentItemService.getDocumentItemById(Long.parseLong(id));
    }

}
