package com.spring.boot.back.challenge;

import com.spring.boot.back.challenge.entities.HistoryEntity;
import com.spring.boot.back.challenge.model.CalculationRequest;
import com.spring.boot.back.challenge.model.CalculationResponse;
import com.spring.boot.back.challenge.repositories.HistoryRepository;
import com.spring.boot.back.challenge.services.CalculationService;
import com.spring.boot.back.challenge.services.HistoryService;
import com.spring.boot.back.challenge.services.PercentageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

    @Mock
    private PercentageService percentageService;

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private HistoryService historyService;

    @InjectMocks
    private CalculationService calculationService;

    public CalculationServiceTest() {
        MockitoAnnotations.openMocks(this); // Esto inicializa los mocks
    }

    @Test
    void shouldThrowJDBCConnectionException() {
        when(historyRepository.findAll((Example<HistoryEntity>) ArgumentMatchers.any())).thenThrow(new DataAccessResourceFailureException("Fallo de conexión"));

        assertThrows(DataAccessResourceFailureException.class, () -> {
            calculationService.getAllHistory(org.springframework.data.domain.PageRequest.of(0, 5));
        });
    }


    @Test
    void testCalculateSumWithPercentage_success() {
        CalculationRequest request = new CalculationRequest(10.0, 20.0);
        when(percentageService.getPercentage()).thenReturn(10.0);

        CalculationResponse response = calculationService.calculateSumWithPercentage(request);

        assertEquals(33.0, response.getResult(), 0.01);
        Mockito.verify(historyService, Mockito.times(1))
                .saveHistoryAsync(Mockito.eq("/api/calculate"), Mockito.anyString(), Mockito.anyString(), Mockito.isNull());
    }

}
