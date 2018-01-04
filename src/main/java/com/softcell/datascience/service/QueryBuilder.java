package com.softcell.datascience.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softcell.datascience.model.query.*;
import com.softcell.datascience.model.request.client.Bucket;
import com.softcell.datascience.model.request.client.Aggregation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QueryBuilder {

    public static <K, V> void add(LinkedHashMap<K, V> map, int index, K key, V value) {
        assert (map != null);
        assert !map.containsKey(key);
        assert (index >= 0) && (index < map.size());

        int i = 0;
        List<Map.Entry<K, V>> rest = new ArrayList<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (i++ >= index) {
                rest.add(entry);
            }
        }
        map.put(key, value);
        for (int j = 0; j < rest.size(); j++) {
            Map.Entry<K, V> entry = rest.get(j);
            map.remove(entry.getKey());
            map.put(entry.getKey(), entry.getValue());
        }
    }

    public ElasticQuery buildChaidQuery(List<Aggregation> requestList) {
        ElasticQuery elasticQuery = new ElasticQuery();
        elasticQuery.setSize(0);
        int index = 0;
        for (Aggregation aggregation : requestList) {
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
            } else if (index == 12) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 13) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 14) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 15) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 16) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 17) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 18) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            } else if (index == 19) {
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                elasticQuery.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(aggregation));
            }
            index++;
        }
        return elasticQuery;
    }

    public List<Aggregation> doSorting(List<Aggregation> requestList) {
        Collections.sort(requestList, Comparator.comparingInt(Aggregation::getLevel));
        return requestList;
    }

    private com.softcell.datascience.model.query.Aggregation getAggregationObject() {
        return new com.softcell.datascience.model.query.Aggregation();
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
                            .size(null != aggregation.getSize() && aggregation.getSize() > 0 ? aggregation.getSize() : 10)
                            .build())
                    .build();
        }
        return placeHolder;
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

    public Object getParseObject(String json, List<Aggregation> requestList) {
        Map top = new LinkedHashMap();
        Gson gson = new GsonBuilder().create();
        LinkedHashMap innerMap = gson.fromJson(json, LinkedHashMap.class);
        Map hits = (Map) innerMap.get("hits");
        Map o = (Map) innerMap.get("aggregations");
        Object object = o.get("children");
        System.out.println(gson.toJson(object));
        Object node = null;
        if (object != null) {
            Object buckets = ((Map) object).get("buckets");
            node = getNode(buckets, requestList, "Applications", -1);
            top.put("name", "Applications");
            top.put("doc_count", hits.get("total"));
            top.put("children", node);
        }
        return top;
    }

    public Object getNode(Object object, List<Aggregation> requestList, String parent, int i) {
        List<Map<String, Object>> listObject = new ArrayList<>();
        Map<String, Object> resultMap;
        if (object instanceof List) {
            List<Object> innerList = (List) object;
            i++;
            for (Object innerObject : innerList) {
                resultMap = new HashMap<>();
                Object temp = ((Map) innerObject).get("children");
                if (temp != null) {
                    String subParent = requestList.get(i).getFieldName();
                    Object bucket = ((Map) temp).get("buckets");
                    Object node = getNode(bucket, requestList, subParent, i);
                    if (node != null) {
                        resultMap.putAll(((Map) innerObject));
                        resultMap.put("name", subParent);
                        resultMap.put("parent", parent);
                        resultMap.put("children", node);
                        listObject.add(resultMap);
                    }
                } else {
                    resultMap.putAll((Map) innerObject);
                    resultMap.put("name", requestList.get(i).getFieldName());
                    resultMap.put("parent", parent);
                    listObject.add(resultMap);
                }
            }
        } else if (object instanceof Map) {
            Map<String, Object> map = (Map) object;
            Set<String> keySet = map.keySet();
            i++;
            for (String key : keySet) {
                resultMap = new HashMap<>();
                Map<String, Object> innerLoopMap = (Map) map.get(key);
                Object temp = innerLoopMap.get("children");
                if (temp != null) {
                    String subParent = requestList.get(i).getFieldName();
                    Object bucket = ((Map) temp).get("buckets");
                    Object node = getNode(bucket, requestList, subParent, i);
                    resultMap.put("children", node);
                    resultMap.put("name", subParent);
                    resultMap.put("parent", parent);
                    Object o = map.get(key);
                    removeObject(o, key, resultMap);
                    listObject.add(resultMap);
                } else {
                    resultMap.putAll(innerLoopMap);
                    resultMap.put("name", requestList.get(i).getFieldName());
                    resultMap.put("parent", parent);
                    Object o = map.get(key);
                    removeObject(o, key, resultMap);
                    listObject.add(resultMap);
                }
            }
        }
        return listObject.isEmpty() ? null : listObject;
    }

    private Map<String, Object> removeObject(Object o,String key, Map<String, Object>  resultMap){
        if (o instanceof Map) {
            Map map = (Map) o;
            map.put("key", key);
            map.remove("children");
            resultMap.putAll((Map) map);
        }
        return resultMap;
    }
}
