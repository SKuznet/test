package com.test.document;

import com.test.model.Document;
import com.test.model.DocumentItem;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DocumentIntegrationTest {
    @Test
    public void addDocument() {
        Document document = createDocument();
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Document> responseEntity = restTemplate.exchange(
                "http://localhost:8080/document/get/with/id/{id}",
                HttpMethod.GET,
                null,
                Document.class,
                document.getId()
        );
        Document resultDocument = responseEntity.getBody();
        Assert.assertNotNull(resultDocument);
        assertEquals(resultDocument.getName(), document.getName());
    }

    @Test
    public void updateCompany() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();
        Document document = createDocument();
        Assert.assertNotNull(document);

        document.setName("Might and magic");

        HttpEntity<Document> httpEntity = new HttpEntity<>(document, headers);
        Document resultUpdCompany = restTemplate.exchange
                ("http://localhost:8080/document/update",
                        HttpMethod.PUT,
                        httpEntity,
                        Document.class)
                .getBody();

        Assert.assertNotNull(resultUpdCompany);
        Assert.assertNotNull(resultUpdCompany.getId());
        assertEquals("Might and magic", resultUpdCompany.getName());
    }

    @Test
    public void deleteDocument() {
        Document document = createDocument();
        Assert.assertNotNull(document);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8080/document/{id}",
                HttpMethod.DELETE,
                null,
                String.class,
                document.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        ResponseEntity<Document> checkDocumentIsExist = restTemplate.exchange(
                "http://localhost:8080/document/get/with/id/{id}",
                HttpMethod.GET,
                null,
                Document.class,
                document.getId()
        );
        Assert.assertNull(checkDocumentIsExist.getBody());
    }

    @Test
    public void getAllDocuments() {
        RestTemplate restTemplate = new RestTemplate();
        createDocument();
        createDocument();

        ResponseEntity<List<Document>> result = restTemplate.exchange(
                "http://localhost:8080/document/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Document>>() {
                }
        );
        assertEquals(HttpStatus.OK, result.getStatusCode());

        Assert.assertNotNull(result.getBody());
    }

    private Document prefillDocument() {
        Document document = new Document();
        document.setName("Sword and shield");
        document.setCode(2);
        Calendar cal = Calendar.getInstance();
        document.setDate(cal.getTime());

        DocumentItem documentItem = new DocumentItem();
        documentItem.setName("sword");
        documentItem.setPrice(new BigDecimal(BigInteger.valueOf(10)));
        DocumentItem documentItem2 = new DocumentItem();
        documentItem2.setName("shield");
        documentItem2.setPrice(new BigDecimal(BigInteger.valueOf(5)));

        List<DocumentItem> documentItemList = new ArrayList<>();
        documentItemList.add(documentItem);
        documentItemList.add(documentItem2);

        document.setDocumentItems(documentItemList);
        return document;
    }

    private Document createDocument() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Document document = prefillDocument();

        HttpEntity<Document> httpEntity = new HttpEntity<>(document, headers);
        RestTemplate restTemplate = new RestTemplate();
        Document result = restTemplate.exchange(
                "http://localhost:8080/document/add/",
                HttpMethod.POST,
                httpEntity,
                Document.class).getBody();

        Assert.assertNotNull(result);
        assertEquals("Sword and shield", result.getName());
        Assert.assertNotNull(result.getId());
        return result;
    }

}
