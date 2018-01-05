package com.softcell.datascience.rest;

import com.softcell.datascience.model.request.client.ClientRequest;
import com.softcell.datascience.service.AnalyticsAdminManager;
import com.softcell.datascience.service.AnalyticsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.softcell.datascience.rest.constants.EndPointRefferer.*;

@RestController
@RequestMapping(
        value = ANALYTICS_ADMIN,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AnalyticsAdminController {

    @Autowired
    private AnalyticsAdminManager analyticsAdminManager;

    @PostMapping(value = SAVE_QUERY)
    public ResponseEntity<?> saveQuery(
            @RequestBody final ClientRequest query) throws IOException {
        if(analyticsAdminManager.saveQuery(query)){
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(false, HttpStatus.PRECONDITION_FAILED);
        }
    }

}
