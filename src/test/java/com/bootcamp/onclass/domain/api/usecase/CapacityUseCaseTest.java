package com.bootcamp.onclass.domain.api.usecase;

import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.data.CapacityData;
import com.bootcamp.onclass.data.ParametersData;
import com.bootcamp.onclass.domain.exception.DuplicateItemsListException;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.model.Technology;
import com.bootcamp.onclass.domain.spi.ICapacityPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CapacityUseCaseTest {

    @Mock
    private ICapacityPersistencePort capacityPersistencePort;
    @InjectMocks
    private CapacityUseCase capacityUseCase;

    private CapacityData capacityData = new CapacityData();

    @Test
    @DisplayName("Test successful adding  of a capacity")
    void shouldAddCapacity() {

        //GIVEN

        Capacity capacity = capacityData.createCapacity();

        //WHEN

        when(capacityPersistencePort.addCapacity(capacity)).thenReturn(capacity);
       capacityUseCase.addCapacity(capacity);

        //THEN

        verify(capacityPersistencePort).addCapacity(capacity);
    }
    @Test
    @DisplayName("Test exception by duplicate technologies")
    void shouldNotAddCapacityByDuplicateTechnologies(){

        //GIVEN

        Capacity capacity = capacityData.createCapacityDuplicateTechnologies();

        // WHEN

        Throwable exception = assertThrows(DuplicateItemsListException.class, () -> {
            capacityUseCase.addCapacity(capacity);
        });

        //THEN
        verify(capacityPersistencePort, never()).addCapacity(any(Capacity.class));
        assertEquals(Constants.DUPLICATE_ITEMS_LIST_EXCEPTION_MESSAGE, exception.getMessage());


    }

    @Test
    @DisplayName("Expected list of capacities to be returned")
    void shouldGetAllCapacities() {
        // GIVEN

        List<Capacity> capacities = capacityData.createCapacities();

        //WHEN
        when(capacityPersistencePort.getAllCapacities(anyInt(), anyInt(), anyBoolean(),anyBoolean()))
                .thenReturn(capacities);
        List<Capacity> actualCapacities = capacityUseCase
                .getAllCapacities(ParametersData.PAGE,  ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME);

        // THEN
        assertEquals(capacities.size(), actualCapacities.size());
    }


}