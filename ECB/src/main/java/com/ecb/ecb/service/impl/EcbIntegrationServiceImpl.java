package com.ecb.ecb.service.impl;

import com.ecb.ecb.dto.ecb.Cube;
import com.ecb.ecb.dto.ecb.ParentCube;
import com.ecb.ecb.model.Rate;
import com.ecb.ecb.repository.RateRepository;
import com.ecb.ecb.repository.specification.RateSpecification;
import com.ecb.ecb.service.EcbIntegrationService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class EcbIntegrationServiceImpl implements EcbIntegrationService {

    private final static String ECB_URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

    @Autowired
    private RateRepository repository;

    public void syncData() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(ECB_URL, String.class);
        ParentCube parentCube = xmlToCube(result);
        ecbToDB(parentCube);
    }

    private void ecbToDB(ParentCube parentCube) {
        if (checkIfRateAllreadySync(parentCube)) {

            parentCube.getCubes().stream().forEach(el -> {
                Rate rateDB = null;
                try {
                    rateDB = new Rate(new SimpleDateFormat("yyyy-MM-dd").parse(parentCube.getDate()), el.getCurrency(), Double.parseDouble(el.getRate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                repository.saveAndFlush(rateDB);
            });
        }
    }

    private boolean checkIfRateAllreadySync(ParentCube parentCube) {
        try {
            return repository.findAll(RateSpecification.getByDate(new SimpleDateFormat("yyyy-MM-dd").parse(parentCube.getDate()))).isEmpty();
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private ParentCube xmlToCube(String result) {
        JSONObject xmlJSON = XML.toJSONObject(result);

        JSONObject cube = xmlJSON.getJSONObject("gesmes:Envelope").getJSONObject("Cube").getJSONObject("Cube");
        String time = cube.getString("time");
        JSONArray cubes = cube.getJSONArray("Cube");
        ParentCube parentCube = new ParentCube();
        parentCube.setCubes(jsonArrayToList(cubes));
        parentCube.setDate(time);
        return parentCube;
    }

    private List<Cube> jsonArrayToList(JSONArray jsonArray) {
        List<Cube> arr = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            String currency = jsonArray.getJSONObject(i).getString("currency");
            Double rate = jsonArray.getJSONObject(i).getDouble("rate");
            arr.add(generateCube(currency, rate));
        }
        return arr;
    }

    private Cube generateCube(String currency, Double rate) {
        Cube cube = new Cube();
        cube.setCurrency(currency);
        cube.setRate(rate.toString());
        return cube;
    }
}
