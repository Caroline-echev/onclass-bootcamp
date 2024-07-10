package com.bootcamp.onclass.adapters.driving.http.controller;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddBootcampRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.bootcamp.BootcampResponse;
import com.bootcamp.onclass.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.bootcamp.onclass.domain.api.IBootcampServicePort;
import com.bootcamp.onclass.domain.model.Bootcamp;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication")
@SecurityRequirement(name = "bearer-key")
public class BootcampRestControllerAdapter {
    private final IBootcampServicePort bootcampServicePort;
    private final IBootcampRequestMapper bootcampRequestMapper;
    private final IBootcampResponseMapper bootcampResponseMapper;
    @ApiResponse(responseCode = "200", description = "Admin registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addBootcamp")
    public ResponseEntity<Bootcamp> addBootcamp (@Valid @RequestBody AddBootcampRequest request){
        return new ResponseEntity<>(bootcampServicePort.addBootcamp(bootcampRequestMapper.addRequestToBootcamp(request)), HttpStatus.OK);
    }
    @ApiResponse(responseCode = "200", description = "Admin registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getBootcamps")
    public ResponseEntity<List<BootcampResponse>> getAllBootcamps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "true") boolean orderAsc,
            @RequestParam(defaultValue = "false") boolean orderName) {
        List<Bootcamp> bootcamps = bootcampServicePort.getAllBootcamps(page, size, orderAsc, orderName);
        List<BootcampResponse> response = bootcamps.stream().map(bootcampResponseMapper::modelToFindResponse).toList();

        return ResponseEntity.ok().body(response);
    }
}
