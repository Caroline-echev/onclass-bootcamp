package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.data.BootcampData;
import com.bootcamp.onclass.data.ParametersData;
import com.bootcamp.onclass.data.VersionData;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.model.Bootcamp;
import com.bootcamp.onclass.domain.model.Version;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VersionAdapterTest {
    @Mock
    private IVersionRepository versionRepository;

    @Mock
    private IVersionEntityMapper versionEntityMapper;

    @Mock
    private IBootcampRepository bootcampRepository;

    @Mock
    private IBootcampEntityMapper bootcampEntityMapper;
    @InjectMocks
    private VersionAdapter versionAdapter;

    private VersionData versionData = new VersionData();
    private BootcampData bootcampData = new BootcampData();

    @Test
    @DisplayName("Test adding version")
    void testAddVersion() {
        // GIVEN
        Version version = versionData.createVersion();
        VersionEntity versionEntity = versionData.createVersionEntity();
        BootcampEntity bootcampEntity =  bootcampData.createBootcampEntity();
        Bootcamp bootcamp = bootcampData.createBootcamp();

        //WHEN
        when(bootcampRepository.findById(anyLong())).thenReturn(java.util.Optional.of(bootcampEntity));
        when(bootcampEntityMapper.toModel(any(BootcampEntity.class))).thenReturn(bootcamp);
        when(versionEntityMapper.toEntity(version)).thenReturn(versionEntity);
        when(versionRepository.save(versionEntity)).thenReturn(versionEntity);
        when(versionEntityMapper.toModel(versionEntity)).thenReturn(version);

        VersionAdapter versionAdapter = new VersionAdapter(versionRepository, versionEntityMapper, bootcampRepository, bootcampEntityMapper);
        Version result = versionAdapter.addVersion(version);

        // THEN
        assertNotNull(result);
        verify(versionRepository).save(versionEntity);
    }
    @Test
    @DisplayName("Test adding version with invalid bootcamp ID")
    void testAddVersionWithInvalidBootcampId() {
        // GIVEN
        Version version = versionData.createVersion();


        // WHEN
        when(bootcampRepository.findById(anyLong())).thenReturn(Optional.empty());
        VersionAdapter versionAdapter = new VersionAdapter(versionRepository, versionEntityMapper, bootcampRepository, bootcampEntityMapper);

        // THEN
        assertThrows(NoDataFoundException.class, () -> {
            versionAdapter.addVersion(version);
        });
    }

    @Test
    @DisplayName("Test getting all versions by bootcamp")
    void testGetAllVersionByBootcamp() {
        // GIVEN

        List<VersionEntity> versionEntities = versionData.createVersionEntities();
        Page<VersionEntity> versionEntityPage = new PageImpl<>(versionEntities);

        // WHEN

        when(versionRepository.findByBootcampId(eq(versionData.BOOTCAMP_ID), any(Pageable.class))).thenReturn(versionEntityPage);
        List<Version> result = versionAdapter
                .getAllVersionByBootcamp(versionData.BOOTCAMP_ID,ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, versionData.BOOTCAMP_NAME);

        // THEN
        assertNotNull(result);
    }
    @Test
    public void testCreateSort_WhenOrderAscIsTrue_ReturnsAscendingSort() {
        // GIVEN - WHEN

        Pageable pageable = VersionAdapter
                .createSort(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, versionData.INITIAL_DATE);

        // THEN

        assertNotNull(pageable);
        assertEquals(ParametersData.PAGE, pageable.getPageNumber());
        assertEquals(ParametersData.SIZE, pageable.getPageSize());
        Sort sort = pageable.getSort();
        assertNotNull(sort);
        assertTrue(sort.isSorted());
        assertEquals(versionData.INITIAL_DATE, sort.getOrderFor(versionData.INITIAL_DATE).getProperty());
        assertEquals(Sort.Direction.ASC, sort.getOrderFor(VersionData.INITIAL_DATE).getDirection());
    }

    @Test
    public void testCreateSort_WhenOrderAscIsFalse_ReturnsDescendingSort() {
        // GIVEN - WHEN
        Pageable pageable = VersionAdapter
                .createSort(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_DESC, versionData.INITIAL_DATE);

        // THEN
        assertNotNull(pageable);
        assertEquals(ParametersData.PAGE, pageable.getPageNumber());
        assertEquals(ParametersData.SIZE, pageable.getPageSize());
        Sort sort = pageable.getSort();
        assertNotNull(sort);
        assertTrue(sort.isSorted());
        assertEquals(versionData.INITIAL_DATE, sort.getOrderFor(versionData.INITIAL_DATE).getProperty());
        assertEquals(Sort.Direction.DESC, sort.getOrderFor(versionData.INITIAL_DATE).getDirection());
    }

    @Test
    public void testGetAllVersionByBootcamp_WhenNoVersionsFound_ThrowsException() {
        // GIVEN
        Pageable pageable = VersionAdapter
                .createSort(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, versionData.INITIAL_DATE);
        PageImpl<VersionEntity> pageResult = new PageImpl<>(new ArrayList<>(), pageable, 0);

        // WHEN
        when(versionRepository.findAll(pageable)).thenReturn(pageResult);
        VersionAdapter versionAdapter = new VersionAdapter(versionRepository, null, null, null);

        //THEN
        NoDataFoundException exception = assertThrows(NoDataFoundException.class, () -> {
            versionAdapter.getAllVersionByBootcamp(null, ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, versionData.INITIAL_DATE);
        });

        assertEquals(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE, exception.getMessage());
    }
}