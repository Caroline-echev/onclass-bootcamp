package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.bootcamp.onclass.data.BootcampData;
import com.bootcamp.onclass.data.ParametersData;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.model.Bootcamp;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.model.Technology;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BootcampAdapterTest {
    @Mock
    private IBootcampRepository bootcampRepository;
    @Mock
    private IBootcampEntityMapper bootcampEntityMapper;

    @InjectMocks
    private BootcampAdapter bootcampAdapter;

    private BootcampData bootcampData = new BootcampData();

    @Test
    @DisplayName("Test adding a bootcamp with a unique name")
    void shouldAddBootcampWhenNameIsUnique() {
        // GIVEN
        Bootcamp bootcamp = bootcampData.createBootcamp();
        BootcampEntity bootcampEntity = bootcampData.createBootcampEntity();
        when(bootcampRepository.findByName(bootcamp.getName())).thenReturn(Optional.empty());
        when(bootcampEntityMapper.toEntity(bootcamp)).thenReturn(bootcampEntity);
        when(bootcampRepository.save(bootcampEntity)).thenReturn(bootcampEntity);

        // WHEN
        Bootcamp addedBootcamp = bootcampAdapter.addBootcamp(bootcamp);

        // THEN
        assertEquals(bootcamp, addedBootcamp);
        verify(bootcampRepository).save(bootcampEntity);
    }
    @Test
    void shouldNotAddDuplicateBootcamp() {
        // GIVEN
        Bootcamp bootcamp =  bootcampData.createBootcamp();

        //WHEN

        when(bootcampRepository.findByName(any())).thenReturn(Optional.of(new BootcampEntity()));
        assertThrows(ElementAlreadyExistsException.class, () -> bootcampAdapter.addBootcamp(bootcamp));

        // THEN

        verify(bootcampRepository, never()).save(any());
    }


    @Test
    @DisplayName("Expected list of bootcamps to be returned")
    void shouldGetAllBootcamps() {
        // GIVEN

        List<Bootcamp> bootcamps = bootcampData.createBootcamps();
        List<BootcampEntity> bootcampEntities = bootcampData.createBootcampEntities();

        // WHEN

        when(bootcampRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(bootcampEntities));
        when(bootcampEntityMapper.toModelList(bootcampEntities)).thenReturn(bootcamps);
        List<Bootcamp> result = bootcampAdapter
                .getAllBootcamps(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME);

        // THEN

        assertFalse(result.isEmpty());
        assertEquals(bootcamps, result);
    }

    @Test
    @DisplayName("Expected empty list of bootcamps to be returned")
    void shouldGetEmptyBootcamps(){
        // GIVEN

        when(bootcampRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of()));

        // WHEN - THEN
        assertThrows(NoDataFoundException.class, () -> bootcampAdapter
                .getAllBootcamps(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME));
    }
    @Test
    @DisplayName("Test getting all bootcamps sorted ascending by name")
    void shouldGetAllBootcampsSortedAscendingByName() {
        // GIVEN

        List<Bootcamp> bootcamps = bootcampData.createBootcamps();

        Sort sort = Sort.by(ParametersData.NAME).ascending();

        Pageable pageable = PageRequest.of(ParametersData.PAGE, ParametersData.SIZE, sort);

        List<BootcampEntity> bootcampEntities = bootcampData.createBootcampEntities();

        // WHEN

        when(bootcampRepository.findAll(pageable)).thenReturn(new PageImpl<>(bootcampEntities));
        when(bootcampEntityMapper.toModelList(bootcampEntities)).thenReturn(bootcamps);

        List<Bootcamp> result = bootcampAdapter
                .getAllBootcamps(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME);

        // THEN

        assertFalse(result.isEmpty());
        assertEquals(bootcamps, result);
        verify(bootcampRepository).findAll(pageable);
    }
    @Test
    @DisplayName("Test getting all bootcamps sorted descending by name")
    void shouldGetAllBootcampsSortedDescendingByName() {
        // GIVEN


        List<Bootcamp> bootcamps =  bootcampData.createBootcamps();

        Sort sort = Sort.by(ParametersData.NAME).descending();

        Pageable pageable = PageRequest.of(ParametersData.PAGE, ParametersData.SIZE, sort);

        List<BootcampEntity> bootcampEntities = bootcampData.createBootcampEntities();

        // WHEN

        when(bootcampRepository.findAll(pageable)).thenReturn(new PageImpl<>(bootcampEntities));
        when(bootcampEntityMapper.toModelList(bootcampEntities)).thenReturn(bootcamps);

        List<Bootcamp> result = bootcampAdapter
                .getAllBootcamps(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_DESC, ParametersData.ORDER_NAME);

        // THEN
        assertFalse(result.isEmpty());
        assertEquals(bootcamps, result);

        verify(bootcampRepository).findAll(pageable);
    }
    @Test
    @DisplayName("Test getting all bootcamps with sorting by technology size (ascending)")
    void shouldGetAllBootcampsSortedByTechnologySizeAscending() {
        // GIVEN

        Pageable pageable = PageRequest.of(ParametersData.PAGE, ParametersData.SIZE);
        List<BootcampEntity> bootcampEntities = bootcampData.createBootcampEntities();
        when(bootcampRepository.findAllOrderedByCapacitySizeAsc(pageable)).thenReturn(new PageImpl<>(bootcampEntities));
        List<Bootcamp> expectedBootcamp = bootcampData.createBootcamps();
        when(bootcampEntityMapper.toModelList(bootcampEntities)).thenReturn(expectedBootcamp);

        // WHEN
        List<Bootcamp> result = bootcampAdapter
                .getAllBootcamps(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_LIST);

        // THEN
        assertEquals(expectedBootcamp, result);
        verify(bootcampRepository).findAllOrderedByCapacitySizeAsc(pageable);
    }
    @Test
    @DisplayName("Test getting all bootcamps with sorting by technology size (descending)")
    void shouldGetAllBootcampsSortedByTechnologySizeDescending() {
        // GIVEN

        Pageable pageable = PageRequest.of(ParametersData.PAGE, ParametersData.SIZE);
        List<BootcampEntity> bootcampEntities = bootcampData.createBootcampEntities();
        when(bootcampRepository.findAllOrderedByCapacitySizeDesc(pageable)).thenReturn(new PageImpl<>(bootcampEntities));
        List<Bootcamp> expectedBootcamp = bootcampData.createBootcamps();
        when(bootcampEntityMapper.toModelList(bootcampEntities)).thenReturn(expectedBootcamp);

        // WHEN
        List<Bootcamp> result = bootcampAdapter
                .getAllBootcamps(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_DESC, ParametersData.ORDER_LIST);

        // THEN
        assertEquals(expectedBootcamp, result);
        verify(bootcampRepository).findAllOrderedByCapacitySizeDesc(pageable);
    }
}