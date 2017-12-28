package com.softcell.datascience;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softcell.datascience.model.query.*;
import com.softcell.datascience.model.request.client.Bucket;
import com.softcell.datascience.model.request.client.ChaidAnalysisRequest;
import com.softcell.datascience.model.response.TreeView;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataScienceApplicationTests {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void contextLoads() {
    }

    @Test
    public void buildQueryObject() throws IOException {
        String chaidJson = "[{\"fieldName\":\"cibilScore\",\"fieldType\":\"numeric\",\"level\":2,\"buckets\":[{\"key\":\"500-600\",\"from\":500,\"to\":600},{\"key\":\"600-700\",\"from\":600,\"to\":700},{\"key\":\"700-800\",\"from\":700,\"to\":800}]},{\"fieldName\":\"age\",\"fieldType\":\"numeric\",\"level\":1,\"buckets\":[{\"key\":\"20-40\",\"from\":20,\"to\":40},{\"key\":\"40-60\",\"from\":40,\"to\":60},{\"key\":\"60-80\",\"from\":60,\"to\":80}]},{\"fieldName\":\"city\",\"fieldType\":\"string\",\"level\":3},{\"fieldName\":\"gender\",\"fieldType\":\"string\",\"level\":4}]";
        List<ChaidAnalysisRequest> chaidAnalysisRequest = (List<ChaidAnalysisRequest>) objectMapper.readValue(chaidJson, new TypeReference<List<ChaidAnalysisRequest>>() {
        });
        Query query = buildQueryObject(chaidAnalysisRequest);
        System.out.println(objectMapper.writeValueAsString(query));
    }

    private Query buildQueryObject(List<ChaidAnalysisRequest> requestList) {
        Collections.sort(requestList, Comparator.comparingInt(ChaidAnalysisRequest::getLevel));
        Query query = new Query();
        query.setSize(0);
        int index = 0;
        for (ChaidAnalysisRequest chaidAnalysisRequest : requestList) {
            System.out.println(chaidAnalysisRequest);
            if (index == 0) {
                query.setAggregation(getAggregationObject());
                query.getAggregation().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 1) {
                query.getAggregation().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 2) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 3) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 4) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 5) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 6) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 7) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 8) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 9) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 10) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 11) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            }
            index++;
        }
        return query;
    }

    private Aggregation getAggregationObject() {
        return new Aggregation();
    }

    private List<Range> getRanges(List<Bucket> buckets) {
        List<Range> ranges = new ArrayList<>();
        for (Bucket bucket : buckets) {
            ranges.add(Range.builder()
                    .from(bucket.getFrom())
                    .to(bucket.getTo())
                    .key(bucket.getKey())
                    .build());
        }
        return ranges;
    }

    private PlaceHolder getPlaceHolder(ChaidAnalysisRequest chaidAnalysisRequest) {
        PlaceHolder placeHolder;
        if (chaidAnalysisRequest.getFieldType().equals("numeric")) {
            placeHolder = PlaceHolder.builder()
                    .range(RangeBucket.builder()
                            .field(chaidAnalysisRequest.getFieldName())
                            .keyed(true)
                            .ranges(getRanges(chaidAnalysisRequest.getBuckets()))
                            .build())
                    .build();
        } else {
            placeHolder = PlaceHolder.builder()
                    .term(Term.builder()
                            .field(StringUtils.join(chaidAnalysisRequest.getFieldName(), ".keyword"))
                            .size(null != chaidAnalysisRequest.getSize() && chaidAnalysisRequest.getSize() > 0 ? chaidAnalysisRequest.getSize() : 1000)
                            .build())
                    .build();
        }
        return placeHolder;
    }

    @Test
    public void toJson() throws IOException {
        String json = "{\"size\":0,\"aggs\":{\"placeHolder\":{\"range\":{\"field\":\"age\",\"keyed\":true,\"ranges\":[{\"key\":\"20-40\",\"from\":20,\"to\":40},{\"key\":\"40-60\",\"from\":40,\"to\":60},{\"key\":\"60-80\",\"from\":60,\"to\":80}]},\"aggs\":{\"placeHolder\":{\"range\":{\"field\":\"cibilScore\",\"keyed\":true,\"ranges\":[{\"key\":\"500-600\",\"from\":500,\"to\":600},{\"key\":\"600-700\",\"from\":600,\"to\":700},{\"key\":\"700-800\",\"from\":700,\"to\":800}]},\"aggs\":{\"placeHolder\":{\"terms\":{\"field\":\"city.keyword\",\"size\":1000},\"aggs\":{\"placeHolder\":{\"terms\":{\"field\":\"gender.keyword\",\"size\":1000}}}}}}}}}}";
        Query query = objectMapper.readValue(json, Query.class);
        System.out.println(query);
    }

    @Test
    public void mapping() throws IOException {
        String json = "{\"name\":null,\"parent\":null,\"doc_count\":null,\"key\":null,\"placeHolder\":null}";
        ObjectMapper mapper = new ObjectMapper();
        TreeView treeView = mapper.readValue(json,TreeView.class);
        String dtoAsString = mapper.writeValueAsString(treeView);
        System.out.println(dtoAsString);
    }

}
