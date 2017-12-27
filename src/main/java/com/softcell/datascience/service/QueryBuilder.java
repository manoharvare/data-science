package com.softcell.datascience.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softcell.datascience.model.query.*;
import com.softcell.datascience.model.request.client.Bucket;
import com.softcell.datascience.model.request.client.ChaidAnalysisRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QueryBuilder {
    int i = 0;
    int j = 0;

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

    public Query buildChaidQuery(List<ChaidAnalysisRequest> requestList) {
        Query query = new Query();
        query.setSize(0);
        int index = 0;
        for (ChaidAnalysisRequest chaidAnalysisRequest : requestList) {
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
            } else if (index == 12) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 13) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 14) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 15) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 16) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 17) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 18) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            } else if (index == 19) {
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().setAggregations(getAggregationObject());
                query.getAggregation().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().getPlaceHolder().getAggregations().setPlaceHolder(getPlaceHolder(chaidAnalysisRequest));
            }
            index++;
        }
        return query;
    }

    public List<ChaidAnalysisRequest> doSorting(List<ChaidAnalysisRequest> requestList) {
        Collections.sort(requestList, Comparator.comparingInt(ChaidAnalysisRequest::getLevel));
        return requestList;
    }

    private Aggregation getAggregationObject() {
        return new Aggregation();
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

    public Object getParseObject(String json, List<ChaidAnalysisRequest> requestList) {
        Map top = new LinkedHashMap();
        Gson gson = new GsonBuilder().create();
        LinkedHashMap innerMap = gson.fromJson(json, LinkedHashMap.class);
        Map hits = (Map) innerMap.get("hits");
        Map o = (Map) innerMap.get("aggregations");
        Object object = o.get("placeHolder");
        System.out.println(gson.toJson(object));
        Object node = null;
        if (object != null) {
            Object buckets = ((Map) object).get("buckets");
            node = getNode(buckets, requestList);
           /* top.put("name", "totalApplication");
            top.put("doc_count", hits.get("total"));
            top.put("parent", node);*/
        }
        if (node != null) {
            System.out.println(gson.toJson(node));
        }
        return node;
    }

    public Object getNode(Object object, List<ChaidAnalysisRequest> requestList) {
        List<Map<String, Object>> listObject = new ArrayList<>();
        Map<String, Object> resultMap;
        if (object instanceof List) {
            List<Object> innerList = (List) object;
            for (Object innerObject : innerList) {
                resultMap = new HashMap<>();
                if (innerObject instanceof Map) {
                    Map tempMap = (Map) innerObject;
                    if (i == requestList.size()) {
                        tempMap.put("name", requestList.get(j).getFieldName());
                        int tempCount = j;
                        tempCount--;
                        tempMap.put("parent", requestList.get(tempCount).getFieldName());

                    } else {
                        tempMap.put("name", requestList.get(i).getFieldName());
                    }

                    if (i == 0) {
                        tempMap.put("parent", null);
                    } else if (i < requestList.size()) {
                        int tempCount = i;
                        tempCount--;
                        tempMap.put("parent", requestList.get(tempCount).getFieldName());
                    }
                    innerObject = tempMap;
                    if (i < requestList.size()) {
                        i++;
                    }

                }
                Object temp = ((Map) innerObject).get("placeHolder");
                if (temp != null) {
                    Object bucket = ((Map) temp).get("buckets");
                    Object node = getNode(bucket, requestList);
                    if (node != null) {
                        resultMap.putAll(((Map) innerObject));
                        resultMap.put("placeHolder", node);
                        listObject.add(resultMap);
                    }
                } else {
                    resultMap.putAll((Map) innerObject);
                    listObject.add(resultMap);
                    if (i == requestList.size()) {
                        j = i;
                    }
                    j--;
                }
            }
        } else if (object instanceof Map) {
            Map<String, Object> map = (Map) object;
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                resultMap = new HashMap<>();
                Map<String, Object> innerLoopMap = (Map) map.get(key);
                //
                if (i == requestList.size()) {
                    innerLoopMap.put("name", requestList.get(j).getFieldName());
                    int tempCount = j;
                    tempCount--;
                    innerLoopMap.put("parent", requestList.get(tempCount).getFieldName());

                } else {
                    innerLoopMap.put("name", requestList.get(i).getFieldName());
                }

                if (i == 0) {
                    innerLoopMap.put("parent", null);
                } else if (i < requestList.size()) {
                    int tempCount = i;
                    tempCount--;
                    innerLoopMap.put("parent", requestList.get(tempCount).getFieldName());
                }
                if (i < requestList.size()) {
                    i++;
                }


                //
                Object temp = innerLoopMap.get("placeHolder");
                if (temp != null) {
                    Object bucket = ((Map) temp).get("buckets");
                    Object node = getNode(bucket, requestList);
                    resultMap.put("placeHolder", node);
                    Object o = map.get(key);
                    if (o instanceof Map) {
                        Map o1 = (Map) o;
                        o1.put("key", key);
                        o1.remove("placeHolder");
                        resultMap.putAll((Map) o1);
                    }
                    listObject.add(resultMap);
                } else {
                    resultMap.putAll(innerLoopMap);
                    listObject.add(resultMap);
                    if (i == requestList.size()) {
                        j = i;
                    }
                    j--;
                }
            }
        }
        return listObject.isEmpty() ? null : listObject;
    }

    public Object placeElementsAtSpecifiedLocation(List<ChaidAnalysisRequest> query, Object json) {
        Gson gson = new GsonBuilder().create();
        ArrayList arrayList = (ArrayList) json;
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (Object arrayList1 : arrayList) {
            System.out.println(arrayList1);
            map.put("placeHolder", arrayList1);
        }

        for (ChaidAnalysisRequest requests : query) {
            add(map, requests.getLevel(), "name", requests.getFieldName());
        }
        return map;
    }
}
