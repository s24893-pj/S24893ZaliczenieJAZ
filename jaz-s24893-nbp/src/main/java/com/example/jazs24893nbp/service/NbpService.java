package com.example.jazs24893nbp.service;

import com.example.jazs24893nbp.model.CurrencyDataModel;
import com.example.jazs24893nbp.model.NbpResponse;
import com.example.jazs24893nbp.repository.NbpRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NbpService {

    private final RestTemplate restTemplate;
    private final NbpRepository nbpRepository;
    private static final String nbpUrl = "http://api.nbp.pl/api/exchangerates/rates";

    public NbpService(RestTemplate restTemplate, NbpRepository nbpRepository) {
        this.restTemplate = restTemplate;
        this.nbpRepository = nbpRepository;
    }

    public NbpResponse getCurrencyRates(String table, String code, LocalDate startDate, LocalDate endDate) {
        String url = String.format("%s/%s/%s/%s/%s", nbpUrl, table, code, startDate, endDate);
        return restTemplate.getForObject(url, NbpResponse.class);
    }
    public double getAverageRate(List<NbpResponse.Rate> rates) {
        return rates.stream().mapToDouble(NbpResponse.Rate::getMid).average()
                .orElseThrow(() -> new RuntimeException(""));
    }

    public CurrencyDataModel saveData(String currency, LocalDate startDate, LocalDate endDate, double averageRate) {
        CurrencyDataModel data = new CurrencyDataModel();
        data.setCurrency(currency);
        data.setStartDate(startDate);
        data.setEndDate(endDate);
        data.setAverageRate(averageRate);
        data.setQueryTime(LocalDateTime.now());
        nbpRepository.save(data);
        return data;
    }
}
