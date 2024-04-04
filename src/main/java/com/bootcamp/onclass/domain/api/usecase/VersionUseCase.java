package com.bootcamp.onclass.domain.api.usecase;

import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.api.IVersionServicePort;
import com.bootcamp.onclass.domain.exception.ValidateDateException;
import com.bootcamp.onclass.domain.model.Version;
import com.bootcamp.onclass.domain.spi.IVersionPersistencePort;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VersionUseCase implements IVersionServicePort {
    private final IVersionPersistencePort versionPersistencePort;

    public VersionUseCase(IVersionPersistencePort versionPersistencePort) {
        this.versionPersistencePort = versionPersistencePort;
    }

    @Override
    public Version addVersion(Version version) {
        if (!validateDateRange(version)) {
            throw new ValidateDateException(Constants.VALIDATE_DATE_RANGE_EXCEPTION_MESSAGE);
        }

        return versionPersistencePort.addVersion(version);
    }
    private boolean validateDateRange(Version version) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate initialLocalDate = LocalDate.parse(version.getInitialDate(), formatter);
        LocalDate finalLocalDate = LocalDate.parse(version.getFinalDate(), formatter);
        return finalLocalDate.isAfter(initialLocalDate);
    }



}
