package com.example.jazs24893nbp.controller;


import com.example.jazs24893nbp.model.CurrencyDataModel;
import com.example.jazs24893nbp.model.NbpResponse;
import com.example.jazs24893nbp.service.NbpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/nbpKolokwium")
public class NbpController {

    private final NbpService nbpService;


    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }

    @Operation(summary = "Kurs Walut")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })

    @GetMapping("/rates/{currency}/{startDate}/{endDate}")
    public ResponseEntity<CurrencyDataModel> getCurrencyRate(@PathVariable("currency") String currency, @PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
        NbpResponse nbpResponse = nbpService.getCurrencyRates("a", currency, startDate, endDate);
        double averageRate = nbpService.getAverageRate(nbpResponse.getRates());
        CurrencyDataModel data = nbpService.saveData(currency, startDate, endDate, averageRate);
        return ResponseEntity.ok(data);
    }

}
