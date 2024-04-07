package com.bootcamp.onclass.adapters.driving.http.controller;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.version.VersionResponse;
import com.bootcamp.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.IVersionResponseMapper;
import com.bootcamp.onclass.data.ParametersData;
import com.bootcamp.onclass.data.VersionData;
import com.bootcamp.onclass.domain.api.IVersionServicePort;
import com.bootcamp.onclass.domain.model.Bootcamp;
import com.bootcamp.onclass.domain.model.Version;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VersionRestControllerAdapterTest {
    @Mock
    private IVersionServicePort versionServicePort;
    @Mock
    private IVersionRequestMapper versionRequestMapper;
    @Mock
    private IVersionResponseMapper versionResponseMapper ;
    @InjectMocks
    private VersionRestControllerAdapter controller;
    private VersionData versionData = new VersionData();

    @Test
    @DisplayName("Test successful adding  of a technology")
    void testAddVersion() {
        // GIVEN
        AddVersionRequest request = versionData.createAddVersionRequest();
        Version version = versionData.createVersion();
        VersionResponse expectedResponse = versionData.createVersionResponse();

        // WHEN
        when(versionRequestMapper.addRequestToVersion(request)).thenReturn(version);
        when(versionServicePort.addVersion(version)).thenReturn(version);
        when(versionResponseMapper.modelToFindResponse(version)).thenReturn(expectedResponse);

        ResponseEntity<VersionResponse> responseEntity = controller.addVersion(request);

        // THEN

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(versionServicePort).addVersion(version);

    }

    @Test
    @DisplayName("Expected list of versiones by bootcamps to be returned")
    void GetAllVersion() {
        // GIVEN

        List<Version> versions = versionData.createVersions();

        // WHEN

        when(versionServicePort
                .getAllVersionByBootcamp(versionData.BOOTCAMP_ID, ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, null)).thenReturn(versions);
        controller.getAllVersionByBootcamp(versionData.BOOTCAMP_ID, ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, null);

        // THEN

        verify(versionServicePort).getAllVersionByBootcamp(versionData.BOOTCAMP_ID, ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, null);
        verify(versionResponseMapper, times(versions.size())).modelToFindResponse(any());
    }
}