package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

public class WeatherReportServiceTest {

    private static final String API_KEY = "83038a5822f1268c45244ad8bfce58f8";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private WeatherReportService weatherReportService;

    @Mock
    private RestTemplate restTemplateMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        weatherReportService = new WeatherReportService();
    }

    @Test
    void testGetWeatherReport() {
        // Arrange
        double latitude = 37.8267;
        double longitude = -122.4233;
        WeatherApiResponse response = new WeatherApiResponse();
        response.setMain(new WeatherApiResponse.Main());
        Mockito.when(restTemplateMock.getForObject(API_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY, WeatherApiResponse.class)).thenReturn(response);

        // Act
        WeatherReport report = weatherReportService.getWeatherReport(latitude, longitude);

        // Assert
        Assertions.assertEquals(report.getTemperature(), response.getMain().getTemperature());
    }
}