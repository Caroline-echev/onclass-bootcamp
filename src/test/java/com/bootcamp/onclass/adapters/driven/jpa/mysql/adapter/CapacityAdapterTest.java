package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.bootcamp.onclass.data.CapacityData;
import com.bootcamp.onclass.data.ParametersData;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
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
class CapacityAdapterTest {
    @Mock
    private ICapacityRepository capacityRepository;
    @Mock
    private ICapacityEntityMapper capacityEntityMapper;
    @InjectMocks
    private CapacityAdapter capacityAdapter;

    private CapacityData capacityData = new CapacityData();

    @Test
    @DisplayName("Test adding a capacity with a unique name")
    void shouldAddCapacityWhenNameIsUnique() {
        // GIVEN
        Capacity capacity = capacityData.createCapacity();
        CapacityEntity capacityEntity = capacityData.createCapacityEntity();
        when(capacityRepository.findByName(capacity.getName())).thenReturn(Optional.empty());
        when(capacityEntityMapper.toEntity(capacity)).thenReturn(capacityEntity);
        when(capacityRepository.save(capacityEntity)).thenReturn(capacityEntity);

        // WHEN
        Capacity addedCapacity = capacityAdapter.addCapacity(capacity);

        // THEN
        assertEquals(capacity, addedCapacity);
        verify(capacityRepository).save(capacityEntity);
    }
    @Test
    void shouldNotAddDuplicateCapacity() {
        // GIVEN
        Capacity capacity =  capacityData.createCapacity();

        //WHEN

        when(capacityRepository.findByName(any())).thenReturn(Optional.of(new CapacityEntity()));
        assertThrows(ElementAlreadyExistsException.class, () -> capacityAdapter.addCapacity(capacity));

        // THEN

        verify(capacityRepository, never()).save(any());
    }

    @Test
    @DisplayName("Expected list of capacities to be returned")
    void shouldGetAllCapacities() {
        // GIVEN

        List<Capacity> capacities = capacityData.createCapacities();
        List<CapacityEntity> capacityEntities = capacityData.createCapacityEntities();

        // WHEN

        when(capacityRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(capacityEntities));
        when(capacityEntityMapper.toModelList(capacityEntities)).thenReturn(capacities);
        List<Capacity> result = capacityAdapter
                .getAllCapacities(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME);

        // THEN

        assertFalse(result.isEmpty());
        assertEquals(capacities, result);
    }

    @Test
    @DisplayName("Expected empty list of capacities to be returned")
    void shouldGetEmptyCapacities(){
        // GIVEN

        when(capacityRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of()));

        // WHEN - THEN
        assertThrows(NoDataFoundException.class, () -> capacityAdapter
                .getAllCapacities(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME));
    }
    @Test
    @DisplayName("Test getting all capacities sorted ascending by name")
    void shouldGetAllCapacitiesSortedAscendingByName() {
        // GIVEN

        List<Capacity> capacities = capacityData.createCapacities();

        Sort sort = Sort.by(ParametersData.NAME).ascending();

        Pageable pageable = PageRequest.of(ParametersData.PAGE, ParametersData.SIZE, sort);

        List<CapacityEntity> capacityEntities = capacityData.createCapacityEntities();

        // WHEN

        when(capacityRepository.findAll(pageable)).thenReturn(new PageImpl<>(capacityEntities));
        when(capacityEntityMapper.toModelList(capacityEntities)).thenReturn(capacities);

        List<Capacity> result = capacityAdapter
                .getAllCapacities(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME);

        // THEN

        assertFalse(result.isEmpty());
        assertEquals(capacities, result);
        verify(capacityRepository).findAll(pageable);
    }
    @Test
    @DisplayName("Test getting all capacities sorted descending by name")
    void shouldGetAllCapacitiesSortedDescendingByName() {
        // GIVEN


        List<Capacity> capacities = capacityData.createCapacities();

        Sort sort = Sort.by(ParametersData.NAME).descending();

        Pageable pageable = PageRequest.of(ParametersData.PAGE, ParametersData.SIZE, sort);

        List<CapacityEntity> capacityEntities = capacityData.createCapacityEntities();

        // WHEN

        when(capacityRepository.findAll(pageable)).thenReturn(new PageImpl<>(capacityEntities));
        when(capacityEntityMapper.toModelList(capacityEntities)).thenReturn(capacities);

        List<Capacity> result = capacityAdapter
                .getAllCapacities(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_DESC, ParametersData.ORDER_NAME);

        // THEN
        assertFalse(result.isEmpty());
        assertEquals(capacities, result);

        verify(capacityRepository).findAll(pageable);
    }
    @Test
    @DisplayName("Test getting all capacities with sorting by technology size (ascending)")
    void shouldGetAllCapacitiesSortedByTechnologySizeAscending() {
        // GIVEN

        Pageable pageable = PageRequest.of(ParametersData.PAGE, ParametersData.SIZE);
        List<CapacityEntity> capacityEntities = capacityData.createCapacityEntities();
        when(capacityRepository.findAllOrderedByTechnologySizeAsc(pageable)).thenReturn(new PageImpl<>(capacityEntities));
        List<Capacity> expectedCapacities = capacityData.createCapacities();
        when(capacityEntityMapper.toModelList(capacityEntities)).thenReturn(expectedCapacities);

        // WHEN
        List<Capacity> result = capacityAdapter
                .getAllCapacities(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_LIST);

        // THEN
        assertEquals(expectedCapacities, result);
        verify(capacityRepository).findAllOrderedByTechnologySizeAsc(pageable);
    }
    @Test
    @DisplayName("Test getting all capacities with sorting by technology size (descending)")
    void shouldGetAllCapacitiesSortedByTechnologySizeDescending() {
        // GIVEN

        Pageable pageable = PageRequest.of(ParametersData.PAGE, ParametersData.SIZE);
        List<CapacityEntity> capacityEntities = capacityData.createCapacityEntities();
        when(capacityRepository.findAllOrderedByTechnologySizeDesc(pageable)).thenReturn(new PageImpl<>(capacityEntities));
        List<Capacity> expectedCapacities = capacityData.createCapacities();
        when(capacityEntityMapper.toModelList(capacityEntities)).thenReturn(expectedCapacities);

        // WHEN
        List<Capacity> result = capacityAdapter
                .getAllCapacities(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_DESC, ParametersData.ORDER_LIST);

        // THEN
        assertEquals(expectedCapacities, result);
        verify(capacityRepository).findAllOrderedByTechnologySizeDesc(pageable);
    }
}