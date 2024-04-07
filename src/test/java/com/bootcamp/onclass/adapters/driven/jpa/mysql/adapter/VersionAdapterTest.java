package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
 /*   @Test
    @DisplayName("Test the retrieval of all versions by bootcamp")
    void testGetAllVersionByBootcamp() {
        // GIVEN
        List<Long> bootcampIds = List.of(1L);
        List<VersionEntity> versionEntities = versionData.createVersionEntities();
        List<Version> expectedVersions = versionData.createVersions();

        when(versionRepository.findByBootcampIdIn(bootcampIds,
                PageRequest.of(ParametersData.PAGE, ParametersData.SIZE, Sort.by(versionData.VERSION_INITIAL_DATE))))
                .thenReturn(versionEntities);
        when(versionEntityMapper.toModelList(versionEntities)).thenReturn(expectedVersions);

        // WHEN
        List<Version> actualVersions = versionAdapter
                .getAllVersionByBootcamp(bootcampIds, ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_DATE);

        // THEN
        assertEquals(expectedVersions.size(), actualVersions.size());
        assertEquals(expectedVersions, actualVersions);
        verify(versionRepository).findByBootcampIdIn(eq(bootcampIds), any(Pageable.class));
    }
*/

   /* @Test
    @DisplayName("Test recovery of all versions by bootcamp sorted by initial date in ascending order")
    void testGetAllVersionByBootcampInitialDateOrdenAsc() {
        // GIVEN
        List<Long> bootcampIds = List.of(1L);
        
        List<Version> versions =  versionData.createVersions();

        Sort sort = Sort.by(versionData.VERSION_INITIAL_DATE).ascending();

        Pageable pageable = PageRequest.of(ParametersData.PAGE, ParametersData.SIZE, sort);

        List<VersionEntity> versionEntities = versionData.createVersionEntities();

        // WHEN

        when(versionRepository.findByBootcampIdIn(bootcampIds, pageable)).thenReturn(versionEntities);
        when(versionEntityMapper.toModelList(versionEntities)).thenReturn(versions);


        List<Version> result = versionAdapter
                .getAllVersionByBootcamp(bootcampIds,ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_DATE);

        // THEN
        assertFalse(result.isEmpty());
        assertEquals(versions, result);

        verify(versionRepository).findAll(pageable);

    }
    @Test
    @DisplayName("Test recovery of all versions by bootcamp sorted by initial date in descending order")
    void testGetAllVersionByBootcampInitialDateOrdenDesc() {
        // GIVEN
        List<Long> bootcampIds = List.of(1L);

        List<Version> versions =  versionData.createVersions();

        Sort sort = Sort.by(versionData.VERSION_INITIAL_DATE).descending();

        Pageable pageable = PageRequest.of(ParametersData.PAGE, ParametersData.SIZE, sort);

        List<VersionEntity> versionEntities = versionData.createVersionEntities();

        // WHEN

        when(versionRepository.findByBootcampIdIn(bootcampIds, pageable)).thenReturn(versionEntities);
        when(versionEntityMapper.toModelList(versionEntities)).thenReturn(versions);


        List<Version> result = versionAdapter
                .getAllVersionByBootcamp(bootcampIds,ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_DESC, ParametersData.ORDER_NAME);

        // THEN
        assertFalse(result.isEmpty());
        assertEquals(versions, result);

        verify(versionRepository).findAll(pageable);
    }*/

}