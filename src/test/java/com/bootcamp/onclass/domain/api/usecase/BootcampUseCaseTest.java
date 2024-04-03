package com.bootcamp.onclass.domain.api.usecase;

import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.data.BootcampData;
import com.bootcamp.onclass.data.ParametersData;
import com.bootcamp.onclass.domain.exception.DuplicateItemsListException;
import com.bootcamp.onclass.domain.model.Bootcamp;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.model.Technology;
import com.bootcamp.onclass.domain.spi.IBootcampPersistencePort;
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
class BootcampUseCaseTest {
    @Mock
    private IBootcampPersistencePort bootcampPersistencePort;
    @InjectMocks
    private BootcampUseCase bootcampUseCase;

    private BootcampData bootcampData = new BootcampData();
    @Test
    @DisplayName("Test successful adding  of a bootcamp")
    void shouldAddBootcamp() {
        //GIVEN


        Bootcamp bootcamp = bootcampData.createBootcamp();

        //WHEN

        when(bootcampPersistencePort.addBootcamp(bootcamp)).thenReturn(bootcamp);
        bootcampUseCase.addBootcamp(bootcamp);

        //THEN

        verify(bootcampPersistencePort).addBootcamp(bootcamp);
    }

    @Test
    @DisplayName("Test exception by duplicate capacities")
    void shouldNotAddBootcampByDuplicateCapacities(){

        //GIVEN

        Bootcamp bootcamp = bootcampData.createBootcampDuplicateCapacities();

        // WHEN

        Throwable exception = assertThrows(DuplicateItemsListException.class, () -> {
            bootcampUseCase.addBootcamp(bootcamp);
        });

        //THEN
        verify(bootcampPersistencePort, never()).addBootcamp(any(Bootcamp.class));
        assertEquals(Constants.DUPLICATE_ITEMS_LIST_EXCEPTION_MESSAGE, exception.getMessage());


    }

    @Test
    @DisplayName("Expected list of bootcamps to be returned")
    void shouldGetAllBootcamps() {
        // GIVEN

        List<Bootcamp> expectedBootcamps = BootcampData.createBootcamps();

        //WHEN
        when(bootcampPersistencePort.getAllBootcamps(anyInt(), anyInt(), anyBoolean(),anyBoolean()))
                .thenReturn( expectedBootcamps);
        List<Bootcamp> actualBootcamps = bootcampUseCase
                .getAllBootcamps(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME);

        // THEN
        assertEquals( expectedBootcamps.size(), actualBootcamps.size());
    }

}