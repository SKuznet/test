package com.test.document;

import com.test.model.DocumentItem;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DocumentItemIntegrationTest {
    @Test
    public void addDocumentItem() {
        RestTemplate restTemplate = new RestTemplate();

        DocumentItem result = createDocumentItem();
        assertNotNull(result);

        DocumentItem resultDocument = restTemplate.getForEntity(
                "http://localhost:8080/document-item/get/with/id/{id}",
                DocumentItem.class,
                result.getId()
        ).getBody();

        assertNotNull(resultDocument);
        assertEquals(resultDocument.getName(), result.getName());
    }

    @Test
    public void updateDocumentItem() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();

        DocumentItem result = createDocumentItem();
        assertNotNull(result);

        result.setName("Might and magic");

        HttpEntity<DocumentItem> httpEntity = new HttpEntity<>(result, headers);
        DocumentItem resultUpdCompany = restTemplate.exchange(
                "http://localhost:8080/document-item/update",
                HttpMethod.PUT,
                httpEntity,
                DocumentItem.class)
                .getBody();

        assertNotNull(resultUpdCompany);
        assertNotNull(resultUpdCompany.getId());
        assertEquals("Might and magic", resultUpdCompany.getName());
    }

    @Test
    public void deleteDocumentItem() {
        RestTemplate restTemplate = new RestTemplate();
        DocumentItem documentItem = createDocumentItem();

        assertNotNull(documentItem);
        assertNotNull(documentItem.getId());

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8080/document-item/{id}",
                HttpMethod.DELETE,
                null,
                String.class,
                documentItem.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        ResponseEntity<DocumentItem> checkDocumentIsExist = restTemplate.exchange(
                "http://localhost:8080/document-item/get/with/id/{id}",
                HttpMethod.GET,
                null,
                DocumentItem.class,
                documentItem.getId()
        );
        assertNull(checkDocumentIsExist.getBody());
    }

    @Test
    public void getAllDocuments() {
        RestTemplate restTemplate = new RestTemplate();
        createDocumentItem();
        createDocumentItem();

        ResponseEntity<List<DocumentItem>> result = restTemplate.exchange(
                "http://localhost:8080/document-item/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DocumentItem>>() {
                }
        );
        assertEquals(HttpStatus.OK, result.getStatusCode());

        assertNotNull(result.getBody());
    }

    private DocumentItem createDocumentItem() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();
        DocumentItem documentItem = prefillDocumentItem();
        HttpEntity<DocumentItem> httpEntity = new HttpEntity<>(documentItem, headers);

        DocumentItem result = restTemplate.exchange(
                "http://localhost:8080/document-item/add/",
                HttpMethod.POST,
                httpEntity,
                DocumentItem.class).getBody();
        assertNotNull(result);
        assertNotNull(result.getId());
        return result;
    }

    private DocumentItem prefillDocumentItem() {
        DocumentItem documentItem = new DocumentItem();
        documentItem.setName("Sword and shield");
        documentItem.setPrice(new BigDecimal(Long.valueOf(10)));
        return documentItem;
    }

}
