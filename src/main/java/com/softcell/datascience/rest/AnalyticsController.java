package com.softcell.datascience.rest;

import com.softcell.datascience.model.request.client.Aggregation;
import com.softcell.datascience.model.request.client.ClientRequest;
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
import java.util.List;

import static com.softcell.datascience.rest.constants.EndPointRefferer.*;

@RestController
@RequestMapping(
        value = ANALYTICS,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AnalyticsController {
    @Autowired
    private AnalyticsManager analyticsManager;

    @PostMapping(value = CHAID)
    public ResponseEntity<?> putBasicData(
            @RequestBody final Object query) throws IOException {
        return new ResponseEntity<>(analyticsManager.doChaidAnalysis(query), HttpStatus.OK);
    }

    @PostMapping(value = DYNAMIC_CHAID)
    public ResponseEntity<?> doAnalysis(
            @RequestBody final List<Aggregation> query) throws IOException {
        return new ResponseEntity<>(analyticsManager.doDynamicChaidAnalysis(query), HttpStatus.OK);
    }

    @PostMapping(value = DYNAMIC_CHAID_WITH_FILTER)
    public ResponseEntity<?> doAnalysisWithFilter(
            @RequestBody final ClientRequest clientRequest) throws IOException {
        return new ResponseEntity<>(analyticsManager.doDynamicChaidAnalysisWithFilter(clientRequest), HttpStatus.OK);
    }

}
