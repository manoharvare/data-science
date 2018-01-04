package com.softcell.datascience;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softcell.datascience.model.query.*;
import com.softcell.datascience.model.request.client.Bucket;
import com.softcell.datascience.model.request.client.Aggregation;
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
        List<Aggregation> aggregation = (List<Aggregation>) objectMapper.readValue(chaidJson, new TypeReference<List<Aggregation>>() {
        });
        ElasticQuery elasticQuery = buildQueryObject(aggregation);
        System.out.println(objectMapper.writeValueAsString(elasticQuery));
    }

    private ElasticQuery buildQueryObject(List<Aggregation> requestList) {
        Collections.sort(requestList, Comparator.comparingInt(Aggregation::getLevel));
        ElasticQuery elasticQuery = new ElasticQuery();
        elasticQuery.setSize(0);
        int index = 0;
        for (Aggregation aggregation : requestList) {
            System.out.println(aggregation);
            if (index == 0) {
                elasticQuery.setAggregation(getAggregationObject());
                elasticQuery.getAggregation().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 1) {
                elasticQuery.getAggregation().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 2) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 3) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 4) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 5) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 6) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 7) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 8) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 9) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 10) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 11) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            }
            index++;
        }
        return elasticQuery;
    }

    private com.softcell.datascience.model.query.Aggregation getAggregationObject() {
        return new com.softcell.datascience.model.query.Aggregation();
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

    private PlaceHolder getPlaceHolder(Aggregation aggregation) {
        PlaceHolder placeHolder;
        if (aggregation.getFieldType().equals("numeric")) {
            placeHolder = PlaceHolder.builder()
                    .range(RangeBucket.builder()
                            .field(aggregation.getFieldName())
                            .keyed(true)
                            .ranges(getRanges(aggregation.getBuckets()))
                            .build())
                    .build();
        } else {
            placeHolder = PlaceHolder.builder()
                    .term(Term.builder()
                            .field(StringUtils.join(aggregation.getFieldName(), ".keyword"))
                            .size(null != aggregation.getSize() && aggregation.getSize() > 0 ? aggregation.getSize() : 1000)
                            .build())
                    .build();
        }
        return placeHolder;
    }

    @Test
    public void toJson() throws IOException {
        String json = "{\"size\":0,\"aggs\":{\"placeHolder\":{\"range\":{\"field\":\"age\",\"keyed\":true,\"ranges\":[{\"key\":\"20-40\",\"from\":20,\"to\":40},{\"key\":\"40-60\",\"from\":40,\"to\":60},{\"key\":\"60-80\",\"from\":60,\"to\":80}]},\"aggs\":{\"placeHolder\":{\"range\":{\"field\":\"cibilScore\",\"keyed\":true,\"ranges\":[{\"key\":\"500-600\",\"from\":500,\"to\":600},{\"key\":\"600-700\",\"from\":600,\"to\":700},{\"key\":\"700-800\",\"from\":700,\"to\":800}]},\"aggs\":{\"placeHolder\":{\"terms\":{\"field\":\"city.keyword\",\"size\":1000},\"aggs\":{\"placeHolder\":{\"terms\":{\"field\":\"gender.keyword\",\"size\":1000}}}}}}}}}}";
        ElasticQuery elasticQuery = objectMapper.readValue(json, ElasticQuery.class);
        System.out.println(elasticQuery);
    }
    

}
