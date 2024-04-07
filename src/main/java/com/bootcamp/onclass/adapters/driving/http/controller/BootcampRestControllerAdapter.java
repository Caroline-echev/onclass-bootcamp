package com.bootcamp.onclass.adapters.driving.http.controller;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddBootcampRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.bootcamp.BootcampResponse;
import com.bootcamp.onclass.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.bootcamp.onclass.domain.api.IBootcampServicePort;
import com.bootcamp.onclass.domain.model.Bootcamp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
public class BootcampRestControllerAdapter {
    private final IBootcampServicePort bootcampServicePort;
    private final IBootcampRequestMapper bootcampRequestMapper;
    private final IBootcampResponseMapper bootcampResponseMapper;
    @PostMapping("/addBootcamp")
    public ResponseEntity<Bootcamp> addBootcamp (@Valid @RequestBody AddBootcampRequest request){
        return new ResponseEntity<>(bootcampServicePort.addBootcamp(bootcampRequestMapper.addRequestToBootcamp(request)), HttpStatus.OK);
    }
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
