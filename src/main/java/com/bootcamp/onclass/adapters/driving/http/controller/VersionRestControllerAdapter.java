package com.bootcamp.onclass.adapters.driving.http.controller;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.version.VersionResponse;
import com.bootcamp.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.IVersionResponseMapper;
import com.bootcamp.onclass.domain.api.IVersionServicePort;
import com.bootcamp.onclass.domain.model.Version;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
@RequiredArgsConstructor
public class VersionRestControllerAdapter {
    private final IVersionServicePort versionServicePort;
    private final IVersionRequestMapper versionRequestMapper;
    private final IVersionResponseMapper versionResponseMapper;
    @PostMapping("/addVersion")
    public ResponseEntity<VersionResponse> addVersion(@Valid @RequestBody AddVersionRequest request){
        Version version = versionServicePort.addVersion(versionRequestMapper.addRequestToVersion(request));
        VersionResponse response = versionResponseMapper.modelToFindResponse(version);
        return ResponseEntity.ok(response);
    }
}
