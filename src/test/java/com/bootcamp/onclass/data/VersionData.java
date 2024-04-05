package com.bootcamp.onclass.data;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.bootcamp.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.version.VersionResponse;
import com.bootcamp.onclass.domain.model.Bootcamp;
import com.bootcamp.onclass.domain.model.Version;

import java.time.LocalDate;
import java.util.List;

public class VersionData {
    static BootcampData bootcampData = new BootcampData();

    static LocalDate initialDate = LocalDate.parse("2024-07-01");
    static LocalDate finalDate = LocalDate.parse("2024-07-31");
    public static Version createVersion() {
        return new Version(1L, initialDate, finalDate,  10,  bootcampData.createBootcamp());
    }
    public static Version createVersionValidateDate() {

        return new Version(1L,  finalDate,initialDate,  10,  bootcampData.createBootcamp());
    }

    public static AddVersionRequest createAddVersionRequest() {
        return new AddVersionRequest(initialDate, finalDate, 10, 1L);
    }

    public static VersionResponse createVersionResponse() {
        return new VersionResponse(1L, initialDate, finalDate, 10, BootcampData.createBootcampNameResponse());
    }
    public  static VersionEntity createVersionEntity() {
        VersionEntity versionEntity = new  VersionEntity(1L, initialDate, finalDate, 10, bootcampData.createBootcampEntity());
        return versionEntity;
    }
}
