package com.bootcamp.onclass.adapters.driving.http.controller;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.version.VersionResponse;
import com.bootcamp.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.IVersionResponseMapper;
import com.bootcamp.onclass.data.VersionData;
import com.bootcamp.onclass.domain.api.IVersionServicePort;
import com.bootcamp.onclass.domain.model.Version;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}