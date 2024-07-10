package com.bootcamp.onclass.adapters.driving.http.controller;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.version.VersionResponse;
import com.bootcamp.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.IVersionResponseMapper;
import com.bootcamp.onclass.domain.api.IVersionServicePort;
import com.bootcamp.onclass.domain.model.Version;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/version")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication")
@SecurityRequirement(name = "bearer-key")
public class VersionRestControllerAdapter {
    private final IVersionServicePort versionServicePort;
    private final IVersionRequestMapper versionRequestMapper;
    private final IVersionResponseMapper versionResponseMapper;
    @ApiResponse(responseCode = "200", description = "Admin registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addVersion")
    public ResponseEntity<VersionResponse> addVersion(@Valid @RequestBody AddVersionRequest request){
        Version version = versionServicePort.addVersion(versionRequestMapper.addRequestToVersion(request));
        VersionResponse response = versionResponseMapper.modelToFindResponse(version);
        return ResponseEntity.ok(response);
    }
    @ApiResponse(responseCode = "200", description = "Admin registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getVersions")
    public ResponseEntity<List<VersionResponse>> getAllVersionByBootcamp(@Valid
            @RequestParam(required = false) Long bootcampId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "true") boolean orderAsc,
            @RequestParam(defaultValue = "initialDate") String orderType) {
        List<Version> versions = versionServicePort.getAllVersionByBootcamp(bootcampId, page, size, orderAsc, orderType);
        List<VersionResponse> response = versions.stream().map(versionResponseMapper::modelToFindResponse).toList();

        return ResponseEntity.ok().body(response);
    }

}
